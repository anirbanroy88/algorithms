package com.anirban.skiing.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.anirban.skiing.exception.StupidInputException;
import com.anirban.skiing.singapore.Mountain;

public class MyBeautfulSkiingRange {

	public static void main(String[] args) throws IOException, StupidInputException {
		Mountain mountain = new Mountain(constructArrayFromFile());
		mountain.build();
		mountain.printTheLovelyPath();
	}

	private static int[][] constructArrayFromFile(boolean b) {
		int[][] arr = {{6,8,4,6},{5,3,2,5},{0,2,7,7},{2,1,3,2}};
		return arr;
	}

	/**
	 * Construct the massive 1000 cross 1000 array by reading values from file
	 * 
	 * @return array representing height of peaks
	 * @throws IOException
	 */
	private static int[][] constructArrayFromFile() throws IOException {
		int[][] peaks = new int[1000][1000];
		int columnCounter = 0;
		File file = new File("E:\\Work\\map.txt");
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		while ((line = br.readLine()) != null) {
			int rowCounter = 0;
			String[] elements = line.split(" ");
			for (String element : elements) {
				peaks[columnCounter][rowCounter] = Integer.parseInt(element);
				rowCounter++;
			}
			columnCounter++;
		}
		br.close();
		return peaks;

	}

}
