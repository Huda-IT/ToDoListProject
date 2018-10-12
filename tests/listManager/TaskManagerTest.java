package listManager;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TaskManagerTest {
	
	private TaskManager tmWithNoTasks;
	private TaskManager tmWithOneTask;
	private TaskManager tmWithThreeTasks;
	private SimpleDateFormat sf;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		sf = new SimpleDateFormat("dd-MM-yyyy");
		tmWithNoTasks = new TaskManager();
		tmWithOneTask = new TaskManager();
		tmWithOneTask.addProjectTasks("project1", "desc1", sf.parse("2018-10-10"), false);
		tmWithThreeTasks = new TaskManager();
		tmWithOneTask.addProjectTasks("project1", "desc1", sf.parse("2018-10-10"), false);
		tmWithThreeTasks.addProjectTasks("project2", "desc2", sf.parse("2018-10-11"), false);		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddProjectTasksNoTasks() throws ParseException {
		tmWithNoTasks.addProjectTasks("project1", "desc1", sf.parse("2018-10-10"), false);
		Task t = tmWithNoTasks.getTaskById(1);
		
		
	}

	@Test
	public void testAddProjectTasksThreeTasks() {
		
	}
	
	@Test
	public void testRemoveProject() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewAllProjects() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewAllTasks() {
		fail("Not yet implemented");
	}

}
