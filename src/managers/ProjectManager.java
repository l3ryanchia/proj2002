package managers;

import models.Project;
import models.Project.Status;

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
    
    public void displayAllAvailableProjects() {
    	System.out.printf("%10s %85s %25s \n", "PROJECT ID", "PROJECT TITLE", "PROJECT SUPERVISOR");
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
    	for(Map.Entry<String, Project> set:projects.entrySet()) {
    		Project project = set.getValue();
    		if(project.getStatus()==Status.AVAILABLE) {
    			System.out.printf("%10s %85s %25s \n", project.getProjectID(), project.getTitle(), project.getSupervisor());
    		}
    	}
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

    }
}
