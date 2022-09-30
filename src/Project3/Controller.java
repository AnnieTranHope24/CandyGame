package Project3;

/**
 * This is the main class of the program. It initiates an instance of the second
 * class (CandyGame) and uses that instance to call the methods in that class to
 * perform the required actions to play the game.
 * 
 * @author Ngoc Tran, Demetria Johansen CSCI235
 */
public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		play();

	}
/**
 * This method plays the whole game.
 */
	private static void play() {
		//step1+2: get the number of students from the user, the constructor of CandyGame
		//will immediately create an array of int.
		CandyGame candyGame = new CandyGame(15, 30);
		System.out.println();
		//step3+4: use the method getEvenInt() of CandyGame to 
		//ask the user to enter the lower and upper numbers of starting candy pieces
		System.out.println("Getting the lower number of starting candy pieces from 4 to 10.");
		int lowerNumber = candyGame.getEvenInt(4, 10);
		int temp = lowerNumber + 50;
		System.out.println("Getting the upper number of starting candy pieces.");
		System.out.println("Must be even and greater than " + lowerNumber
				+ " (the lower number) but less than or equal to\r\n" + +temp + " (the lower number plus 50).");
		int upperNumber = candyGame.getEvenInt(lowerNumber + 2, temp);
		//step5: distribute and print the candy pieces
		candyGame.distributeCandy(lowerNumber, upperNumber);
		candyGame.printIntegers();
	}

}
