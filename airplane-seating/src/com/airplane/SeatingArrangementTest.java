package com.airplane;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class SeatingArrangementTest {

	@Test
	public void AllocateSeatsTest1() {

		List<Integer[]> seatsGrid = new ArrayList<>();
		Integer[] seatGrid1 = { 3, 2 };
		Integer[] seatGrid2 = { 4, 3 };
		Integer[] seatGrid3 = { 2, 3 };
		Integer[] seatGrid4 = { 3, 4 };
		seatsGrid.add(seatGrid1);
		seatsGrid.add(seatGrid2);
		seatsGrid.add(seatGrid3);
		seatsGrid.add(seatGrid4);

		SeatingArrangement obj = new SeatingArrangement();
		String[][][] seats = obj.allocateSeats(seatsGrid, 30);
		int passengers = 30;
		
		for (int i = 1; i <= passengers; i++) {
			SeatingArrangement.passengersQueue.add(i);
		}

		String[][][] Allocatedseats = { 
				{ { "19", "25", "1" }, { "21", "29", "7" } },
				{ { "2", "26", "27", "3" }, { "8", "30", "E", "9" }, { "13", "E", "E", "14"} },
				{ { "4", "5" }, { "10", "11" }, { "15", "16" } },
				{ { "6", "28", "20" }, { "12", "E", "22"}, { "17", "E", "23"}, { "18", "E", "24"} },
				};
		assertArrayEquals(seats, Allocatedseats);
		assertArrayEquals(seats, Allocatedseats);
	}
	
	@Test
	public void AllocateSeatsTest2() {
		List<Integer[]> seatsGrid = new ArrayList<>();
		Integer[] seatGrid1 = { 3, 2 };
		Integer[] seatGrid2 = { 2, 3 };
		seatsGrid.add(seatGrid1);
		seatsGrid.add(seatGrid2);

		SeatingArrangement obj = new SeatingArrangement();
		String[][][] seats = obj.allocateSeats(seatsGrid, 30);
		int passengers = 30;
		
		for (int i = 1; i <= passengers; i++) {
			SeatingArrangement.passengersQueue.add(i);
		}

		String[][][] Allocatedseats = {{{"6", "11", "1"}, {"8", "12", "3"}}, {{"2", "7"}, {"4", "9"}, {"5", "10"}}};
		assertArrayEquals(seats, Allocatedseats);
		assertArrayEquals(seats, Allocatedseats);
	}
	
}
