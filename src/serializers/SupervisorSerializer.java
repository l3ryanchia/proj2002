package serializers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import managers.SupervisorManager;

public class SupervisorSerializer {
    public static SupervisorManager readSupervisorsFromFile(String filepath) {
        SupervisorManager supervisorManager = new SupervisorManager();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            br.readLine(); // Skipping header
            while ((line = br.readLine()) != null) {
            	if(line.trim().isEmpty()) continue;
                String[] supervisorData = line.split(",");
                String name = supervisorData[0].trim();
                String email = supervisorData[1].trim();

                if (!email.isEmpty() && email.contains("@") && email.indexOf('@') > 0) {
                    String userID = email.split("@")[0];
                    supervisorManager.addSupervisor(userID, name);
                } else {
                    System.out.println("Invalid email for supervisor: " + name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return supervisorManager;
    }
}
