package managers;

import models.Project;

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
}
