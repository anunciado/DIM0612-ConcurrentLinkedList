package threads;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;

/**
 * Class to represent a InsertThread that extends Thread. 
 * A InsertThread have a list of integers, a item to be added in the list, 
 * a lock used for it to be blocked by a RemoveThread and a lock used to 
 * lock other InsertThread.
 * 
 * @author Erick O. Silva, Luis E. A. Silva
 * @version 2018.05.28
 */

public class InsertThread extends Thread 
{
	// A list of integers
	private LinkedList <Integer> list;
	
	// The item to be added in the list
	private int item;
	
	// Lock used for it to be blocked by a RemoveThread
	private final Lock removeLock;
	
	// Lock used to lock other InsertThread
	private final Lock insertLock;
	
	/**
	 * Contructs a InsertThread.
	 * 
	 * @param list LinkedList <Integer> a list of integers
	 * @param item int to be added in the list
	 * @param removeLock Lock used for it to be blocked by a RemoveThread
	 * @param insertLock Lock used to lock other InsertThread
	 */
	public InsertThread(LinkedList <Integer> list, int item, Lock removeLock, Lock insertLock) 
	{
		this.list = list;
		this.item = item;
		this.removeLock = removeLock;
		this.insertLock = insertLock;
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
			/* Lock will block all future executions of the insert functions
			until it finishes its execution and unlock the Lock. */
			insertLock.lock();
			try
			{
			System.out.println("--Starting number addition--");
			this.list.addLast(this.item);
			System.out.println("I added the number " + this.item);
			System.out.println("--Ending number addition--");
			}
			finally 
			{
				insertLock.unlock();
			}
		}
		finally 
		{
			removeLock.unlock();
		}
    }
}
