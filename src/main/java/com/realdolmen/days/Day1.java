package com.realdolmen.days;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {

    public static void main(String[] args) {

        try {
            Set<Integer> integerSet;
            try (Stream<String> lines = new BufferedReader(
                    new FileReader(
                            new File("src/main/resources/input")
                    )
            ).lines()) {

                integerSet = lines
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());
            }
            for (int i :
                    integerSet) {
                if (integerSet.contains(2020 - i)) {
                    System.out.println(i * (2020 - i));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
