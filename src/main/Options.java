package main;

import java.util.Date;

/**
 * This class only prints the options one the screen when it is called!
 * 
 * @author tmp-sda-1184
 */
public class Options {
	String projectname;
	String description ;
	Date date;
	boolean status;
    int id = 0 ;
    
	public Options() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void printProjectOptions() {
		System.out.println("To Do List Project Options: ");
		System.out.println("------------------------------------------------------------------------------------------------- ");
		System.out.println(" View Tasks for a spacific Project, enter (1)         ------       Delete a Task, enter (5)");
		System.out.println(" Add new Project, enter (2)   			              ------       Delete a Project, enter (6)");
		System.out.println(" View Projects, enter (3)                             ------       Edit project and Tasks, enter (7)");
		System.out.println(" Sort All Projects, enter (4)                         -------      Exit form the projects, enter (8)");
		System.out.println("------------------------------------------------------------------------------------------------- ");
	}
	
	public void printTaskOptions() {
		System.out.println("Task Options: ");
		System.out.println("-----------------------------------------");
		System.out.println(" Add new Tasks, enter (1)");
		System.out.println(" View Task(s), enter (2)" ); 
		System.out.println(" Save and Exit this project, enter (3)");
		System.out.println("-------------------------------------------");
	}
	
	
	}
	
