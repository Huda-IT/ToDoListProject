package listManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;




	public class Task {
		
		 int id;
		 String project;
		 String description;
		 Date date;
		 boolean status;
		 SimpleDateFormat dateFormat;
		 
		public Task( int id, String project, String description, Date date, boolean status) {
			this.project = project;
			this.description = description;
			this.date = date;
			this.status = status;
			this.id = id;
			dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
		}
		
		public Task(String project, String description, Date date, boolean status) {
			this(-1, project, description, date, status);
		}
		
		
		// Set Title method
		public void setProject(String project) {
			this.project = project;
		}
		
		// get Description method
		public String getProject() {
			return project;
		}
		
		// Set Description method
		public void setDescription(String description) {
				this.description= description;
		}
		
		// get Description method
		public String getDescription() {
			return description;
		}
		
		
		// Set Task Date method
		public void setTaskDate(Date date) {
			this.date=date;
		}
		
		
		// get the Date of the Task method
			public Date getTaskDate() {
				return date;
			}
		
		
		// set the condition of the Task
		public void setTaskStatus(boolean status) {
			this.status= status;
		}
		
		
		// get the condition of the Task
		public boolean getTaskStatus() {
			return status;
		}
		
		public int getId(){
			return id;
		}

		public void setId(int id){
			this.id = id;
		}
		
		@Override
		public String toString(){ 
			return   id + " : " + getProject() + '\n'+ "    "  + getDescription()+ "   At " + dateFormat.format(getTaskDate()) + "    " + getTaskStatus()  +"\n";
		}
		
		
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Task)) {
				return false;
			}
			Task t = (Task) o;
			return this.project.equals(t.getProject()) && this.description.equals(t.getDescription()) && this.status == t.getTaskStatus() && this.getTaskDate().equals(t.getTaskDate());	
		}
		
		@Override
	    public int hashCode() {
			//return Objects.hash(project, description, date, (Boolean) status);
	        int result = 17;
	        result = 31 * result + project.hashCode();
	        result = 31 * result + description.hashCode();
	        result = 31 * result + date.hashCode();
	        result = 31 * result + ((Boolean) status).hashCode();
	        return result;
	    }

		
			
		
	}

