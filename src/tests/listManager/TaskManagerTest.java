package listManager;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
	 String project;
	 String description;
	 Date date;
	 boolean status;
	
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
		tmWithThreeTasks.addProjectTasks("project3", "desc3", sf.parse("2018-11-11"), true);
		
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAddProjectTasksNoTasks() throws ParseException {
		
		assertTrue(tmWithNoTasks.addProjectTasks("project1", "desc1", sf.parse("2018-10-10"), false));
	}
	
	@Test
	public void testAddProjectTasksOneTasks() throws ParseException {
		assertTrue(tmWithOneTask.addProjectTasks("project5", "desc5", sf.parse("2018-10-23"), false));
		
		
	}

	@Test
	public void testAddProjectTasksThreeTasks() throws ParseException {
		assertTrue(tmWithThreeTasks.addProjectTasks("project6", "desc6", sf.parse("2019-11-11"), false));	
	}

	
	@Test
	public void getTaskByIdWithNoTask( ) {
		assertNull(tmWithNoTasks.getTaskById(1));
	}
	
	@Test
	public void getTaskByIdWithOneTask() {
		assertNotNull(tmWithOneTask.getTaskById(1));
	}
	
	@Test
	public void getTaskByIdWithThreeTasks( ) {
		assertNotNull(tmWithThreeTasks.getTaskById(2));	
	}
	

	@Test
	public void deleteTaskById() {
		
	}

	@Test
	public void testViewAllProjects() {
		
	}

	@Test
	public void testViewAllTasks() {
		
	}

}
