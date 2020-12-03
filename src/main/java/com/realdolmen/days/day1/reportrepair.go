package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strconv"
)

func readInts(r io.Reader) ([]int, error) {
	scanner := bufio.NewScanner(r)
	scanner.Split(bufio.ScanWords)
	var result []int
	for scanner.Scan() {
		x, err := strconv.Atoi(scanner.Text())
		if err != nil {
			return result, err
		}
		result = append(result, x)
	}
	return result, scanner.Err()
}

func main() {
	file, _ := os.Open("input")
	reader := bufio.NewReader(file)
	input, _ := readInts(reader)

one:
	for _, x := range input {
		complement := 2020 - x
		for _, y := range input {
			if y == complement {
				fmt.Print("two numbers: ", x*complement, "\n")
				break one
			}
		}
	}

two:
	for _, x := range input {
		complement := 2020 - x
		for _, y := range input {
			complement2 := complement - y
			for _, z := range input {
				if z == complement2 {
					fmt.Print("three numbers: ", x*y*z)
					break two
				}
			}
		}
	}
}
