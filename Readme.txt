How to Run the project?
This project is created in eclipse.
Import this project in eclipse and run this project.

Files attached
--------------
Zombie.java - contains the source code for this problem
sample.txt  - contains the sample data
Readme.txt - this file

Implementation details
----------------------
1. Read input data from the sample data.
2. Calculate the optimal number of zombies that can be crushed in the following way
	a. Select a zombie location from origin and calculate number of zombies location that could be reached 
		if this is chosen first
	b. Select zombie starting location that gives the maximum score
	c. Calculate distance to reach a point from origin and it takes 100d ms time to reach there
	d. If zombie location can be reached before it disappears
			 Calculate distance from current zombie location
						 if zombie location is very near than roam around
						 until smasher recharges otherwise while on the way, smasher recharges
							 
	e. The optimal solution is the one with highest score 
3. Write data as required into "output.txt" file.


