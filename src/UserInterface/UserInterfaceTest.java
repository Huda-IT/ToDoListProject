package UserInterface;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import listManager.Task;
import listManager.TaskManager;

class UserInterfaceTest {
	private HashMap<String, ArrayList<Task>> taskMap;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	Task task = new Task(0, null, null, null, false);
	TaskManager manager = new TaskManager();
	
	@Test
	void testEnterTaskValues() {
		UserInterface inter = new UserInterface(manager);
		
		
	}

	 void testProjectname() {
		String expected = "work";
		String actual = task.getProject();
		assertEquals(expected, actual);
	}
	
	 void testDescription() {
		String expected = "planning";
		String actual = task.getDescription();
		assertEquals(expected, actual);
	}
	
	 void testDate() {
		String expectedDate = "2-2-2018";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);

			Date date = null;
			try {
				date = dateFormat.parse(expectedDate);
				System.out.println(dateFormat.format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(" Error Formatting !");
				e.printStackTrace();
			}
		Date actual = task.getTaskDate();
		assertEquals(expectedDate, actual);
	}

}
