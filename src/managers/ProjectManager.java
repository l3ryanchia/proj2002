package managers;

import models.Project;
import models.Project.Status;
import models.Supervisor;
import models.request;
import models.to;

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
    public void displayAllAvailableProjects() {
    	System.out.printf("%10s %85s %25s %30s %10s \n", "PROJECT ID", "PROJECT TITLE", "SUPERVISOR NAME", "SUPERVISOR EMAIL", "STATUS");
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	for(Map.Entry<String, Project> set:projects.entrySet()) {
    		Project project = set.getValue();
    		if(project.getStatus()==Status.AVAILABLE) {
    			System.out.printf("%10s %85s %25s %30s %10s \n", project.getProjectID(), project.getTitle(), project.getSupervisor(), FYPMSApp.supervisorManager. + "@e.ntu.edu.sg", "AVAILABLE");
    		}
    	}
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void makeUnavailable(Supervisor supervisor) {
    	//iterate through projs and check supervisor
    	if(not ALLOCATED)
    		if(RESERVED) reject request
    		change to UNAVAILABLE
    }
    
}
