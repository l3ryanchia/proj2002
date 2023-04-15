package managers;

import models.Project;
import models.Project.Status;
import models.Req_AllocateProj;
import models.Req_DeallocateProj;
import models.Req_TransferStudent;
import models.Request;
import models.Request.ReqStatus;
import models.Supervisor;

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
    
    public void updateProjectsStatus(Request request, RequestManager reqManager) {
    	if(request instanceof Req_AllocateProj) {
    		Supervisor supervisor = ((Req_AllocateProj) request).getProject().getSupervisor();
    		if(supervisor.getNumOfAllocated() >= 2) {
    			this.makeUnavailable(supervisor, reqManager);
    		}
    	} else if (request instanceof Req_DeallocateProj) {
    		Project project = ((Req_DeallocateProj) request).getProject();
    		Supervisor supervisor = project.getSupervisor(); 
    		if(supervisor.getNumOfAllocated() == 1) {
    			this.makeAvailable(supervisor);
    		}
    		this.rejectAllPending(project.getProjectID(), reqManager);
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
    
    private void rejectAllPending(String projID, RequestManager reqManager) {
		List <Request> requests = reqManager.getRequestByProjID(projID);
	    for(int i=0; i<requests.size(); i++) {
	    	if(requests.get(i).getRequestStatus() != ReqStatus.PENDING) continue;
	    	requests.get(i).rejectRequest();
	    	System.out.println("All pending requests for " + projID + " has been automatically rejected...");
	    }
    }
    
    public Map<String, Project> filterByStatus(Map<String, Project> projects, Status status){    	
    	Map<String, Project> filteredProjs = new HashMap<>();
    	
    	for(Map.Entry<String, Project> set:projects.entrySet()) {
	    	Project project = set.getValue();
	    	if(project.getStatus() == status) filteredProjs.put(project.getProjectID(), project);
    	}
    	
    	return filteredProjs;
    }
    
    public Map<String, Project> filterBySupervisor(Map<String, Project> projects, String supervisorID){   	
    	Map<String, Project> filteredProjs = new HashMap<>();
    	
    	for(Map.Entry<String, Project> set:projects.entrySet()) {
	    	Project project = set.getValue();
	    	if(project.getSupervisor().getUserID().equals(supervisorID)) filteredProjs.put(project.getProjectID(), project);
    	}
    	
    	return filteredProjs;
    }
    
    public Map<String, Project> filterByStudent(Map<String, Project> projects, String studentID){
    	
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
    
    public void displayProjects(Project project) { //override method if only one project, avoid unnecessary loop
    	System.out.printf("%10s %85s %25s %30s %10s \n", "PROJECT ID", "PROJECT TITLE", "SUPERVISOR NAME", "SUPERVISOR EMAIL", "STATUS");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor().getName(), project.getSupervisor().getUserID() + "@e.ntu.edu.sg", project.getStatus());
    	   	if(project.getStatus() == Status.ALLOCATED) {
       	   		System.out.printf("%96s %56s \n", "[ ALLOCATED STUDENT: " + project.getStudent().getName(), "STUDENT EMAIL: " + project.getStudent().getUserID() + "@e.ntu.edu.sg ]");
    	   	}
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
