import java.util.List;
import java.util.ArrayList;


/***********************************************************************************
Class:  EmailAddress
Purpose: Add, display and search EmailList in the directory.
Author:   Madhav Sachdeva
Course:   CST8130 - Data Structures
Data members:   dirList:ArrayList<EmailList> - list of all the directories 
Methods: addEmailList(EmailList email) - Adds an EmailList to the directory.
         getEmailList(String name):EmailList - Finds the specified EmailList for the given name.
         display() - Displays all EmailLists in the directory.
         search(EmailList email):int - Searches for the right spot in the ArrayList for a new Directory through binary search.
*************************************************************************************/


public class Directory {

	private ArrayList<EmailList> dirList = null;

	public Directory() {
		this.dirList = new ArrayList<EmailList>();
	}

	public void addEmailList(EmailList email) {
		
		if (dirList.isEmpty()) {
			dirList.add(0,email);
		} else {
			int index = search(email);
			dirList.add(index, email);
		}
	}

	public EmailList getEmailList(String name) {

		for (int i = 0; i < dirList.size(); i++) {

			if (dirList.get(i).isEqual(name)) {
				return dirList.get(i);
			}

		}
		return null;
	}

	public void display() {

		System.out.println("The email lists are: ");

		for (int i = 0; i < dirList.size(); i++) {
			dirList.get(i).displayList();
		}
         System.out.println();
		
	}

	public int search(EmailList email) {

		int low = 0;
		int high = dirList.size() - 1;
		int key = 0;

		if (dirList.get(low).nameIsGreaterThan(email)) {// if parameter is smaller than the
														// smallest
			key = low;

		} else if (!dirList.get(high).nameIsGreaterThan(email)) { // if parameter is higher
																	// than highest
			key = high + 1;

		} else {

			while (low < high) {// binary search

				int midpoint = (low + high) / 2;

				if (!dirList.get(midpoint).nameIsGreaterThan(email)) {

					low = midpoint + 1;
					key = (low + high) / 2;
				} else if (dirList.get(midpoint).nameIsGreaterThan(email)) {

					high = midpoint;
					key = (low + high) / 2;
				} else if (dirList.get(midpoint).equals(email)) {

					key = midpoint;
					break;

				}

			}

		}
		return key;
	}

}
