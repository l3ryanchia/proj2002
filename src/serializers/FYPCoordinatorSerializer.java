//package serializers;
//
//import models.FYPCoordinator;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class FYPCoordinatorSerializer {
//
//    public static FYPCoordinator readCoordinatorFromFile(String filename) {
//        FYPCoordinator coordinator = null;
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            br.readLine(); // Skip header line
//
//            if ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                String name = values[0].trim();
//                String email = values[1].trim();
//                coordinator = new FYPCoordinator(name, email);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return coordinator;
//    }
//}
//

package serializers;

import models.FYPCoordinator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FYPCoordinatorSerializer {

    public static FYPCoordinator readCoordinatorFromFile(String filename) { //change to supervisor return type
        FYPCoordinator coordinator = null;//change to supervisor

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header line

            if ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0].trim();
                String email = values[1].trim();
                String userID = email.substring(0, email.indexOf('@'));
                coordinator = new FYPCoordinator(userID, name, email); //find by name from supervisor manager
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coordinator;
    }
}
