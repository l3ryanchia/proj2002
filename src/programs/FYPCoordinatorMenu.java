package programs;

import java.util.Scanner;
import java.util.List;
import models.*;

public class FYPCoordinatorMenu {
    protected static void displayMenu(FYPCoordinator coordinator) {
        boolean logout = false;
        Scanner scanner = new Scanner(System.in);

        while (!logout) {
            System.out.println("\nWelcome to FYP Management System - FYP Coordinator");
            System.out.println("1. View projects by status");
            System.out.println("2. Generate project details report");
            System.out.println("3. View pending requests");
            System.out.println("4. View request history");
            System.out.println("5. Logout");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewProjectsByStatus(scanner);
                    break;
                case 2:
                    generateProjectDetailsReport(scanner);
                    break;
                case 3:
                    viewPendingRequests(coordinator, scanner);
                    break;
                case 4:
                    viewRequestHistory(coordinator, scanner);
                    break;
                case 5:
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewProjectsByStatus(Scanner scanner) {
        System.out.println("Please enter project status (AVAILABLE, UNAVAILABLE, RESERVED, ALLOCATED): ");
        String status = scanner.nextLine().toUpperCase();

        try {
            ProjectStatus projectStatus = ProjectStatus.valueOf(status);
            List<Project> projects = FYPMSApp.projectManager.getProjectsByStatus(projectStatus);

            for (Project project : projects) {
                project.displayProject();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status. Please try again.");
        }
    }

    private static void generateProjectDetailsReport(Scanner scanner) {
        System.out.println("1. Filter by status");
        System.out.println("2. Filter by supervisor");
        System.out.println("3. Back");
        System.out.print("Please choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                viewProjectsByStatus(scanner);
                break;
            case 2:
                System.out.print("Enter supervisor ID: ");
                String supervisorID = scanner.nextLine();
                List<Project> projects = FYPMSApp.projectManager.getProjectsBySupervisor(supervisorID);

                for (Project project : projects) {
                    project.displayProject();
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewPendingRequests(FYPCoordinator coordinator, Scanner scanner) {
        FYPMSApp.requestManager.displayPendingRequests(coordinator);

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
                    FYPMSApp.requestManager.approveRequest(requestID, coordinator);
                } else {
                    FYPMSApp.requestManager.rejectRequest(requestID, coordinator);
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewRequestHistory(FYPCoordinator coordinator, Scanner scanner) {
        FYPApp.requestManager.displayRequestHistory(coordinator);
        System.out.println("1. Filter by status");
        System.out.println("2. Filter by supervisor or student");
        System.out.println("3. Back");
        System.out.print("Please choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter request status (PENDING, APPROVED, REJECTED): ");
                String status = scanner.nextLine().toUpperCase();
                try {
                    RequestStatus requestStatus = RequestStatus.valueOf(status);
                    FYPMSApp.requestManager.displayRequestHistoryByStatus(coordinator, requestStatus);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid status. Please try again.");
                }
                break;
            case 2:
                System.out.print("Enter supervisor or student ID: ");
                String userID = scanner.nextLine();
                FYPMSApp.requestManager.displayRequestHistoryByUser(coordinator, userID);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
