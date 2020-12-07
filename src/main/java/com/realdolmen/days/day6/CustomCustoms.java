package com.realdolmen.days.day6;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static java.nio.file.Files.readString;
import static java.util.Arrays.stream;

public class CustomCustoms {
	
	public static void main(String[] args) {
		// Part 1
		try {
			String input = readString(Paths.get("src/main/resources/input-day-6"));
			
			Long result = splitOnDoubleNewline(input)
					.map(removeWhitespaceCharacters())
					.map(CustomCustoms::countUniqueChars)
					.reduce(Long::sum)
					.orElse(-1L);
			
			System.out.println(result);
			
			// Part 2
			Integer resultPart2 = splitOnDoubleNewline(input)
					.map(CustomCustoms::toLinesArray)
					.map(CustomCustoms::countCommonCharacters)
					.reduce(Integer::sum)
					.orElse(-1);
			System.out.println(resultPart2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Stream<String> splitOnDoubleNewline(String input) {
		return stream(input.split("\\n\\n"));
	}
	
	private static Function<String, String> removeWhitespaceCharacters() {
		return string -> string.replaceAll("\\s", "");
	}
	
	private static long countUniqueChars(String s) {
		return s.chars().distinct().count();
	}
	
	private static String[] toLinesArray(String s) {
		return s.split("\\s");
	}
	
	private static Integer countCommonCharacters(String[] list) {
		if (list.length == 1) return list[0].length();
		int counter = 0;
		for (char c : list[0].toCharArray()) {
			boolean contains = IntStream.range(1, list.length).allMatch(i -> list[i].contains(valueOf(c)));
			if (contains) counter++;
		}
		return counter;
	}
}
