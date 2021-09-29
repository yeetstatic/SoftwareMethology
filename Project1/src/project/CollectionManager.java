package project;
import java.util.Scanner;

/**
 * Gets what the user inputs and calls the correct methods based on the commands.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class CollectionManager {
	/**
	 * The Collection object that will be used by the caller method.
	 */
	Collection obj=new Collection();
	/**
	 * The method that is in charge of dealing with user input and displaying error messages.
	 * @param input the input line of user
	 * @return true to keep program running, false if user entered "Q" to stop program
	 */
	public boolean caller(String input){
		if(input.equals("Q")) {
			return false;
		}
		else if(input.length()==0) {
		}
		else if(input.charAt(0)=='A'&&input.length()>1&&input.charAt(1)==',') {
			adder(input);
		}
		else if(input.charAt(0)=='D'&&input.length()>1&&input.charAt(1)==',') {
			remover(input);
		}
		else if(input.charAt(0)=='L'&&input.length()>1&&input.charAt(1)==',') {
			lender(input);
		}
		else if(input.charAt(0)=='R'&&input.length()>1&&input.charAt(1)==',') {
			returner(input);
		}
		else if(input.charAt(0)=='P') {
			printer(input);
		}
		else {
			System.out.println("Invalid command!");
		}
		return true;
	}
	
	/**
	 * The method that handles calling add function
	 * @param input The input line from user
	 */
	public void adder(String input) {
		Album adding=new Album(input.substring(2));
		boolean valid = adding.isValid();
		if(valid != true) {
			System.out.println("Invalid Date!");
			
		}
		else {
			boolean result=obj.add(adding);
			if(result) {
				System.out.println(adding+" >> added.");
			}
			else {
				System.out.println(adding+" >> is already in the collection.");
			}
		}
	}
	
	/**
	 * The method that handles calling remove function
	 * @param input The input line from user
	 */
	public void remover(String input) {
		Album removing=new Album(input.substring(2));
		boolean result=obj.remove(removing);
		if(result) {
			System.out.println(removing.basicString()+" >> deleted.");
		}
		else {
			System.out.println(removing.basicString()+" >> is not in the collection.");
		}
	}
	
	/**
	 * The method that handles calling lendingOut function
	 * @param input The input line from user
	 */
	public void lender(String input) {
		Album lending=new Album(input.substring(2));
		boolean result=obj.lendingOut(lending);
		if(result) {
			System.out.println(lending.basicString()+" >> lending out and set to not available.");
		}
		else {
			System.out.println(lending.basicString()+" >> is not available.");
		}		
	}
	
	/**
	 * The method that handles calling returnAlbum function
	 * @param input The input line from user
	 */
	public void returner(String input) {
		Album returning=new Album(input.substring(2));
		boolean result=obj.returnAlbum(returning);
		if(result) {
			System.out.println(returning.basicString()+" >> returning and set to available.");
		}
		else {
			System.out.println(returning.basicString()+" >> return cannot be completed.");
		}
	}
	
	/**
	 * The method that handles calling printing functions
	 * @param input The input line from user
	 */
	public void printer(String input) {
		if(input.length()>1&&input.charAt(1)=='D') {
			obj.printByReleaseDate();
		}
		else if(input.length()>1&&input.charAt(1)=='G') {
			obj.printByGenre();
		}
		else if(input.length()>1) {
			System.out.println("Invalid command!");
		}
		else {
			obj.print();
		}
	}
	
	/**
	 * The method that will run the CollectionManger class.
	 */
	public void run() {
		System.out.println("Collection Manager starts running.");
		boolean running=true;
		Scanner reader=new Scanner(System.in);
		String input;
		while(running) {
			input=reader.nextLine();
			running=caller(input);
		}
		reader.close();
		System.out.println("Collection Manager terminated.");
	}

}
