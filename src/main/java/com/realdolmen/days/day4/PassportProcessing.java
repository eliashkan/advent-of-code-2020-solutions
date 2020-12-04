package com.realdolmen.days.day4;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.file.Files.readString;

public class PassportProcessing {
	
	public static void main(String[] args) {
		
		// each passport is represented by a map in this list
		List<Map<String, String>> mapList = prepInput();
		
		// the required fields
		List<String> required = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
		
		// filter on the predicate that each map must contain all required fields (thus allMatch) and finally count
		long count = mapList.stream()
				.filter(map -> required.stream().allMatch(map::containsKey))
				.count();
		
		// ... PROFIT!
		System.out.println(count);
		
	}
	
	private static List<Map<String, String>> prepInput() {
		List<Map<String, String>> mapList = null;
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
