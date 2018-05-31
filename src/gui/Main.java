package gui;
import java.util.Scanner;

import datastructure.ConcurrentLinkedList;

/**
 * Main class to execute the Concurrent Linked List project
 * 
 * @author Erick O. Silva, Luis E. A. Silva
 * @version 2018.05.28
 */

public class Main {

	public static void main(String[] args) {
		ConcurrentLinkedList cll = new ConcurrentLinkedList();  // Instantiates the Concurrent Linked List
		String option = "";
		int number = 2;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the concurrent linked list test program!!!");
		while(true) {
			try {
				System.out.println("What do you want to do?");
				System.out.println("Type s to search for numbers in the list.");
				System.out.println("Type r to remove numbers from the list.");
				System.out.println("Type p to put numbers from the list.");
				System.out.println("Type q to exit the program.");		
				option = scanner.nextLine();
				if(option.toLowerCase().equals("s")) {
					System.out.println("\nWhich number do you want to search the list for?");
					number = Integer.parseInt(scanner.nextLine());
					cll.search(number); // Search a entry number in the list
				}
				else if (option.toLowerCase().equals("p")) {
					System.out.println("\nWhich number do you want to add to the list?");
					number = Integer.parseInt(scanner.nextLine());
					cll.put(number); // Add a entry number in the list
				}
				else if(option.toLowerCase().equals("r")) {
					System.out.println("\nWhich number do you want to remove from the list?");
					number = Integer.parseInt(scanner.nextLine());
					cll.remove(number); // Remove a entry number in the list
				}
				else if(option.toLowerCase().equals("q")) {
					System.out.println("\nThanks for using our program and see you next time!");
					break; // Exit the program
				}
				else {
					System.out.println("\nI did not understand what you wrote.");
				}
				Thread.sleep(500);
				System.out.println("The elements of your current list are");
				System.out.println(cll.toString() + "\n");
			} catch (InterruptedException ex) {
				System.out.println(ex);
			}
		}
	}
}
