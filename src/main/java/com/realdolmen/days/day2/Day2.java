package com.realdolmen.days.day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

public class Day2 {

    private static int result1 = 0;
    private static int result2 = 0;

    public static void main(String[] args) {

        try (Stream<String> lines = new BufferedReader(
                new FileReader(
                        new File("src/main/resources/input-day-2")
                )
        ).lines()) {

            lines.forEach(s -> {
                // input prep
                String[] splitOverColon = s.split(":");
                String[] splitRules = splitOverColon[0].split("[- ]");
                int min = Integer.parseInt(splitRules[0]);
                int max = Integer.parseInt(splitRules[1]);
                char match = splitRules[2].charAt(0);
                char[] password = splitOverColon[1].toCharArray();
                int charCount = 0;

                // first part
                for (char c : password) {
                    if (c == match) charCount++;
                }
                if (charCount >= min && charCount <= max) {
                    result1++;
                }

                // second part
                if (password[min - 1] == match ^ password[max - 1] == match) {
                    result2++;
                }
            });

            System.out.println(result1);
            System.out.println(result2);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
