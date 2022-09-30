package Project3;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * This class contains the methods required to perform the necessary actions to
 * play the game.
 *
 * @author Ngoc Tran, Demetria Johansen CSCI235
 */

public class CandyGame {
	private Scanner scan;
	private boolean isOver;
	private int[] candyForEachStudent;

	/**
	 * The constructor of the class. The number of students is asked and an array
	 * representing students is created in the constructor.
	 * @param lowerLimit The lower limit
	 * @param upperLimit The upper limit
	 */

	public CandyGame(int lowerLimit, int upperLimit) {
		int studentNumbers = getNumbOfStudents(lowerLimit, upperLimit);
		candyForEachStudent = new int[studentNumbers];

	}

	/**
	 * This method helps to get an integer from the user in the range from a lower
	 * limit to an upper limit inclusive. If the user enters an invalid input, show
	 * a notification and ask for another input.
	 * 
	 * @param lowerLimit The lower limit
	 * @param upperLimit The upper limit
	 * @return an integer which is the number of students playing the game
	 */
	public int getNumbOfStudents(int lowerLimit, int upperLimit) {

		System.out.println("Getting the number of students.");
		scan = new Scanner(System.in);
		boolean success = false;
		System.out.println("Enter an integer in [" + lowerLimit + ", " + upperLimit + "] inclusive:");
		int numb;
		while (!success) {
			try {
				numb = scan.nextInt();
				if (numb >= lowerLimit && numb <= upperLimit) {
					success = true;
					return numb;
				} else {
					System.out.println("Must be in [" + lowerLimit + ", " + upperLimit + "] inclusive! Re-enter: ");

				}
			} catch (Exception e) {
				scan.next();
				System.out.println(
						"Must be an INTEGER in [" + lowerLimit + ", " + upperLimit + "] inclusive! Re-enter: ");
			}

		}

		return 0;
	}

	/**
	 * This method gets and returns an even integer in the range from an even (i.e.
	 * not odd) lower limit to an even upper limit inclusive. If the user enters an
	 * invalid input, show a notification and ask for another input.
	 * 
	 * @param evenLowerLimit The even lower limit
	 * @param evenUpperLimit The even upper limit
	 * @return an even integer in the range from an even (i.e. not odd) lower limit
	 *         to an even upper limit inclusive
	 */
	public int getEvenInt(int evenLowerLimit, int evenUpperLimit) {

		scan = new Scanner(System.in);

		System.out.println("Enter an even integer in [" + evenLowerLimit + ", " + evenUpperLimit + "] inclusive:");
		int numb;
		boolean success = false;
		while (!success) {
			try {
				numb = scan.nextInt();
				int r = numb % 2;
				if (numb >= evenLowerLimit && numb <= evenUpperLimit && r == 0) {
					success = true;
					return numb;
				} else {
					System.out.println("Must be EVEN and in [" + evenLowerLimit + ", " + evenUpperLimit
							+ "] inclusive! Re-enter: ");
				}
			} catch (Exception e) {
				scan.next();
				System.out.println("Must be an EVEN INTEGER and in [" + evenLowerLimit + ", " + evenUpperLimit
						+ "] inclusive! Re-enter: ");
			}
		}
		return 0;
	}

	/**
	 * This method will distribute a number of pieces of candy to an array of
	 * integers (representing the students). The number given to each student must
	 * be random, even, and between two specified even limits inclusive.
	 * 
	 * @param evenLowerLimit The even lower limit
	 * @param evenUpperLimit The even upper limit
	 */
	public void distributeCandy(int evenLowerLimit, int evenUpperLimit) {
		// use Random and for loop to add random even positive integers to the array.
		Random rand = new Random();
		for (int i = 0; i < candyForEachStudent.length; i++) {
			int temp = rand.nextInt(evenUpperLimit - evenLowerLimit + 1) + evenLowerLimit;
			int r = temp % 2;
			if (r != 0) {
				temp += 1;
			}
			candyForEachStudent[i] = temp;
		}
	}

//-----------------------------------------------------------------------------------------------------------------------------------------//
	/**
	 * Prints out the simple and long versions of the game.
	 */
	public void printIntegers() {
		// print the array of integers on one line
		scan = new Scanner(System.in);

		System.out.println("The original deal is: ");
		for (int i = 0; i < candyForEachStudent.length; i++) {
			System.out.printf("%4d", candyForEachStudent[i]);
		}

		System.out.println("\n\r");
		System.out.println("We are ready to play the game. ");
		System.out.println("Do you want to print the status after each move? (1 for yes, 0 for no). ");
		System.out.println("Enter an integer in [0, 1] inclusive:" + "\n\r");

		boolean success = false;
		int checkLoop = 3;
		while (!success) {

			try {
				checkLoop = scan.nextInt();
				if (checkLoop != 1 && checkLoop != 0) {
					throw new InvalidException();
				} else if (checkLoop == 1) {
					// throw new InputMismatchException();
					success = true;

				}
				success = true;
			} catch (InputMismatchException ex) {
				// scan.nextInt();
				System.out.println("Must be a number! Must enter 1 or 0. Re-enter: "
						+ " Do you want to print the status after each move? (1 for yes, 0 for no).");
				scan.next();
				
			} catch (InvalidException ex) {
				// scan.nextInt();
				System.out.println("Unknown number. Must enter 1 or 0!\"\r\n"
						+ " Do you want to print the status after each move? (1 for yes, 0 for no).");
				// InvalidException.getMessage();
			}
		}

		while (!isGameOver()) {
			// if (checkLoop == 1) {
//				for (int i = 0; i < candyForEachStudent.length; i++) {
//					System.out.printf("%4d", candyForEachStudent[i]);
//				}
			for (int i = 0; i < candyForEachStudent.length; i++) {

				if (checkLoop == 1) {
					System.out.printf("%4d", candyForEachStudent[i]);
				}
			}
			passCandy();
			if (checkLoop == 1) {
				System.out.println("");
			}
		}
		for (int i = 0; i < candyForEachStudent.length; i++) {
			System.out.printf("%4d", candyForEachStudent[i]);
		}
	}


	/**
	 * Passes the candy to students (1/2 of each student's is passed to the other).
	 * If there is an odd number then 1 is added
	 */
	public void passCandy() {
		// pass the candy as described in the game
		// Portions (specifically half) of each element in the array are getting
		// added to the element on the right, except for the last element, a portion of
		// which gets
		// added to the first.
		int[] temporary = new int[candyForEachStudent.length];

		// All 1/2 numbs
		for (int i = 0; i < candyForEachStudent.length; i++) {
			temporary[i] = candyForEachStudent[i] / 2;
		}
		// CForES = 1/2 numbs
		for (int i = 0; i < candyForEachStudent.length; i++) {
			candyForEachStudent[i] = candyForEachStudent[i] / 2;
		}
		// Adds 1/2 numbs to next index
		for (int i = 0; i < candyForEachStudent.length; i++) {
			if (i == candyForEachStudent.length - 1) {
				candyForEachStudent[0] += temporary[candyForEachStudent.length - 1];

				if (candyForEachStudent[0] % 2 != 0) {
					candyForEachStudent[0] += 1;
				}
			} else {
				candyForEachStudent[i + 1] += temporary[i];

				if (candyForEachStudent[i + 1] % 2 != 0) {
					candyForEachStudent[i + 1] += 1;
				}

			}

		}

	} 

	/**
	 * Tests if the game is over.
	 * 
	 * @return isOver (whether the game is over or not)
	 */
	public boolean isGameOver() {
		// test whether of not the game is over
		// if all the values are the same --> isOver = true, otherwise isOver = false
		int numbCheck = candyForEachStudent[0];

		for (int i = 0; i < candyForEachStudent.length; i++) {

			if (candyForEachStudent[i] == numbCheck) {
				isOver = true;
				// printIntegers();
			} else {
				isOver = false;
				return false;
				// System.out.println("Final Result");
				// printIntegers();
			}
		}
		return isOver;

	}
}
