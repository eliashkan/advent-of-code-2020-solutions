package com.realdolmen.days.day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {

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
            for (int firstCursor : integerSet) {
                int complement = 2020 - firstCursor;
                for (int secondCursor : integerSet) {
                    int secondComplement = complement - secondCursor;
                    if (integerSet.contains(secondComplement)) {
                        System.out.println(firstCursor * secondCursor * secondComplement);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
