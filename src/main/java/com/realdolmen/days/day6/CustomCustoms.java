package com.realdolmen.days.day6;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readString;
import static java.util.Arrays.stream;

public class CustomCustoms {
	
	public static void main(String[] args) {
		try {
			String input = readString(Paths.get("src/main/resources/input-day-6"));
			
			long result = stream(input.split("\\n\\n"))
					.map(s -> s.replaceAll("\\s", ""))
					.map(s -> s.chars().distinct().count())
					.reduce(Long::sum)
					.orElse(-1L);
			
			System.out.println(result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
