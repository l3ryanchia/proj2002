
package programs;

import java.util.Scanner;
import java.util.List;
import models.*;

public class SupervisorMenu {
    protected static void displayMenu(Supervisor supervisor) {
        boolean logout = false;
        Scanner scanner = new Scanner(System.in);

        while (!logout) {
            System.out.println("\nWelcome to FYP Management System - Supervisors");
            System.out.println("1. Create a new project");
            System.out.println("2. View my projects");
            System.out.println("3. View pending requests");
            System.out.println("4. View request history");
            System.out.println("5. Logout");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                	Project newProj = supervisor.createProject();
                	FYPMSApp.projectManager.addProject(newProj);
                    break;
                case 2:
                    //viewProjects(supervisor, scanner);
                    break;
                case 3:
                    //viewPendingRequests(supervisor, scanner);
                    break;
                case 4:
                    //viewRequestHistory(supervisor, scanner);
                    break;
                case 5:
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

/*
    private static void viewProjects(Supervisor supervisor, Scanner scanner) {
        //List<Project> projects = FYPMSApp.projectManager.getProjectsBySupervisor(supervisor.getUserID());
        for (Project project : projects) {
            project.displayProject();
        }

        System.out.println("1. Modify a project title");
        System.out.println("2. Request student transfer");
        System.out.println("3. Back");
        System.out.print("Please choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter project ID: ");
                String projectID = scanner.nextLine();
                Project project = FYPMSApp.projectManager.getProject(projectID);

                if (project != null && project.getSupervisor().equals(supervisor.getUserID())) {
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    project.setTitle(newTitle);
                    System.out.println("Project title updated successfully!");
                } else {
                    System.out.println("Invalid project ID or you are not the supervisor of this project.");
                }
                break;
            case 2:
                requestStudentTransfer(supervisor, scanner);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void requestStudentTransfer(Supervisor supervisor, Scanner scanner) {
        System.out.print("Enter project ID: ");
        String projectID = scanner.nextLine();

        System.out.print("Enter replacement supervisor ID: ");
        String replacementSupervisorID = scanner.nextLine();

        Project project = FYPMSApp.projectManager.getProject(projectID);
        Supervisor replacementSupervisor = FYPMSApp.supervisorManager.getSupervisor(replacementSupervisorID);

        if (project != null && replacementSupervisor != null && project.getSupervisor().equals(supervisor.getUserID())) {
            FYPMSApp.requestManager.addRequest(new Req_TransferStudent(supervisor, replacementSupervisor, project));
            System.out.println("Transfer request submitted successfully!");
        } else {
            System.out.println("Invalid project ID, supervisor ID, or you are not the supervisor of this project.");
        }
    }
    
    private static void viewPendingRequests(Supervisor supervisor, Scanner scanner) {
        FYPMSApp.requestManager.displayPendingRequests(supervisor);

        System.out.println("1. Approve request");
        System.out.println("2. Reject request");
        System.out.println("3. Back");
        System.out.print("Please choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
            case 2:
                System.out.print("Enter request ID: ");
                String requestID = scanner.nextLine();

                if (choice == 1) {
                    FYPMSApp.requestManager.approveRequest(requestID, supervisor);
                } else {
                    FYPMSApp.requestManager.rejectRequest(requestID, supervisor);
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewRequestHistory(Supervisor supervisor, Scanner scanner) {
        FYPMSApp.requestManager.displayRequestHistory(supervisor);
    }

*/
}        
        
