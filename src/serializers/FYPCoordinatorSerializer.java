
package serializers;

import models.Supervisor;
import managers.SupervisorManager;
import managers.FYPCoordinatorManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FYPCoordinatorSerializer {

    public static FYPCoordinatorManager readCoordinatorFromFile(String filename, SupervisorManager supervisorManager) { 
    	Supervisor coordinator = null;
    	FYPCoordinatorManager fypCoordinatorManager = new FYPCoordinatorManager();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header line

            if ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0].trim();
                String email = values[1].trim();
                String userID = email.substring(0, email.indexOf('@'));

                coordinator = supervisorManager.getSupervisor(userID);
            	if(coordinator == null) {
	        		System.out.println("FYP Coordinator must be registered as a Supervisor!");
	        		return null;
	        	}
                fypCoordinatorManager.setCoordinator(coordinator, supervisorManager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fypCoordinatorManager;
    }
}
