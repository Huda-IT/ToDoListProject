package main;

import listManager.TaskManager;
import UserInterface.UserInterface;


import java.text.ParseException;


public class Main {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		TaskManager manager = new TaskManager();
		manager.loadFromFile("SavedTasks/projectstor.txt");
		manager.projectSummary();
		 UserInterface view = new UserInterface(manager);
		 view.Run();
	}

}