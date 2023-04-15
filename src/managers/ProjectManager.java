package managers;

import models.Project;
import models.Project.Status;
import models.Req_AllocateProj;
import models.Req_DeallocateProj;
import models.Req_TransferStudent;
import models.Request;
import models.Request.ReqStatus;
import models.Supervisor;
//import models.to;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectManager {
    private Map<String, Project> projects;

    public ProjectManager() {
        projects = new HashMap<>();
    }

    public Project getProject(String projectID) {
        return projects.get(projectID);
    }

    public Project addProject(String title, Supervisor supervisor) {
    	Project project = new Project(title, supervisor);
        projects.put(project.getProjectID(), project);
        supervisor.addProject(project.getProjectID());
        return project;
    }
    
    public Map<String, Project> getProjectList(){
    	return projects;
    }

    public void displayAllAvailableProjects() {//remove
    	System.out.printf("%10s %85s %25s %30s %10s \n", "PROJECT ID", "PROJECT TITLE", "SUPERVISOR NAME", "SUPERVISOR EMAIL", "STATUS");
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	for(Map.Entry<String, Project> set:projects.entrySet()) {
    		Project project = set.getValue();
    		if(project.getStatus()==Status.AVAILABLE) {
    			System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor().getName(), project.getSupervisor().getUserID() + "@e.ntu.edu.sg", project.getStatus());
    		}
    	}
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void updateProjectsStatus(Request request, RequestManager reqManager) { //move to request manager
    	if(request instanceof Req_AllocateProj) {
    		Supervisor supervisor = ((Req_AllocateProj) request).getProject().getSupervisor();//downcasting
    		if(supervisor.getNumOfAllocated() >= 2) {
    			this.makeUnavailable(supervisor, reqManager);
    		}
    		
    	} else if (request instanceof Req_DeallocateProj) {
    		Supervisor supervisor = ((Req_DeallocateProj) request).getProject().getSupervisor(); 
    		if(supervisor.getNumOfAllocated() == 1) {
    			this.makeAvailable(supervisor);
    		}
    		String projID = ((Req_DeallocateProj) request).getProject().getProjectID();
    		List <Request> requests = reqManager.getRequestByProjID(projID);
    	    for(int i=0; i<requests.size(); i++) {
    	    	if(requests.get(i).getRequestStatus() != ReqStatus.PENDING) continue;
    	    	requests.get(i).rejectRequest();
    	    	System.out.println("All pending requests for " + projID + " has been automatically rejected...");
    	    }
    	} else if (request instanceof Req_TransferStudent) {
    		Supervisor supervisorOld = ((Req_TransferStudent) request).getSupervisorOld(); 
    		Supervisor supervisorNew = ((Req_TransferStudent) request).getSupervisorNew(); 
    		if(supervisorNew.getNumOfAllocated() >= 2) {
    			this.makeUnavailable(supervisorNew, reqManager);
    		}
    		if(supervisorOld.getNumOfAllocated() == 1) {
    			this.makeAvailable(supervisorOld);
    		}
    	}
    }
    
    public void makeUnavailable (Supervisor supervisor, RequestManager reqManager){
    	for (Map.Entry<String, Project> set:projects.entrySet()) {
    		Project project = set.getValue();
    		if (project.getSupervisor().equals(supervisor)) {
    			if (project.getStatus() != Status.ALLOCATED) {
    				if (project.getStatus() == Status.RESERVED) {
    					List<Request> projectRequests = reqManager.getRequestByProjID(project.getProjectID());
    					for(int i=0; i<projectRequests.size(); i++) {
    						if(projectRequests.get(i).getRequestStatus() != ReqStatus.PENDING) continue;
    						projectRequests.get(i).rejectRequest();
    					}
    				}
    				project.setStatus(Status.UNAVAILABLE);
    			}
    		}
        }
		System.out.println("\n" + supervisor.getUserID() + " has reached the maximum number of allocated projects...");
		System.out.println("All AVAILABLE projects of " + supervisor.getUserID() + " has been made UNAVAILABLE.");
		System.out.println("All requests for RESERVED projects have been automatically REJECTED.");
    }
    
    public void makeAvailable(Supervisor supervisor) {
    	for (Map.Entry<String, Project> set:projects.entrySet()) {
    		Project project = set.getValue();
    		if (project.getSupervisor().equals(supervisor)) {
    			if (project.getStatus() == Status.UNAVAILABLE) {
    				project.setStatus(Status.AVAILABLE);
    			}
    		}
        }
		System.out.println("\n" + supervisor.getUserID() + " now has less than the maximum number of allocated projects...");
		System.out.println("All UNAVAILABLE projects of " + supervisor.getUserID() + " has been made AVAILABLE.");
    }
    
    public Map<String, Project> filterByStatus(Map<String, Project> projects, Status status){
    	//if(status==null) return this.getProjectList();
    	
    	Map<String, Project> filteredProjs = new HashMap<>();
    	
    	for(Map.Entry<String, Project> set:projects.entrySet()) {
	    	Project project = set.getValue();
	    	if(project.getStatus() == status) filteredProjs.put(project.getProjectID(), project);
    	}
    	
    	return filteredProjs;
    }
    
    public Map<String, Project> filterBySupervisor(Map<String, Project> projects, String supervisorID){
    	//if(supervisorID==null) return this.getProjectList();
    	
    	Map<String, Project> filteredProjs = new HashMap<>();
    	
    	for(Map.Entry<String, Project> set:projects.entrySet()) {
	    	Project project = set.getValue();
	    	if(project.getSupervisor().getUserID().equals(supervisorID)) filteredProjs.put(project.getProjectID(), project);
    	}
    	
    	return filteredProjs;
    }
    
    public Map<String, Project> filterByStudent(Map<String, Project> projects, String studentID){
    	//if(studentID==null) return this.getProjectList();
    	
    	Map<String, Project> filteredProjs = new HashMap<>();
    	
    	for(Map.Entry<String, Project> set:projects.entrySet()) {
	    	Project project = set.getValue();
	    	if(project.getStudent().getUserID().equals(studentID)) filteredProjs.put(project.getProjectID(), project);
    	}
    	
    	return filteredProjs;
    }
    
    public int displayProjects(Map<String, Project> projects) {
    	int count = 0;
    	System.out.printf("%10s %85s %25s %30s %10s \n", "PROJECT ID", "PROJECT TITLE", "SUPERVISOR NAME", "SUPERVISOR EMAIL", "STATUS");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   	    for(Map.Entry<String, Project> set:projects.entrySet()) {
    	    Project project = set.getValue();
    	   	System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor().getName(), project.getSupervisor().getUserID() + "@e.ntu.edu.sg", project.getStatus());
    	   	if(project.getStatus() == Status.ALLOCATED) {
    	   		System.out.printf("%96s %56s \n", "[ ALLOCATED STUDENT: " + project.getStudent().getName(), "STUDENT EMAIL: " + project.getStudent().getUserID() + "@e.ntu.edu.sg ]");
    	   	}
    	   	count++;
    	   }
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	return count;
    }
    
    public void displayProjects(Project project) {
    	System.out.printf("%10s %85s %25s %30s %10s \n", "PROJECT ID", "PROJECT TITLE", "SUPERVISOR NAME", "SUPERVISOR EMAIL", "STATUS");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor().getName(), project.getSupervisor().getUserID() + "@e.ntu.edu.sg", project.getStatus());
    	   	if(project.getStatus() == Status.ALLOCATED) {
       	   		System.out.printf("%96s %56s \n", "[ ALLOCATED STUDENT: " + project.getStudent().getName(), "STUDENT EMAIL: " + project.getStudent().getUserID() + "@e.ntu.edu.sg ]");
    	   	}
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public int displayFilter(Status status, String supervisor) {//remove
    	int count = 0;
    	if ((status == null) && (supervisor == null)) {
    		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   	    	for(Map.Entry<String, Project> set:projects.entrySet()) {
   	    		Project project = set.getValue();
   	    		if(project.getStatus()==Status.AVAILABLE) {
   	    			System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor(), project.getSupervisor().getUserID() + "@e.ntu.edu.sg", project.getStatus());
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
        	    	if(project.getSupervisor().getName()==supervisor) {
        	    		System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor(), project.getSupervisor().getUserID() + "@e.ntu.edu.sg", project.getStatus());
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
        	   			System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor(), project.getSupervisor().getUserID() + "@e.ntu.edu.sg", project.getStatus());
        	   			count++;
            		}
       	    	}
       	    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    		}
    			
   			else {
   				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       	    	for(Map.Entry<String, Project> set:projects.entrySet()) {
        	    	Project project = set.getValue();
        	    	if((project.getStatus()==status) && (project.getSupervisor().getName()==supervisor)) {				//both filter used
        	   			System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor(), project.getSupervisor().getUserID() + "@e.ntu.edu.sg", project.getStatus());
        	   			count++;
        	   		}
        	   	}
        	   	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    		}
    	}
    	return count;
    }
    
}
