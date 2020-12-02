package com.realdolmen.days.day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

public class Day2 {

    private static String[] splitOverColon;
    private static String[] splitRules;
    private static int result;

    public static void main(String[] args) {

        try (Stream<String> lines = new BufferedReader(
                new FileReader(
                        new File("src/main/resources/input-day-2")
                )
        ).lines()) {

            // input prep
            lines.forEach(s -> {
                splitOverColon = s.split(":");
                splitRules = splitOverColon[0].split("[- ]");
            });

            // first part
            int charCount = 0;
            for (char c : splitOverColon[1].toCharArray()) {
                if (c == splitRules[2].charAt(0)) charCount++;
            }
            if (charCount >= Integer.parseInt(splitRules[0])
                    && charCount <= Integer.parseInt(splitRules[1])) {
                result++;
            }
            System.out.println(result);

            // second part


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
