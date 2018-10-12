package listManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class Writefile {
	String filename = "SavedTasks/projectstor.txt";
	
	public Writefile() {
		
	}

	/**
	 * This method writes the HashMap keys and values into the filename 
	 * @param taskMap is the Hashmap name
	 * @return true if the writing was successful
	 * @throws ParseException 
	 */
	public boolean writeToFile( HashMap<String, ArrayList<Task>> taskMap) throws ParseException {
		boolean success = false;
		try {
			FileWriter writer = new FileWriter (filename, false); 
			BufferedWriter bwriter = new BufferedWriter(writer);
			SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy" );
			
			for (Entry<String, ArrayList<Task>> key :taskMap.entrySet()) {
				//System.out.println(key.getKey() + " : " + '\n');
				List<Task> tasks= key.getValue( );
				//bwriter.write(key.getKey() +  '\n') ;
					for(Task task : tasks) {
						
						bwriter.write(task.getId()+ ":" + task.getProject() + ":" + task.getDescription() + ":" 
+ dateFormat.format((task.getTaskDate())) + ":" + task.getTaskStatus());
						bwriter.newLine();
						success = true;
					}
			}
			
		bwriter.close();
		
		}catch (IOException e) {
			System.err.println(" There was a problem writing to "+ filename);
			success =false;
		}
			return success;
		}	
	
	
	/* Deleting a Task from a project by entering its id
	 * 
	 */
	/*public void deleteTaskByID (int id) {
		try { 
			FileWriter writer = new FileWriter (tempfile, true); 
			BufferedWriter bwriter = new BufferedWriter(writer);
			
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				String[ ] parts = line.split(":");
				String taskid = parts[0];
				System.out.println(taskid);
				
				if (!String.valueOf(id).equals(taskid)) {
					bwriter.write(line + '\n' );
				}
				
		}
			reader.close();
			System.out.println( '\n'); 
			bwriter.close();
			
			//Rename the new file to the origin filename 
			//if (!tempfile.renameTo(filename))
		        System.out.println("Could not rename file");
			
		} 
		catch ( FileNotFoundException e) {
			System.err.println("Unable to open the file "+ this.filename);
		} catch (IOException e) {
			System.err.println("A problem in reading "+ this.filename);
		}
	}*/
	

	
}

	

