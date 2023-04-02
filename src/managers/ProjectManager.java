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
}
