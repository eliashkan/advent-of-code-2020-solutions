package com.realdolmen.days.day3;

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
			
			// y keeps track of y-axis position
			int y = 0;
			int counter = 0;
			
			// traverse rows and check for a 1 right after continuing from the last row
			for (var row : rows) {
				if (row.get(y) == 1) counter++;
				y = y + 3;
				// if y passes the right bound subtract the row size from y to wrap around to beginning
				if (y >= row.size()) y = y - row.size();
			}
			
			System.out.println(counter);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
