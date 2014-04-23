package com.java.zombie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Zombie {

	public static class zombie {
		int x;
		int y;
		long M;

		zombie() {
			x = 0;
			y = 0;
			M = 0;
		}
	}

	public static int max(int a, int b) {
		return a >= b ? a : b;
	}

	public static int min(int a, int b) {
		return a <= b ? a : b;
	}

	public static void swap(int a, int b) {
		int temp = a;
		a = b;
		b = temp;
	}

	public static double calculateMindistance(int Sx, int Sy, int Dx, int Dy) {
		return max(Math.abs(Sx - Dx), Math.abs(Sy - Dy));
	}

	public static boolean validateInput(zombie[] Z) {
		for (int i = 0; i < Z.length; i++) {
			if (Z[i].x < -1000 || Z[i].x > 1000 || Z[i].y < -1000
					|| Z[i].y > 1000 || Z[i].M < 0 || Z[i].M > 100000000){
				System.out.println(Z[i].x+" "+Z[i].y+" "+Z[i].M);
				return false;
			}
		}
		return true;
	}

	public static int maxZombies(zombie[] Z) {
		int result = 0, temp = 0;
		int Sx, Sy, Dx, Dy;
		int t1 = 0, t2 = 0;
		long time = 0;

		for (int i = 0; i < Z.length; i++) {
			temp = 0;

			/*
			 * Select a zombie location from origin and calculate number of
			 * zombies location that could be reached if this is chosen first
			 * Select zombie starting location that gives the maximum score
			 */
			Sx = 0;
			Sy = 0;
			Dx = Z[i].x;
			Dy = Z[i].y;

			/*
			 * Calculate distance to reach a point from origin and it takes 100d
			 * ms time to reach there
			 */
			t1 = (int) calculateMindistance(Sx, Sy, Dx, Dy);
			t1 *= 100;
			time = t1;

			/*
			 * If zombie location can be reached within the time it disappears
			 */
			if (time < Z[i].M + 1000) {
				temp++;

				for (int j = 0; j < Z.length; j++) {
					if (j != i) {

						/*
						 * Calculate distance from current zombie location
						 */
						t2 = (int) calculateMindistance(Dx, Dy, Z[j].x, Z[j].y);
						t2 = t2 * 100;

						/* If it is reacheable before it disappears */
						if (t2 <= Z[j].M + 1000 - time) {

							/*
							 * if zombie location is very near than roam around
							 * until smasher recharges otherwise while on the
							 * way smasher recharges
							 */
							if (t2 <= 750)
								time += 750;
							else
								time += t2;
							temp++;
						}
					} else
						continue;
				}
			}
			/* the optimal solution is the one with highest score */
			if (temp >= result) {
				result = temp;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		String curDir = System.getProperty("user.dir");
		final File textFile = new File(curDir + "/sample.txt");
		final File outputFile = new File(curDir + "/Output.txt");
		BufferedReader in = null;
		BufferedWriter out = null;
		// Iterate through each line of the file
		String currentLine;
		int firstLine = 0;
		int numOfTestCases = 0;
		int numOfZombies = 0;
		zombie Z[] = new zombie[100];
		int index = 0;
		int count = 1;

		try {
			in = new BufferedReader(new FileReader(textFile));
			out = new BufferedWriter(new FileWriter(outputFile));

			while ((currentLine = in.readLine()) != null) {
				firstLine++;
				if (firstLine == 1) {
					numOfTestCases = Integer.parseInt(currentLine);
					if (numOfTestCases < 1 || numOfTestCases > 100) {
						System.out.println("Invalid Input");
						break;
					}
				} else {
					if (numOfZombies == 0) {
						if (Z != null && index != 0) {
							if (validateInput(Z)) {
								out.write("Case #" + count + ": "
										+ maxZombies(Z) + "\n");
							}
							count++;
						}
						numOfZombies = Integer.parseInt(currentLine);
						index = numOfZombies;
						Z = new zombie[numOfZombies];
					} else {
						String[] tokens = currentLine.split("\\s+");
						Z[index - numOfZombies] = new zombie();
						Z[index - numOfZombies].x = Integer.parseInt(tokens[0]);
						Z[index - numOfZombies].y = Integer.parseInt(tokens[1]);
						Z[index - numOfZombies].M = Long.parseLong(tokens[2]);
						numOfZombies--;
					}
				}
			}
			if (Z != null)
				out.write("Case #" + count + ": " + maxZombies(Z) + "\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
