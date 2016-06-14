package de.roth;

import junit.framework.TestCase;

public class QueueTest extends TestCase 
{
	private Queue testQueue;
	
	public void testEntfernenAusleererQueue()//Entfernen aus leererQueue
	{ 
		this.testQueue = new Queue(3);
		
		try
		{
			testQueue.dequeue();
			fail("Erwartete Exception wurde nicht geworfen");
		}
		catch(Exception e)
		{

		}
	}

	public void testConstructor()
	{
		try
		{
			this.testQueue = new Queue(-1);
			fail("Erwartete Exception wurde nicht geworfen");
		}
		catch(Exception e)
		{
			
		}
	}

	public void testEnqueue()
	{
		this.testQueue = new Queue(6);
		testQueue.enqueue(2);
		assertEquals(testQueue.dequeue(),2);
	}

	public void testDequeue()
	{
		this.testQueue = new Queue(4);
		this.testQueue.enqueue(5);
		this.testQueue.enqueue(6);
		int sollwert = 5;
		assertEquals(sollwert, this.testQueue.dequeue());
	}

}
