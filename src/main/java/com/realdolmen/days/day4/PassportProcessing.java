package com.realdolmen.days.day4;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.nio.file.Files.readString;

public class PassportProcessing {
	
	public static void main(String[] args) {
		
		// each passport is represented by a map in this list
		List<Map<String, String>> inputMapList = prepInput();
		
		// the required keys and rules
		List<String> requiredKeys = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
		
		// Part 1
		// filter on the predicate that each map must contain all required fields (thus allMatch) and finally count
		long countPart1 = inputMapList.stream()
				.filter(map -> requiredKeys.stream().allMatch(map::containsKey))
				.count();
		
		// ... PROFIT!
		System.out.println(countPart1);
		
		// Part 2
		long countPart2 = inputMapList.stream()
				.filter(map -> requiredKeys.stream().allMatch(map::containsKey))
				.filter(PassportProcessing::checkPassportValues)
				.count();
		System.out.println(countPart2);
	}
	
	private static boolean checkPassportValues(Map<String, String> map) {
		AtomicReference<Boolean> isValid = new AtomicReference<>(true);
		map.forEach((key, value) -> {
			switch (key) {
				case "byr":
					// four digits; at least 1920 and at most 2002.
					if (!value.matches("\\b(19[2-9][0-9]|200[0-2])\\b")) isValid.set(false);
					break;
				case "iyr":
					// four digits; at least 2010 and at most 2020.
					if(!value.matches("\\b(201[0-9]|2020)\\b")) isValid.set(false);
					break;
				case "eyr":
					// four digits; at least 2020 and at most 2030.
					if(!value.matches("\\b(202[0-9]|2030)\\b")) isValid.set(false);
					break;
				case "hgt":
					// a number followed by either cm or in:
					//    If cm, the number must be at least 150 and at most 193.
					//    If in, the number must be at least 59 and at most 76.
					if(!value.matches("\\b((1[5-8][0-9]|19[0-3])cm|(59|6[0-9]|7[0-6])in)\\b")) isValid.set(false);
					break;
				case "hcl":
					// a # followed by exactly six characters 0-9 or a-f.
					if(!value.matches("#([0-9]|[a-f]){6}")) isValid.set(false);
					break;
				case "ecl":
					// exactly one of: amb blu brn gry grn hzl oth.
					if(!value.matches("\\b(amb|blu|brn|gry|grn|hzl|oth)\\b")) isValid.set(false);
					break;
				case "pid":
					// a nine-digit number, including leading zeroes.
					if(!value.matches("\\b\\d{9}\\b")) isValid.set(false);
					break;
			}
		});
		return isValid.get();
	}
	
	private static List<Map<String, String>> prepInput() {
		List<Map<String, String>> mapList = new ArrayList<>();
		try {
			// read out input in one big string
			String input = readString(Paths.get("src/main/resources/input-day-4"));
			
			// split input passports where there's two newlines
			String[] passports = input.split("\\n\\n");
			
			// split every passport over \s which matches any whitespace character (space, newline, ...)
			// split over ":" and collect to a map
			mapList = Arrays.stream(passports)
					.map(s -> s.split("\\s"))
					.map(arr -> Arrays.stream(arr)
							.map(s -> s.split(":"))
							.collect(Collectors.toMap(
									split -> split[0],
									split -> split[1],
									(a, b) -> b)))
					.collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapList;
	}
}
