package managers;

import models.Project;
import models.Project.Status;
import models.Supervisor;
//import models.Request;
//import models.to;

import java.util.HashMap;
import java.util.Map;

public class ProjectManager {
    private Map<String, Project> projects;

    public ProjectManager() {
        projects = new HashMap<>();
    }

    public Project getProject(String projectID) {
        return projects.get(projectID);
    }

    public void addProject(Project project) {
        projects.put(project.getProjectID(), project);
    }
    
    public Map<String, Project> getProjectList(){
    	return projects;
    }

    // Other methods related to projects can be added here
//    public List<Project> getProjectsBySupervisor(String supervisorID) {
//        List<Project> supervisorProjects = new ArrayList<>();
//        for (Project project : projects.values()) {
//            if (project.getSupervisor().equals(supervisorID)) {
//                supervisorProjects.add(project);
//            }
//        }
//        return supervisorProjects;
//    }
    /*
    public void reserveProject(String projectID) {
    	Project project = getProject(projectID);
    	project.setStatus(Status.RESERVED);
    }

    public void unreserveProject(String projectID) {
    	Project project = getProject(projectID);
    	project.setStatus(Status.AVAILABLE);
    }

    public void allocateStudent(String projectID, String studentID) {
    	Project project = getProject(projectID);
    	project.setStudent(studentID);
    	project.setStatus(Status.ALLOCATED);
    }
    */
    
    //add filters
    public void displayAllAvailableProjects(SupervisorManager supervisorManager ) {
    	System.out.printf("%10s %85s %25s %30s %10s \n", "PROJECT ID", "PROJECT TITLE", "SUPERVISOR NAME", "SUPERVISOR EMAIL", "STATUS");
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	for(Map.Entry<String, Project> set:projects.entrySet()) {
    		Project project = set.getValue();
    		if(project.getStatus()==Status.AVAILABLE) {
    			System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor(), supervisorManager.getSupervisorID(project.getSupervisor()) + "@e.ntu.edu.sg", project.getStatus());
    		}
    	}
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void makeUnavailable (Supervisor supervisor, RequestManager reqManager){
    	for (Map.Entry<String, Project> set:projects.entrySet()) {
    		Project project = set.getValue();
    		if (project.getSupervisor() == supervisor.getName()) {
    			if (project.getStatus() != Status.ALLOCATED) {
    				if (project.getStatus() != Status.RESERVED) {
    					reqManager.getRequestByProjID(project.getProjectID()).rejectRequest();
    				}
    				project.setStatus(Status.UNAVAILABLE);
    			}
    		}
        }
    }
    
    public void makeAvailable(Supervisor supervisor, RequestManager reqManager) {
    	for (Map.Entry<String, Project> set:projects.entrySet()) {
    		Project project = set.getValue();
    		if (project.getSupervisor() == supervisor.getName()) {
    			if (project.getStatus() == Status.UNAVAILABLE) {
    				project.setStatus(Status.AVAILABLE);
    			}
    		}
        }
    	/*if (UNAVAILABLE)
    		change to AVAILABLE*/
    }
    
    public Map<String, Project> filterByStatus(Map<String, Project> projects, Status status){
    	if(status==null) return this.getProjectList();
    	
    	Map<String, Project> filteredProjs = new HashMap<>();
    	
    	for(Map.Entry<String, Project> set:projects.entrySet()) {
	    	Project project = set.getValue();
	    	if(project.getStatus() == status) filteredProjs.put(project.getProjectID(), project);
    	}
    	
    	return filteredProjs;
    }
    
    public Map<String, Project> filterBySupervisor(Map<String, Project> projects, String supervisor){
    	if(supervisor==null) return this.getProjectList();
    	
    	Map<String, Project> filteredProjs = new HashMap<>();
    	
    	for(Map.Entry<String, Project> set:projects.entrySet()) {
	    	Project project = set.getValue();
	    	if(project.getSupervisor() == supervisor) filteredProjs.put(project.getProjectID(), project);
    	}
    	
    	return filteredProjs;
    }
    
    public int displayProjects(Map<String, Project> projects, SupervisorManager supervisorManager) {
    	int count = 0;
    	System.out.printf("%10s %85s %25s %30s %10s \n", "PROJECT ID", "PROJECT TITLE", "SUPERVISOR NAME", "SUPERVISOR EMAIL", "STATUS");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   	    for(Map.Entry<String, Project> set:projects.entrySet()) {
    	    Project project = set.getValue();
    	   	System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor(), supervisorManager.getSupervisorID(project.getSupervisor()) + "@e.ntu.edu.sg", project.getStatus());
    	   	count++;
    	   }
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	return count;
    }
    
    public int displayFilter(Status status, String supervisor, SupervisorManager supervisorManager) {
    	int count = 0;
    	if ((status == null) && (supervisor == null)) {
    		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   	    	for(Map.Entry<String, Project> set:projects.entrySet()) {
   	    		Project project = set.getValue();
   	    		if(project.getStatus()==Status.AVAILABLE) {
   	    			System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor(), supervisorManager.getSupervisorID(project.getSupervisor()) + "@e.ntu.edu.sg", project.getStatus());
   	    			count++;
   	    		}
    	    }
    	   	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	}
    	else {
   			if (status == null) {
   				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       	    	for(Map.Entry<String, Project> set:projects.entrySet()) {
       	    		Project project = set.getValue();
        	    	if(project.getSupervisor()==supervisor) {
        	    		System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor(), supervisorManager.getSupervisorID(project.getSupervisor()) + "@e.ntu.edu.sg", project.getStatus());
        	    		count++;
        	   		}
        	   	}
        	   	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    		}
   			
    		else if (supervisor == null) {
    			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        	   	for(Map.Entry<String, Project> set:projects.entrySet()) {
        	   		Project project = set.getValue();
        	   		if(project.getStatus()==status) {
        	   			System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor(), supervisorManager.getSupervisorID(project.getSupervisor()) + "@e.ntu.edu.sg", project.getStatus());
        	   			count++;
            		}
       	    	}
       	    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    		}
    			
   			else {
   				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       	    	for(Map.Entry<String, Project> set:projects.entrySet()) {
        	    	Project project = set.getValue();
        	    	if((project.getStatus()==status) && (project.getSupervisor()==supervisor)) {				//both filter used
        	   			System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor(), supervisorManager.getSupervisorID(project.getSupervisor()) + "@e.ntu.edu.sg", project.getStatus());
        	   			count++;
        	   		}
        	   	}
        	   	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    		}
    	}
    	return count;
    }
    
}
