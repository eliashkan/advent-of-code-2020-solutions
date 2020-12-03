package com.realdolmen.days.day3;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TobogganTrajectory {
	
	public static void main(String[] args) {
		
		try (var reader = new BufferedReader(
				new FileReader(
						new File("src/main/resources/input-day-3")))) {
			
			// input goes in a list of lists of 0's and 1's
			ArrayList<ArrayList<Byte>> rows = new ArrayList<>();
			
			reader.lines().forEach(line -> {
				ArrayList<Byte> list = new ArrayList<>();
				for (char c : line.toCharArray()) {
					if (c == '.') list.add(Byte.valueOf("0"));
					else list.add(Byte.valueOf("1"));
				}
				rows.add(list);
			});
			
			// part 1
			bombTheHill(rows, 3, 1);
			
			// part 2
			// Guava Multimap for possible routes because first and last entry have duplicate keys
			// And Java has no Collection/Map that supports duplicate keys
			// With a classic Map could've used the elegant Map.ofEntries...
			Multimap<Integer, Integer> map = MultimapBuilder.hashKeys().arrayListValues().build();
			map.put(1, 1);
			map.put(3, 1);
			map.put(5, 1);
			map.put(7, 1);
			map.put(1, 2);
			
			int result = map
					.entries()
					.stream()
					.mapToInt(entry -> bombTheHill(rows, entry.getKey(), entry.getValue()))
					.reduce(1, (a, b) -> a * b);
			
			System.out.printf("The answer to part 2 is: %d", result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int bombTheHill(ArrayList<ArrayList<Byte>> rows, int slope, int speed) {
		// y keeps track of y-axis position
		int y = 0;
		int counter = 0;
		
		// iterate rows and check for a 1 right after descending {speed} number of rows
		for (int i = 0; i < rows.size(); i = i + speed) {
			ArrayList<Byte> row = rows.get(i);
			
			// check for a tree
			if (row.get(y) == 1) counter++;
			y = y + slope;
			
			// if y passes the right bound, subtract the row size from y to wrap around to beginning
			if (y >= row.size()) y = y - row.size();
		}
		
		System.out.printf("For slope %d and speed %d you encounter %d trees%n", slope, speed, counter);
		
		return counter;
	}
}
