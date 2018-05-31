package threads;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;

/**
 * Class to represent a RemoveThread that extends Thread. 
 * A InsertThread have a list of integers, a item to be added in the list and
 * a lock used to lock other InsertThread, RemoveThread and Search.
 * 
 * @author Erick O. Silva, Luis E. A. Silva
 * @version 2018.05.28
 */

public class RemoveThread extends Thread 
{
	// A list of integers
	private LinkedList <Integer> list;
	
	// The item to be removed in the list
	private int item;
	
	// Lock used to lock other InsertThread, RemoveThread and SearchThread
	private final Lock removeLock;
	
	/**
	 * Contructs a RemoveThread.
	 * 
	 * @param list LinkedList <Integer> a list of integers
	 * @param item int to be removed in the list
	 * @param removeLock Lock used to lock other InsertThread, RemoveThread and SearchThread
	 */
	public RemoveThread(LinkedList <Integer> list, int item, Lock removeLock) 
	{
		this.list = list;
		this.item = item;
		this.removeLock = removeLock;
	}
	
	@Override
	public void run() 
	{	
		/* Lock will block all future executions of the insert, remove and search functions
		until it finishes its execution and unlock the Lock. */
		removeLock.lock();
		try 
		{
			System.out.println("--Starting number remove--");
			if(this.list.remove(new Integer(this.item))) 
			{
				System.out.println("I remove the number " + this.item);
			}
			else 
			{
				System.out.println("I didn't remove and found the number " + this.item);
			}
			System.out.println("--Ending number remove--");
		}
		finally 
		{
			removeLock.unlock();
		}
	}
}
