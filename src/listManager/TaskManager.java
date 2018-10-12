package listManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class TaskManager {
	private String filename = "SavedTasks/projectstor.txt";
	private HashMap<String, ArrayList<Task>> taskMap;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private int currentId;

	public TaskManager() {
		taskMap = new HashMap<String, ArrayList<Task>>();
		currentId = 0;
	}

	/**
	 * This method load file values into the Arraylist<Task> tasks.
	 * @param filename is the file that the method retrieve data from
	 * @return true if there is tasks in the it is saved in the ArrayList
	 * @return false if there is no tasks in the ArrayList
	 */
	public boolean loadFromFile(String filename) {
		Readfile readf = new Readfile();
		// TODO try catch for Readfile exception when there
		ArrayList<Task> tasks = readf.LoadTasks(filename);

		if (tasks == null)
			return false;
		for (Task task : tasks) {
			this.addProjectTasks(task.project, task.description, task.date, task.status);
		}
		return true;
	}

	/**
	 * This methods adds project name and task values into the HashMap (taskMap)
	 * @param projectname
	 * @param description
	 * @param date
	 * @param status
	 * @return false if addingproejct is not success
	 */
	public boolean addProjectTasks(String projectname, String description, Date date, boolean status) {

		// retrieves tasks with the project name
		ArrayList<Task> tasks = taskMap.get(projectname);
		Task newTask = new Task(projectname, description, date, status);

		if (tasks == null) {
			tasks = new ArrayList<Task>();
			this.currentId++;
			newTask.setId(this.currentId);
			tasks.add(newTask);

			taskMap.put(projectname, tasks);
			return true;
		} else if (!tasks.contains(newTask)) {

			currentId++;
			newTask.setId(this.currentId);
			tasks.add(newTask);
			taskMap.put(projectname, tasks);
			return true;
		}
		return false;
	}

	/**
	 * @param projectname
	 */
	public void removeProject(String projectname) {
		taskMap.remove(projectname);
		return;
	}

	// View all new entered Projects during runtime
	public void ViewAllProjects() {
		if (tasks == null)
			System.out.println();
		else {
			for (String key : taskMap.keySet())
				System.out.println(key + '\n');
		}
	}

	// View all the Tasks in selected project
	public void viewAllTasks() {
		ArrayList<Task> taskList = getAllTasks();
		viewAllTasks(taskList);
	}

	private void viewAllTasks(ArrayList<Task> taskList) {
		for (Task t : taskList) {
			System.out.println(t);
		}
	}

	private ArrayList<Task> getAllTasks() {
		ArrayList<Task> taskList = new ArrayList<>();
		System.out.printf("%-4s %-10s %-10s %-10s %-10s\n", "ID", "PROJECTNAME", "DESC", "DATE", "STATUS");
		for (Map.Entry<String, ArrayList<Task>> key : taskMap.entrySet()) {
			ArrayList<Task> tasks = key.getValue();
			for (int i = 0; i < tasks.size(); i++) {
				taskList.add(tasks.get(i));
			}
		}
		return taskList;
	}

	/**
	 * This method gets Task by its ID that is entered by the user
	 * @param wantedId is the id that want to get its Tasks
	 * @return Null if the HashMap is empty
	 * @return Task's values if the HashMap has values
	 */
	public Task getTaskById(int wantedId) {
		if (taskMap == null)
			return null;
		for (Map.Entry<String, ArrayList<Task>> entry : taskMap.entrySet()) {
			//System.out.println(entry.getKey() + " : ");
			tasks = entry.getValue();
			for (int i = 0; i < tasks.size(); i++) {
				Task t = tasks.get(i);
				if (t.getId() == wantedId) {
					return t;
				}
			}
		}
		return null;
	}

	/**
	 * This methods removes the Task that is chosen by its ID
	 * @param id entered by the user
	 */
	public void deleteTaskById(int id) {
		Task t = getTaskById(id);
		// check if the wanted id exist, if not the method will return null!
		if (t == null) {
			System.out.println(" The Task has null value!");
			return;
		}
		tasks.remove(t);
		System.out.println(" The Task has been removed!");
	}

	/**
	 * Editing Task values by entering new values from the screen to update them! If
	 * some values want to be unedited,just skip entering new values and it will
	 * take it as a null! If the value is null (no entered values), it will keep the
	 * original value!
	 * @param id is the ID for wanted editing value entered by the user
	 */
	public void editTaskById(int id) {

		Task t = getTaskById(id);
		if (t == null) {
			return;
		}

		System.out.println(" Editing on : " + t);

		// Editing the task values by entering new values for the updated fields
		Scanner read = new Scanner(System.in);

		System.out.println(" Add new Project Name: ");
		String newProjectname = read.nextLine();

		if (!newProjectname.isEmpty()) {
			t.setProject(newProjectname);
		}

		System.out.println("Add new Description: ");
		String description = read.nextLine();
		if (!description.isEmpty()) {
			t.setDescription(description);
		}

		System.out.println("Current date:" + t.getTaskDate().toString() + " Add new Date in this format: (dd-MM-yyyy): ");

		String date = read.nextLine();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		if (!date.isEmpty()) {
			try {
				t.setTaskDate(dateFormat.parse(date));
				System.out.println("New date: " + t.getTaskDate());
			} catch (ParseException e) {
				System.out.println("Invalid date format. It needs to be dd-mm-yyyy e.g. (01-01-2020). ");
				// e.printStackTrace();
			}
		}

		boolean done = t.getTaskStatus();

		System.out.println(" Current status: " + t.getTaskStatus());

		if (done) {
			System.out.println("Do you want to unmark it? y/n");
			String status = read.nextLine();
			if (status.contains("y")) {
				t.setTaskStatus(false);
			}
		} else {
			System.out.println("Do you want to mark it? y/n");
			String status = read.nextLine();
			if (status.contains("y")) {
				t.setTaskStatus(true);
			}
		}
	}

	/**
	 * View Tasks for a specific project that is entered by the user (projectName)
	 * @param projectName
	 */
	public void viewTasksForProject(String projectName) {
		ArrayList<Task> tasks = taskMap.get(projectName);
		if (tasks == null) {
			System.out.println("No tasks for project: " + projectName);
			return;
		}
		for (Task t : tasks) {
			System.out.println(t);
		}
	}

	// Sorting the Task objects
	private static final Comparator<Task> sortByDate = new Comparator<Task>() {
		@Override
		public int compare(Task t1, Task t2) {
			return t1.getTaskDate().compareTo(t2.getTaskDate());
		}
	};

	public void sortTasksByDate() {
		ArrayList<Task> taskList = getAllTasks();
		Collections.sort(taskList, sortByDate);
		viewAllTasks(taskList);
		// System.out.print(tasks.sort(Comparator.comparing(Task::getTaskDate)));
	}

	// Reading Projects and Tasks saved in the file
	public void readFromArrayList() {
		loadFromFile(filename);
	}

	// Saving Projects and Tasks in the file
	public void writeToFile() throws ParseException {
		Writefile writef = new Writefile();
		writef.writeToFile(taskMap);
	}


	// Reading overview of Projects and Tasks from a file
	public void projectSummary() {
		Readfile readf = new Readfile();
		readf.projectOverview();
	}

	// View Tasks for a specific Project that is entered by the user

	public void viewTasksForAProject(String project) {
		Readfile readf = new Readfile();
		readf.viewTasksForProject(project);
	}

}





