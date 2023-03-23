//package serializers;
//
//import models.Project;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProjectSerializer {
//
//    public static List<Project> readProjectsFromFile(String filename) {
//        List<Project> projects = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            br.readLine(); // Skip header line
//
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",", 2);
//                String supervisor = values[0].trim();
//                String title = values[1].trim();
//                projects.add(new Project(title, supervisor));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return projects;
//    }
//}

package serializers;

import models.Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//public class ProjectSerializer {
//
//    public static List<Project> readProjectsFromFile(String filename) {
//        List<Project> projects = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            br.readLine(); // Skip header line
//
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",", 3);
//                String projectID = values[0].trim();
//                String supervisor = values[1].trim();
//                String title = values[2].trim();
//                projects.add(new Project(projectID, title, supervisor));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return projects;
//    }
//}

public class ProjectSerializer {

    public static List<Project> readProjectsFromFile(String filename) {
        List<Project> projects = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header line

            int projectIDCounter = 1;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", 2);

                if (values.length != 2) {
                    System.out.println("Skipping invalid row: " + line);
                    continue;
                }

                String projectID = "P" + projectIDCounter;
                String supervisor = values[0].trim();
                String title = values[1].trim();
                projects.add(new Project(projectID, title, supervisor));
                projectIDCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projects;
    }
}
