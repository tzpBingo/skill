// Таблица умножения в XLSX
package main

import (
	"log"
	"os"

	"strconv"

	flags "github.com/jessevdk/go-flags"
	"github.com/tealeg/xlsx"
)

var opts struct {
	N        int    `short:"n" long:"number" default:"6" description:"Число N для таблицы"`
	FileSAVE string `short:"s" long:"save" default:"table.xlsx" description:"Файл таблицы"`
}

func main() {
	// разбор флагов
	flags.Parse(&opts)

	// в какой папке исполняемый файл
	pwdDir, _ := os.Getwd()

	// создание файла log для записи ошибок
	fLog, err := os.OpenFile(pwdDir+`/.log`, os.O_APPEND|os.O_WRONLY|os.O_CREATE, 0640)
	if err != nil {
		log.Fatalln(err)
	}
	defer fLog.Close()

	// запись ошибок и инфы в файл
	log.SetOutput(fLog)

	// переменные
	// файл
	var file *xlsx.File
	// страница
	var sheet *xlsx.Sheet
	// строка
	var row *xlsx.Row
	// ячейка
	var cell *xlsx.Cell
	// стиль написания в ячейке
	style := xlsx.NewStyle()

	// обрамление
	border := *xlsx.NewBorder("thin", "thin", "thin", "thin")
	style.Border = border
	style.ApplyBorder = true

	// заливка
	fill := *xlsx.NewFill("solid", "00FF0000", "FF000000")
	style.Fill = fill
	style.ApplyFill = true

	// шрифт
	font := *xlsx.NewFont(10, "Verdana")
	style.Font = font
	style.ApplyFont = true

	// создаем новый файл
	file = xlsx.NewFile()
	// добавляем страницу
	sheet, err = file.AddSheet("Sheet")
	log.Printf("Добавление страницы %v", sheet.Name)
	if err != nil {
		log.Printf("Ошибка добаления страницы %v", err)
	}

	// добавляем ячейки в строку (следуют одна за одной)
	for i := 1; i <= opts.N; i++ {
		if i == 1 {
			// создаем новую строку в странице
			row = sheet.AddRow()
			cell = row.AddCell()
		}

		cell = row.AddCell()
		cell.SetStyle(style)
		cell.SetInt(i)
	}

	// добавляем ячейки в столбец (следуют одна за одной)
	for i := 1; i <= opts.N; i++ {
		row = sheet.AddRow()
		cell = row.AddCell()
		cell.SetStyle(style)
		cell.SetInt(i)
	}

	for i := 1; i <= opts.N; i++ {
		for j := 1; j <= opts.N; j++ {
			cell = sheet.Cell(i, j)
			cell.SetInt(i * j)
			log.Printf("Запись в %v", strconv.Itoa(i)+" * "+strconv.Itoa(j))
		}
	}

	// сохранение
	err = file.Save(pwdDir + "/" + opts.FileSAVE)
	if err != nil {
		log.Printf("Ошибка сохранения файла %v", err)
	}
	log.Printf("Сохранение в %v", opts.FileSAVE)

	// готово
	log.Println("Готово")
}
