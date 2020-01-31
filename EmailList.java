import java.util.List;
import java.util.LinkedList;

/***********************************************************************************
Class:  EmailAddress
Purpose: Add, delete, display and return size of entry in the list, show entries in the directory and compare with EmailList.
Author:   Madhav Sachdeva
Course:   CST8130 - Data Structures
Data members:  name : String - hold the value of a valid list name
               address : LinkedList<EmailAddress>- LinkedList of EmailAddress objects
Methods: EmailList(String name) - Parameterized constructor.
         addEmail(EmailAddress email) - Adds an entry to the list.
         deleteEmail(int numIndex) - Removes the entry at the specified position in the list.
         displayList() - Displays all entries in the list.
 		 bulletList() - Shows all entries in the directory for selection.
         isEqual(String name):boolean -  Compares for equality of the EmailList with name.
         size():int - Returns the number of entries in the list.
         nameIsGreaterThan(EmailList email):boolean - Compares the name of the list
*************************************************************************************/

public class EmailList {

	private String name = null;
	private LinkedList<EmailAddress> address = null;

	
	public EmailList(String name) {
		this.name = name;
		this.address = new LinkedList<EmailAddress>();
	}

	
	public void addEmail(EmailAddress email) {
		address.add(email);
	}

	
	public void deleteEmail(int numIndex) {
		address.remove(numIndex);
	}

	
	public void displayList() {
		
		
		System.out.print(name + ":[");
		for (int i = 0; i < address.size(); i++) {
			if (i > 0) {
				System.out.println(", ");
			}	
			System.out.print(address.get(i));
		}
		System.out.println("]");
		System.out.println();
	}

	
	public void bulletList() {
		System.out.println(name);
		
		for (int i = 0; i < address.size(); i++) {
			System.out.println(i + " " + address.get(i));
		}
		
	}

	
	public boolean isEqual(String name) {
		
		if (this.name.equals(name)) {
			return true;
		} 
		else {
			return false;
		}
	}

	
	public int size() {
		return address.size();
	}
	
	public boolean nameIsGreaterThan(EmailList email) {
		
		if((this.name.compareToIgnoreCase(email.name))>0) {
			return true;

		}
		else {
			return false;
		}
		
		
	}
	
	
	
}