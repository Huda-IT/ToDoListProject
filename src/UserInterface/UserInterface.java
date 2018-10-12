package UserInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import listManager.TaskManager;
import main.Options;

public class UserInterface {
	TaskManager manager;
	String projectname;
	String description;
	Date date;
	boolean status;
	int id = 0;
	Scanner read = new Scanner(System.in);
	
	public UserInterface(TaskManager manager) {
		this.manager = manager;
	}

	public void Run() throws ParseException {
		this.Menu();
	}

	/**
	 * This method called other methods to perform operations depending on the
	 * choice was entered by the user
	 * 
	 * @throws ParseException if the input is incorrect!
	 */
	public void Menu() throws ParseException {

		Options option = new Options();
		boolean process = true;
		while (process) {
			option.printProjectOptions();
			
			try {	
				 read = new Scanner (System.in);
				
				if  (read.hasNextInt()) {
					int choice = read.nextInt();
				
						if (choice == 1) {
							System.out.println(" Enter Project Name to view its Tasks:");
							projectname = read.next();
							manager.viewTasksForAProject(projectname);
						}
		
						else if (choice == 2) {
							System.out.println(" Enter Project Name: ");
							projectname = read.next();
							System.out.println('\n');
		
							// Adding Task information
							boolean tProcess = true;
							while (tProcess) {
								option.printTaskOptions();
								int taskChoice = read.nextInt();
		
								if (taskChoice == 1) {
									enterTaskValues();
								}
		
								else if (taskChoice == 2) {
									System.out.println(" The Task(s) are: " + '\n');
									manager.viewAllTasks();
								}
		
								else if (taskChoice == 3) {
									System.out.println(" You are Done with your Tasks! ");
									manager.writeToFile();
									tProcess = false;
								} else
									System.out.println(" Invalid Choice number!!");
							}
						}
		
						else if (choice == 3) {
							System.out.println(" The Projects are: ");
							manager.viewAllTasks();
						}
		
						else if (choice == 4) {
							System.out.println(" Sorting Projects by Date: ");
							manager.sortTasksByDate();
						}
		
						else if (choice == 5) {
							System.out.println(" Enter Task Number you want to delete: ");
							int taskid = read.nextInt();
							manager.deleteTaskById(taskid);
						}
		
						else if (choice == 6) {
							System.out.println(" Enter  project name you want to remove: ");
							String removeproject = read.next();
							manager.removeProject(removeproject);
						}
		
						else if (choice == 7) {
							// TODO readfile.ReadProject();
							System.out.println(" Enter Task number you want to Edit: ");
							id = read.nextInt();
							manager.editTaskById(id);
		
						} else if (choice == 8) {
							System.out.println(" Done with this project, save and quite! ");
							manager.writeToFile();
							process = false;
						}
		
						else
							System.out.println(" INVALID INPUT!, PLEASE ENTER YOUR CHOICE AGAIN: " + '\n');
			}
			else {
				 System.out.println("INVALID INPUT!, PLEASE ENTER YOUR CHOICE AGAIN: " + '\n');
			}
		    	}
			catch (InputMismatchException e) {
				System.out.println("INVALID INPUT!, PLEASE ENTER YOUR CHOICE AGAIN: " + '\n');
			}
		}	
	}
	
	
	/**
	 *  This method call methods for (choice 2)  in Menu() method to enter task's values
	 */
	public void enterTaskValues () {
			System.out.println(" Add New Tasks: ");
	
			// Scanning Task Description
			System.out.println(" Add Description: ");
			Scanner read = new Scanner(System.in);
			description = read.nextLine();
			
	
			// Scanning Task Date
			System.out.println(" Add Date in this format: (dd-MM-yyyy)");
			String date1 = read.next();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setLenient(false);
	
				date = null;
				try {
					date = dateFormat.parse(date1);
					System.out.println(dateFormat.format(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println(" Error Formatting !");
					e.printStackTrace();
				}
	
			// Scanning Task Status
			status = false;
			System.out.println("Select Status: ");
			status = read.nextBoolean();
	
			if (status == true)
				System.out.println(" Your Task is done! Good job!");
			else
				System.out.println(" Task is not done yet!! ");
	
			manager.addProjectTasks(projectname, description, date, status);
			 
	}
	
}
