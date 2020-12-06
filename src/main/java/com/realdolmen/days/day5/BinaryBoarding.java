package com.realdolmen.days.day5;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.nio.file.Files.readAllLines;
import static java.util.Comparator.comparingInt;

public class BinaryBoarding {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> ids = new ArrayList<>();
		try {
			// Part 1
			Optional<Integer> max = readAllLines(Paths.get("src/main/resources/input-day-5"))
					.stream()
					.map(line -> new String[]{line.substring(0, 7), line.substring(7)})
					.map(array -> {
						int row = binarySearch(array[0], 0, 127);
						int column = binarySearch(array[1], 0, 7);
						int id = row * 8 + column;
						ids.add(id);
						return id;
					})
					.max(comparingInt(o -> o));
			System.out.println(max.orElse(-1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Part 2
		// Find sum of all possible id's
		// Need to find min and max in actual id's because there's seats missing at beginning and end
		// Difference between sum of all possible id's and sum of all actual id's in our input = our missing seat id.
		int min = ids.stream().min(Integer::compareTo).get();
		int max = ids.stream().max(Integer::compareTo).get();
		int expected = IntStream.range(min, max + 1).sum();
		int actual = ids.stream().mapToInt(i -> i).sum();
		int missingId = expected - actual;
		
		System.out.println("result: " + missingId);
		
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
