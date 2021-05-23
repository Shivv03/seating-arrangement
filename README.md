# seating-arrangement
This project gives a Java Algorithm to allocate seats for passengers in an Airplane. 


This helps	seat	audiences	in	a	flight	based	on	the	following	input	and	rules.
Rules	for	seating	
• Always	seat	passengers	starting	from	the	front	row	to	back,	
starting	from	the	left	to	the	right	
• Fill	aisle	seats	first	followed	by	window	seats	followed	by	center	
seats	(any	order	in	center	seats)

Logic Used:
1) Initially all the input layouts are retrieved.
2) Based on this airplane seats  are constructed and initialised as a 3D array with E in each value(Empty Seats)
3) First 3D Matrix is iterated row by row, using combination of while and for loops.
	iterations are done, until the passenger queue is empty or aisle seats in all rows are full, followed by window and center seats.
