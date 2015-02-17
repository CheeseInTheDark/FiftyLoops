package net.jmlproductions.fiftyloops;

public class LoopingThread extends Thread
{

	private ThreadFunction function;
	
	public LoopingThread(ThreadFunction function)
	{
		this.function = function;
	}

	@Override
	public synchronized void run()
	{
	    do
	    {
	        function.execute();
	    } 
	    while (!interrupted());
	    
	    notify();
	}
	
    public synchronized void waitForDeath() throws InterruptedException
    {
        while (isAlive())
        {
            wait();
        }
    }
}
