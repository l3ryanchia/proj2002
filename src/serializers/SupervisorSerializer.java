package serializers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import models.Supervisor;

import managers.SupervisorManager;

public class SupervisorSerializer {
    public static SupervisorManager readSupervisorsFromFile(String filepath) {
        //List<Supervisor> supervisors = new ArrayList<>();
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
                    //supervisors.add(new Supervisor(userID, name));
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
