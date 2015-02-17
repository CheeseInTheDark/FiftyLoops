package net.jmlproductions.fiftyloops;

import static net.jmlproductions.testing.answers.EndOfThreadTestAnswer.stopThread;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LoopingThreadTest
{

	@Mock
	private ThreadFunction function;
	
	private LoopingThread underTest;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		
		underTest = new LoopingThread(function);
	}
	
	@Test
	public void shouldExecuteFunction() throws InterruptedException
	{
		doAnswer(stopThread(underTest)).when(function).execute();
		
		underTest.start();
		underTest.waitForDeath();
		
		verify(function).execute();
	}

	@Test
	public void shouldExecuteFunctionTwice() throws InterruptedException
	{
		doNothing().doAnswer(stopThread(underTest)).when(function).execute();
		
		underTest.start();
		underTest.waitForDeath();
		
		verify(function, times(2)).execute();
	}
}
