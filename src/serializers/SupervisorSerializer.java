package serializers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Supervisor;

public class SupervisorSerializer {
    public static List<Supervisor> readSupervisorsFromFile(String filepath) {
        List<Supervisor> supervisors = new ArrayList<>();
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
                    supervisors.add(new Supervisor(userID, name, email));
                } else {
                    System.out.println("Invalid email for supervisor: " + name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return supervisors;
    }
}
