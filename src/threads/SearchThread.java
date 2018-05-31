package threads;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;

/**
 * Class to represent a SearchThread that extends Thread. 
 * A InsertThread have a list of integers, a item to be added in the list and
 * a lock used for it to be blocked by a RemoveThread.
 * 
 * @author Erick O. Silva, Luis E. A. Silva
 * @version 2018.05.28
 */

public class SearchThread extends Thread 
{
	// A list of integers
	private LinkedList <Integer> list;
	
	// The item to be searched in the list
	private int item;
	
	// Lock used for it to be blocked by a RemoveThread
	private final Lock removeLock;
	
	/**
	 * Contructs a SearchThread.
	 * 
	 * @param list LinkedList <Integer> a list of integers
	 * @param item int to be searched in the list
	 * @param removeLock Lock used for it to be blocked by a RemoveThread
	 */
	public SearchThread(LinkedList <Integer> list, int item, Lock removeLock)
	{
		this.list = list;
		this.item = item;
		this.removeLock = removeLock;
	}
	
	@Override
	public void run() 
	{
		/* Lock will allow you to execute threads with insert function and search in parallel.
		 * But if a remove thread starts executing, the search and add functions threads will
		 * be blocked until the execution of that thread is finished.*/
		removeLock.lock();
		try 
		{
			System.out.println("--Starting number search--");
			if(this.list.contains(this.item)) 
			{
				System.out.println("I found the number " + this.item);
			}
			else 
			{
				System.out.println("I didn't found the number " + this.item);
			}
			System.out.println("--Ending number search--");
		}
		finally 
		{
			removeLock.unlock();
		}
		
    }

}
