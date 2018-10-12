package listManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Readfile {

	String filename;
	TaskManager manager = new TaskManager();
	ArrayList<Task> temptask = new ArrayList<>();
	int tCounter;
	int fCounter;
	boolean tvalue;

	public Readfile(String filename) {
		this.filename = filename;
	}
	
	public Readfile() {
		this("SavedTasks/projectstor.txt");
	}

	
	/**
	 * loading values from a file into a HashMap (taskMap) in the TaskManager class to
	 * use them in runtime
	 * @param filename the name of the file name
	 * @return ArrayList<Task> tasks
	 */
	public ArrayList<Task> LoadTasks(String filename) {
		this.filename = filename;
		ArrayList<Task> tasks = new ArrayList<Task>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			System.out.println("SAVED PROJECTS ARE:");
			System.out.println("**************************************");
			System.out.println("ID PROJECTNAME DESCRIPTION DATE STATUS");
			System.out.println("---------------------------------------");
			int rowCount = 0;
			while ((line = reader.readLine()) != null) {
				rowCount++;

				// id:project:description:date:status
				String[] parts = line.split(":");

				System.out.println(parts[0].trim() + " " + parts[1].trim() + " " + parts[2].trim() + " "
						+ parts[3].trim() + " " + parts[4].trim());

				/* skip id and give it a new index */
				String projectname = parts[1];
				String description = parts[2];
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

				Date date = null;
				try {
					date = dateFormat.parse(parts[3]);
				} catch (ParseException e) {
					System.out.println("[ERROR] Incorrect date format at row:" + rowCount);
				}

				boolean status = Boolean.valueOf(parts[4]);
				tasks.add(new Task(projectname, description, date, status));
			}
			System.out.println("   ");
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("Unable to open the file " + this.filename);
		} catch (IOException e) {
			System.err.println("A problem in reading " + this.filename);
		}

		return tasks;
	}

	/**
	 * Showing a summary of the projects and its task to the user when the program started
	 * prints number of tasks that are done and number of tasks are not!
	 */
	public void projectOverview() {
		try {

			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			System.out.println("Projects Overview:  ");
			System.out.print("---------------------------------------");
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(":");
				tvalue = true;
				if (parts[4].equals("true"))
					tCounter++;
				else
					fCounter++;
			}
			System.out.println('\n' + "You have " + fCounter + " todo and " + tCounter + " are done!");
			System.out.println("---------------------------------------" + '\n');
			reader.close();
	
		} catch (FileNotFoundException e) {
			System.err.println("Unable to open the file " + this.filename);
		} catch (IOException e) {
			System.err.println("A problem in reading " + this.filename);
		}
	}

	/**
	 * View Tasks for specific project that is entered by the user
	 * @param wantedProjectName
	 */
	public void viewTasksForProject(String wantedProjectName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(":");
				String project = parts[1];
				
				if (project.equals(wantedProjectName)) {
					System.out.println(parts[0] + " " + parts[1] + " " + parts[2] + " " + parts[3] + " " + parts[4]);
				}

				 if (!project.equals(wantedProjectName)) {
				 System.out.println("There is no project in the name: " + wantedProjectName);
				 return; }
			}
			reader.close();
			System.out.println('\n');
		} catch (FileNotFoundException e) {
			System.err.println("Unable to open the file " + this.filename);
		} catch (IOException e) {
			System.err.println("A problem in reading " + this.filename);
		}
	}

}
