package com.realdolmen.days.day5;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

import static java.nio.file.Files.readAllLines;
import static java.util.Comparator.comparingInt;

public class BinaryBoarding {
	
	public static void main(String[] args) {
		
		try {
			Optional<Integer> max = readAllLines(Paths.get("src/main/resources/input-day-5"))
					.stream()
					.map(line -> new String[]{line.substring(0, 7), line.substring(7)})
					.map(array -> {
						int row = binarySearch(array[0], 0, 127);
						int column = binarySearch(array[1], 0, 7);
						return row * 8 + column;
					})
					.max(comparingInt(o -> o));
			System.out.println(max.orElse(-1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int binarySearch(String sequence, int low, int high) {
		int middle = (low + high) / 2;
		
		if (high < low) return -1;
		
		if (sequence.isEmpty()) {
			return high;
		} else if (sequence.charAt(0) == 'F' || sequence.charAt(0) == 'L') {
			return binarySearch(
					sequence.substring(1), low, middle);
		} else {
			return binarySearch(
					sequence.substring(1), middle, high);
		}
	}
}
