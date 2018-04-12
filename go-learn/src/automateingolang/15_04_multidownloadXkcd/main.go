//загружает комиксы XKCD.com многопоточно, но кривовато
package main

import (
	"flag"
	"io"
	"io/ioutil"
	"log"
	"net/http"
	"os"
	"regexp"
	"time"

	"strings"

	"strconv"

	"sort"

	"github.com/opesun/goquery"
)

var (
	golog bool
)

func init() {
	flag.BoolVar(&golog, "log", false, "Use logfile")
}

func main() {
	// разбор флагов
	flag.Parse()

	if golog {
		// создание файла log для записи ошибок
		fLog, err := os.OpenFile(`./.log`, os.O_APPEND|os.O_WRONLY|os.O_CREATE, 0640)
		if err != nil {
			log.Fatalln(err)
		}
		defer fLog.Close()

		// запись ошибок и инфы в файл и вывод
		log.SetOutput(io.MultiWriter(fLog, os.Stdout))
	}

	// исходный url
	url := `http://xkcd.com`

	err := os.Mkdir("./xkcd", 0775)
	if err != nil {
		log.Println(err)
	}

	// TODO: Загрузить страницу
	// запрос по url
	resp, err := http.Get(url)
	log.Printf("Загружается страница:	%v", url)
	if err != nil {
		log.Fatalln(err)
	}

	// TODO: Найти URL комикса
	// парсинг ответа
	x, err := goquery.Parse(resp.Body)
	if err != nil {
		log.Fatalln(err)
	}
	resp.Body.Close()

	// TODO: Получить URL для prev
	// ищу ссылочки на старые комиксы
	var urlInt []int
	regLastLink, _ := regexp.Compile(`\/[0-9]{1,}\/`)
	for _, i := range x.Find("a").Attrs("href") {
		if regLastLink.MatchString(i) {
			a, err := strconv.Atoi(strings.Trim(i, "/"))
			if err != nil {
				log.Fatalln(err)
			}
			urlInt = append(urlInt, a)
		}
	}
	sort.Ints(urlInt)
	// предыдущая ссылка
	log.Println(urlInt[len(urlInt)-1])

	// формирование массива ссылок
	var massUrls []string
	for k := urlInt[len(urlInt)-1] + 1; k >= 1; k-- {
		massUrls = append(massUrls, url+"/"+strconv.Itoa(k))
	}

	// многопоточность
	i := 0
	for i < len(massUrls) {
		urls := massUrls[i : i+5]
		for _, url := range urls {
			go downloads(url)
		}
		i = i + 5
		time.Sleep(10 * time.Second)
	}

	log.Println("Готово")
}

func downloads(urls string) {
	resp, err := http.Get(urls)
	log.Printf("Загружается страница:	%v", urls)
	if err != nil {
		log.Fatalln(err)
	}
	defer resp.Body.Close()

	// Найти URL комикса
	// парсинг ответа
	x, err := goquery.Parse(resp.Body)
	if err != nil {
		log.Fatalln(err)
	}

	// нахождение ссылки
	regStr, _ := regexp.Compile(`comics`)
	for _, i := range x.Find("img").Attrs("src") {
		if regStr.MatchString(i) {
			// TODO: Загрузить комикс
			respImg, err := http.Get("http:" + i)
			log.Printf("Загружается изображение:	%v", "http:"+i)
			if err != nil {
				log.Fatalln(err)
			}
			defer respImg.Body.Close()

			// запись ответа в переменную
			bodyImg, err := ioutil.ReadAll(respImg.Body)
			if err != nil {
				log.Fatalln(err)
			}

			// создание файла для загрузки картинки
			nameImg := strings.Split(i, "/")
			name := "./xkcd/" + nameImg[len(nameImg)-1]
			fSave, err := os.Create(name)
			if err != nil {
				log.Fatalln(err)
			}
			defer fSave.Close()
			log.Printf("Сохрание в:	%v", name)
			fSave.Write(bodyImg)
		}
	}
	time.Sleep(5 * time.Second)
}
