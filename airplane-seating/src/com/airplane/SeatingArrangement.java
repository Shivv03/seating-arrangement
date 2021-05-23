package com.airplane;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SeatingArrangement {

	public static final int passengers = 30;
	public static Queue<Integer> passengersQueue = new LinkedList<>();

	public static void main(String[] args) {
		List<Integer[]> seatsGrid = new ArrayList<>();
		Integer[] seatGrid1 = { 3, 2 };
		Integer[] seatGrid2 = { 4, 3 };
		Integer[] seatGrid3 = { 2, 3 };
		Integer[] seatGrid4 = { 3, 4 };
		seatsGrid.add(seatGrid1);
		seatsGrid.add(seatGrid2);
		seatsGrid.add(seatGrid3);
		seatsGrid.add(seatGrid4);
		///To Print result in CSV file, please enter fileName, else leave it blank  
		String FileName = ""; 
		
		for (int i = 1; i <= passengers; i++) {
			passengersQueue.add(i);
		}
		String[][][] seats = constructSeatsMatrix(seatsGrid);
		allocateAisleSeats(seats, seatsGrid);
		allocateWindowSeats(seats, seatsGrid);
		allocateCenterSeats(seats, seatsGrid);
		printSeats(seats, seatsGrid);
		if(!passengersQueue.isEmpty()) {
			int allottedSeats = Integer.valueOf(passengersQueue.peek()) -1 ;
			System.out.println("Not Enough Seats, Seats only Allocated for " + allottedSeats + " passengers");
		}
		if(!"".equals(FileName)) {
			printAsCSV(seats, seatsGrid, FileName);
		}
	}

	

	private static String[][][] constructSeatsMatrix(List<Integer[]> seatsGrid) {
		int i = 0;
		String[][][] seats3dGrid = new String[seatsGrid.size()][][];
		for (Integer[] seatGrid : seatsGrid) {
			String[][] seats2dgrid = new String[seatGrid[1]][seatGrid[0]];
			for (String[] row : seats2dgrid) {
				Arrays.fill(row, "E");
			}
			seats3dGrid[i] = seats2dgrid;
			i++;
		}
		return seats3dGrid;
	}

	private static void allocateAisleSeats(String[][][] seats, List<Integer[]> seatsGrid) {
		int row = 0;
		System.out.println();
		while (!passengersQueue.isEmpty() && row < seats.length) {
			for (int col = 0; col < seats.length; col++) {
				if (seatsGrid.get(col)[1] > row) {
					// Iterate through all the first rows in each column one by one
					for (int seat = 0; seat < seats[col][row].length && !passengersQueue.isEmpty(); seat++) {
						// To Avoid Window Seat in First Column
						if (col == 0) {
							if (seat == seats[col][row].length - 1) {
								seats[col][row][seat] = passengersQueue.remove().toString();
							}
						}
						// To Avoid Window Seat in last Column
						else if (col == seats.length - 1) {
							if (seat == 0) {
								seats[col][row][seat] = passengersQueue.remove().toString();
							}
						} else if (seat == 0 || seat == seats[col][row].length - 1) {
							seats[col][row][seat] = passengersQueue.remove().toString();
						}
					}
				}
			}
			row++;
		}
	}

	private static void allocateWindowSeats(String[][][] seats, List<Integer[]> seatsGrid) {
		int row = 0;
		System.out.println();
		while (!passengersQueue.isEmpty() && row < seats.length) {
			for (int col = 0; col < seats.length; col++) {
				if (seatsGrid.get(col)[1] > row) {
					for (int seat = 0; seat < seats[col][row].length && !passengersQueue.isEmpty(); seat++) {
						// Window Seat in first Column
						if (col == 0 && seat == 0) {
							seats[col][row][seat] = passengersQueue.remove().toString();
						} 
						// Window Seat in last Column
						else if (col == seats.length - 1 && seat == seats[col][row].length - 1) {
							seats[col][row][seat] = passengersQueue.remove().toString();
						}
					}
				}
			}
			row++;
		}
	}

	private static void allocateCenterSeats(String[][][] seats, List<Integer[]> seatsGrid) {
		int row = 0;
		System.out.println();
		while (!passengersQueue.isEmpty() && row < seats.length) {
			for (int col = 0; col < seats.length; col++) {
				if (seatsGrid.get(col)[1] > row) {
					for (int seat = 0; seat < seats[col][row].length && !passengersQueue.isEmpty(); seat++) {
						//All Empty Seats will be filled with the passengers left in Queue
						if (seats[col][row][seat] == "E") {
							seats[col][row][seat] = passengersQueue.remove().toString();
						}
					}
				}
			}
			row++;
		}
	}

	private static void printSeats(String[][][] seats, List<Integer[]> seatsGrid) {
		for (int row = 0; row < seats.length; row++) {
			for (int col = 0; col < seats.length; col++) {
				//To check a row exists in that layout grid
				if (seatsGrid.get(col)[1] > row) {
					for (int seat = 0; seat < seats[col][row].length; seat++) {
						System.out.print("|");
						System.out.print(seats[col][row][seat] == "E" ? "--" : seats[col][row][seat]);
						System.out.print("|"); 
					}
				}
				// If a row doesn't exist, using blank space to fill it
				else {
					int noofColsinThatGrid = seatsGrid.get(col)[0];
					String fillers = new String(new char[noofColsinThatGrid]).replace("\0", "   ");
					System.out.print(fillers);
				}
				System.out.print("   ");
			}
			System.out.println("");
		}
	}
	
	private static void printAsCSV(String[][][] seats, List<Integer[]> seatsGrid, String FileName) {
		try {
			 File file = new File(FileName);
	            // if file doesnt exists, then create it
	            if (!file.exists()) {
	                file.createNewFile();
	            }
	        FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			StringBuilder builder = new StringBuilder();
			for (int row = 0; row < seats.length; row++) {
				for (int col = 0; col < seats.length; col++) {
					if (seatsGrid.get(col)[1] > row) {
						for (int seat = 0; seat < seats[col][row].length; seat++) {
							if(seats[col][row][seat].equalsIgnoreCase("E")){
								builder.append(",");
							} else {
								builder.append("Passenger");
								builder.append(seats[col][row][seat]);
								builder.append(",");
							}
						}
					} else {
						for (int i = 0; i < seatsGrid.get(col)[0]; i++) {
						builder.append(",");
						}
					}
					builder.append(",");
					builder.append(",");
				}
				builder.append("\n");
			}
			writer.write(builder.toString());
			writer.close();
			System.out.println("Seating Arrangement Printed in File : " + FileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
