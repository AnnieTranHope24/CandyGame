package Project3;

public class InvalidException extends RuntimeException {
	
	@Override
	public String getMessage() {
		
		return "Unknown number. Must enter 1 or 0!"
				+ " Do you want to print the status after each move? (1 for yes, 0 for no).";
	}
}
