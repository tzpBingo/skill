package main

import (
	"io/ioutil"
	"os"
)

// read 负责读取文件
// 这是一个通用的方法
func read(path string) []byte {
	file, err := os.Open(path)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	data, err := ioutil.ReadAll(file)
	return data
}
