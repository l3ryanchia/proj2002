
package serializers;

import managers.SupervisorManager;
import managers.ProjectManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProjectSerializer {

    public static ProjectManager readProjectsFromFile(String filename, SupervisorManager supervisorManager) { 
    	ProjectManager projectManager = new ProjectManager();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", 2);

                if (values.length != 2) {
                    System.out.println("Skipping invalid row: " + line);
                    continue;
                }

                String supervisor = values[0].trim(); // find supervisor from supervisor manager
                String title = values[1].trim();
                
                projectManager.addProject(title, supervisorManager.getSupervisor(supervisorManager.getSupervisorID(supervisor)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projectManager;
    }
}
