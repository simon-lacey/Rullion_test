package com.rullion.task.test;

import com.rullion.task.example.RullionScreenTask;
import com.rullion.task.example.TaskDescription;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.junit.Assert.*;

public class RullionScreenTaskTest {
	private RullionScreenTask executer;
	private ScheduledExecutorService ses;


	@Before
	public void setUp() throws Exception {
		ses = Executors.newSingleThreadScheduledExecutor();
		executer = new RullionScreenTask();
	}

	@Test
	public void testRun() throws InterruptedException {

		Runnable task = new Runnable() {
			@Override
			public void run() {
				assertNotNull(executer.testProcessResult(TaskDescription.getTaskDescription(1)));
				assertEquals("[]", executer.testProcessResult(TaskDescription.getTaskDescription(1)));

				assertNotNull(executer.testProcessResult(TaskDescription.getTaskDescription(2)));
				assertEquals("[a,b]", executer.testProcessResult(TaskDescription.getTaskDescription(2)));

				assertNotNull(executer.testProcessResult(TaskDescription.getTaskDescription(3)));
				assertEquals("[b,a]", executer.testProcessResult(TaskDescription.getTaskDescription(3)));

				assertNotNull(executer.testProcessResult(TaskDescription.getTaskDescription(4)));
				assertEquals("[b,a,d,c]", executer.testProcessResult(TaskDescription.getTaskDescription(4)));

				assertNotNull(executer.testProcessResult(TaskDescription.getTaskDescription(5)));
				assertEquals("[c,b.a]", executer.testProcessResult(TaskDescription.getTaskDescription(5)));

				assertNotNull(executer.testProcessResult(TaskDescription.getTaskDescription(6)));
				assertEquals("[Error - this is a cyclic dependency]",
						executer.testProcessResult(TaskDescription.getTaskDescription(16)));


			}
		};

	}

	@After
	public void close() {
		ses.shutdown();
	}

}
