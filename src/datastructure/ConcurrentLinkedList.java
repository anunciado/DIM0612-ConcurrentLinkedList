package datastructure;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import threads.InsertThread;
import threads.RemoveThread;
import threads.SearchThread;

/**
 * Class to represent a Concurrent Linked List
 * A Concurrent Linked List have a list of integers, a remove lock variable to block 
 * all functions while a remove function is running and a Insert lock variable to 
 * block all threads from the insert function while an insert function is running. 
 * For this program was used a ReadWriteLock because when a thread acquires a WriteLock,
 * no other thread can acquire the ReadLock nor the WriteLock of the same instance of 
 * ReentrantReadWriteLock, unless that thread releases the lock. However, multiple threads
 * can acquire the ReadLock at the same time. Therefore, the insert and search functions 
 * can be performed in parallel using ReadLock and the delete functions can be executed 
 * sequentially and blocking using WriteLock and the insert functions can be executed 
 * sequentially and blocking using another Lock.
 * 
 * @author Erick O. Silva, Luis E. A. Silva
 * @version 2018.05.28
 */

public class ConcurrentLinkedList 
{
	// A list of integers
	private LinkedList <Integer >list;
	
	// Remove lock variable
	private final ReadWriteLock removeLock;
	
	// Insert lock variable
	private final Lock insertLock;
	
	/**
	 * Constructs a ConcurrentLinkedList.
	 */
	public ConcurrentLinkedList() 
	{
		this.list = new LinkedList<Integer>(); // Init the list of integers
		this.removeLock = new ReentrantReadWriteLock(true); // Init the lock for remove function 
		this.insertLock = new ReentrantLock(true); // Init the lock for insert function 
	}
	
	/**
	 * @brief Search a number in the encapsulated list.
	 * 
	 * @param item int that will be searched to the encapsulated list.
	 */
	public void search(int item) 
	{
		/* The removeLock.readLock will be passed as parameter for the search and 
		 * addition function can be executed in parallel by different threads.
		 */
		Thread t = new SearchThread(list, item, removeLock.readLock());
		t.start();
	}
	
	/**
	 * @brief Put a number at the end of the encapsulated list.
	 * 
	 * @param item int that will be added at the end of the encapsulated list.
	 */
	public void put(int item)
	{	
		/* The removeLock.readLock will be passed as parameter for the search and 
		 * addition function can be executed in parallel by different threads.
		 * The insertLock will be passed as parameter for the addition functions 
		 * be executed sequentially between threads.
		 */
		Thread t = new InsertThread(list, item, removeLock.readLock(), insertLock);
		t.start();
	}
	
	/**
	 * @brief Remove a number in the encapsulated list.
	 * 
	 * @param item int that will be removed to the encapsulated list.
	 */
	public void remove(int item) 
	{
		/* The removeLock.writeLock will be passed as parameter so that the remove
		 * functions be executed sequentially between threads. In addition, the add
		 * and search functions will be blocked.
		 */
		Thread t = new RemoveThread(list, item, removeLock.writeLock());
		t.start();
	}
	
	/**
	 * @brief Show all items added to list in the encapsulated list.
	 * 
	 * @return String that represents all items added to list in the encapsulated list.
	 */
	public String toString() 
	{
		return list.toString();
	}

}
