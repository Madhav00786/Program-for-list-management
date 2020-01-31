import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;




/***********************************************************************************
Class:  Assign3
Purpose: This class contains the main method and displays the menu.
Author:   Madhav Sachdeva
Course:   CST8130 - Data Structures
Data members:   input: Scanner  - Scanner object for keyboard entries
                directory: Directory
Methods: menu() -  Shows the menu, interacts with the user for menu selection, loops till they exit.     	       
         createList() - Prompts user to enter data for a new list. 
         displayAllLists() -  Displays all EmailLists in the directory. 
         displaySingleList() - Prompts user to enter the name of the EmailList and displays the list.
         addEmailToList() -  Prompts user to enter the name of the EmailList and adds an EmailAddress to the list.
         deleteEntryList() - Prompts user to enter the name of the EmailList and deletes the list.
         readFile() - Reads in EmailLists from a file.
         getEmailAddresses(EmailList emailList) - Getting email addresses from user.
         openFile(String filename):Scanner - Getting reference of file in Scanner object
*************************************************************************************/




public class Assign3 {
	private Scanner input = new Scanner(System.in);
	private Directory directory = new Directory();

	public static void main(String[] args) {
		Assign3 assign3Main = new Assign3();
		assign3Main.menu();
	}

	
	public void menu() {

		String choice = "0";

		while (!choice.equals("q")) {
			
			System.out.print(
					"Enter c to create a new list\n      p to display all lists\n      a to add an entry to a list\n      d to delete from a list\n      l to display a list\n      f to load lists from file\n      q to quit: ");

			

			choice = input.next().toLowerCase();

			switch (choice) {
			case "c":
				createList();
				break;

			case "p":
				displayAllLists();
				break;

			case "a":
				addEmailToList();
				break;

			case "d":
				deleteEntryFromList();
				break;

			case "l":
				displaySingleList();
				break;

			case "f":
				readFile();
				break;

			case "q":
				return;

			default:
				System.out.println("Invalid entry...");

			}

		}
	}

	
	private void createList() {

		String name = null;

		System.out.println("Enter the name of the list: ");
		name = input.next();

		EmailList emailList = directory.getEmailList(name);

		if (emailList == null) {
			emailList = new EmailList(name);

			getEmailAddresses(emailList);

			directory.addEmailList(emailList);

		} else {
			System.out.println("The EmailList with the name already exist.");
		}
	}


	private void displayAllLists() {
		directory.display();
	}


	private void displaySingleList() {


		String name = null;

		System.out.println("Enter the name of the list to display: ");

		name = input.next();

		EmailList emailList = directory.getEmailList(name);

		if (emailList != null) {
			emailList.displayList();
		} else {
			System.out.println("The EmailList does not exist.");
		}
	}

	
	private void addEmailToList() {

		String name = null;

		System.out.println("Enter the name of the list to add to: ");

		name = input.nextLine();

		EmailList emailList = directory.getEmailList(name);

		if (emailList != null) {

			EmailAddress emailAddress = new EmailAddress();
			emailAddress.addAddress(input, "y");
			emailList.addEmail(emailAddress);
			input.nextLine();

		} else {
			System.out.println("The EmailList does not exist.");
		}
	}

	
	private void deleteEntryFromList() {

		String name = null;
		System.out.println("Enter the name of the list to delete from: ");
		name = input.nextLine();

		EmailList emailList = directory.getEmailList(name);

		if (emailList != null) {
			emailList.bulletList();

			int num = -1;

			System.out.print("Enter entry number to delete: ");
			while (!input.hasNextInt()) {
				System.out.println("Please enter an integer");
			}
			num = input.nextInt();

			if (num >= 0 && num < emailList.size()) {
				emailList.deleteEmail(num);
			} else {
				System.out.println("The entry number is out of range.");
			}
		} else {
			System.out.println("The EmailList does not exist.");
		}
	}

	/**
	 * 
	 */
	private void readFile() {
		String fileName = null;

		System.out.print("Enter name of file to process: ");

		fileName = input.next();

		Scanner scanner = openFile(fileName);

		if (scanner != null) {
			int lines = 0;

			if (scanner.hasNextLine()) {
				if (scanner.hasNextInt()) {

					lines = scanner.nextInt();

					if (lines <= 0) {
						System.out.println("The total lines must be a positive integer: ");
						lines = 0;
						return;
					}

				} else {
					System.out.println("Cannot find the total lines ");
					return;
				}

			}

			int lineCount = 0;

			while (scanner.hasNextLine()) {
				lineCount++;

				scanner.nextLine();
				String name = scanner.next();

				int numEmailAddresses = 0;

				if (scanner.hasNextInt()) {
					numEmailAddresses = scanner.nextInt();

				}

				if (numEmailAddresses != 0) {

					EmailList emailList = new EmailList(name);

					for (int i = 0; i < numEmailAddresses; i++) {

						emailList.addEmail(new EmailAddress(scanner.next()));

					}
					directory.addEmailList(emailList);

					if (lineCount >= lines) {
						break;
					}
				}

			}
			
			 scanner.close();

			
		} else {
			System.out.println(fileName + " not found");
		}
	}

	

	private void getEmailAddresses(EmailList emailList) {
		EmailAddress emailAddress = null;

		String option = "";

		while (!option.equals("n")) {
			emailAddress = new EmailAddress();
			emailAddress.addAddress(input, "y");
			emailList.addEmail(emailAddress);

		
			System.out.print("Another? y/n: ");

			

			option = input.next().toLowerCase();

			if ((!option.equals("y")) && (!option.equals("n"))) {
				System.out.println("Invalid option, please try again.");
			}
		}
	}

	

	private Scanner openFile(String filename) {
		File file = new File(filename);

		if (file.exists()) {
			try {
				Scanner scanner = new Scanner(file);
				return scanner;
			} catch (FileNotFoundException e) {
				return null;
			}
		} else {
			return null;
		}
	}
}

