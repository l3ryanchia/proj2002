package programs;

import java.util.Scanner;
import java.util.List;
import java.util.Map;

import models.*;
import models.Request.UserType;

public class FYPCoordinatorMenu {
    protected static void displayMenu(FYPCoordinator coordinator) {
        boolean logout = false;
        Scanner scanner = new Scanner(System.in);

        while (!logout) {
        	boolean isValidInput = false;
        	int subchoice;
            int choice = 0;
            System.out.println("\nWelcome to FYP Management System - FYP Coordinator");
            System.out.println("1. View projects by status");
            System.out.println("2. Generate project details report");
            System.out.println("3. View pending requests");
            System.out.println("4. View request history");
            System.out.println("5. Logout");
            System.out.println("6. Change password");
            System.out.print("Please choose an option: ");

            //int choice = scanner.nextInt();
            //scanner.nextLine();
            while (!isValidInput) {
	            try {
	            	choice = scanner.nextInt();
	            	scanner.nextLine(); // Consume the newline
	            	isValidInput = true;
	            } catch(Exception e) {
	            	//System.out.println("Invalid choice. Please try again.");
	            	scanner.nextLine();
	            	break;
	            }
            }

            switch (choice) {
                case 1:
                	Map <String, Project> projectsToDisplay;
                	
                	//consider using loop to iterate through Project.Status instead
                	
                	System.out.println("AVAILABLE PROJECTS: ");
                	projectsToDisplay = FYPMSApp.projectManager.filterByStatus(FYPMSApp.projectManager.getProjectList(), Project.Status.AVAILABLE);
                	FYPMSApp.projectManager.displayProjects(projectsToDisplay, FYPMSApp.supervisorManager);
                	
                	System.out.println("ALLOCATED PROJECTS: ");
                	projectsToDisplay = FYPMSApp.projectManager.filterByStatus(FYPMSApp.projectManager.getProjectList(), Project.Status.ALLOCATED);
                	FYPMSApp.projectManager.displayProjects(projectsToDisplay, FYPMSApp.supervisorManager);
                	
                	System.out.println("RESERVED PROJECTS: ");
                	projectsToDisplay = FYPMSApp.projectManager.filterByStatus(FYPMSApp.projectManager.getProjectList(), Project.Status.RESERVED);
                	FYPMSApp.projectManager.displayProjects(projectsToDisplay, FYPMSApp.supervisorManager);
                	
                	System.out.println("UNAVAILABLE PROJECTS: ");
                	projectsToDisplay = FYPMSApp.projectManager.filterByStatus(FYPMSApp.projectManager.getProjectList(), Project.Status.UNAVAILABLE);
                	FYPMSApp.projectManager.displayProjects(projectsToDisplay, FYPMSApp.supervisorManager);
                	
                    break;
                case 2:
                	Map <String, Project> projectsList = FYPMSApp.projectManager.getProjectList();
                	
                	int displayReport=0, filter=0;
                	while(displayReport != 2) {
                		displayReport = 0; filter = 0;
                		isValidInput = false;
	                	System.out.println("1. Add search filter");
	                	System.out.println("2. Display project details report");
	                	//displayReport = scanner.nextInt();
	                	while (!isValidInput) {
	        	            try {
	        	            	displayReport = scanner.nextInt();
	        	            	scanner.nextLine(); // Consume the newline
	        	            	isValidInput = true;
	        	            } catch(Exception e) {
	        	            	//System.out.println("Invalid choice. Please try again.");
	        	            	scanner.nextLine();
	        	            	break;
	        	            }
	                    }
	                	
	                	switch(displayReport) {
	                		case 1:
			                	System.out.println("Filter by: ");
			                	System.out.println("1. Project Status");
			                	System.out.println("2. Project Supervisor");
			                	isValidInput = false;
			                	//filter = scanner.nextInt();
			                	
			                	while (!isValidInput) {
			        	            try {
			        	            	filter = scanner.nextInt();
			        	            	scanner.nextLine(); // Consume the newline
			        	            	isValidInput = true;
			        	            } catch(Exception e) {
			        	            	//System.out.println("Invalid choice. Please try again.");
			        	            	scanner.nextLine();
			        	            	break;
			        	            }
			                    }
			                	
			                	switch(filter) {
			                		case 1:
			                			String statusFilter; //consider making this a list to filter by multiple options
			                			System.out.println("Enter status filter [AVAILABLE, ALLOCATED, RESERVED, UNAVAILABLE]: ");
			                			statusFilter = scanner.nextLine().toUpperCase();
			                			Project.Status status = null;
			                			switch(statusFilter) {
			                				case "AVAILABLE": status = Project.Status.AVAILABLE; break;
			                				case "ALLOCATED": status = Project.Status.ALLOCATED; break;
			                				case "RESERVED": status = Project.Status.RESERVED; break;
			                				case "UNAVAILABLE": status = Project.Status.UNAVAILABLE; break;
			                				default: System.out.println("Invalid filter!"); break;
			                			}
			                			
			                			projectsList = FYPMSApp.projectManager.filterByStatus(projectsList, status);
			                			break;
			                		case 2:
			                			String supersivorFilter; //consider making this a list to filter by multiple options
			                			System.out.println("Enter supervisor's full name: ");
			                			supersivorFilter = scanner.nextLine();
			                			
			                			projectsList = FYPMSApp.projectManager.filterBySupervisor(projectsList, supersivorFilter);
			                			break;
			                			
			                		default:
			                			System.out.println("Invalid input.");
			                			break;
			                	}
			                	break;
	                		case 2:
	                			FYPMSApp.projectManager.displayProjects(projectsList, FYPMSApp.supervisorManager);
	                			break;
	                		default: 
	                			System.out.println("Invalid input.");
	                	}
                	}
                    break;
                case 3:
                	System.out.println("PENDING REQUESTS FROM STUDENTS:");
                	if(FYPMSApp.requestManager.checkIncoming(coordinator.getUserID(), models.Request.UserType.STUDENT, true)==0) System.out.println("No pending requests.");
                	System.out.println("PENDING REQUESTS FROM SUPERVISORS:");
                	if(FYPMSApp.requestManager.checkIncoming(coordinator.getUserID(), models.Request.UserType.SUPERVISOR, true)==0) System.out.println("No pending requests.");
                	while(true) {
                		isValidInput = false;
                		subchoice = 0;
                		System.out.println("\n1. Approve a request");
                		System.out.println("2. Reject a request");
	                    System.out.println("3. Back");
	                    System.out.print("Please choose an option: ");
	                    
	                    //subchoice = scanner.nextInt();
	                    //scanner.nextLine();
	                    while (!isValidInput) {
	        	            try {
	        	            	subchoice = scanner.nextInt();
	        	            	scanner.nextLine(); // Consume the newline
	        	            	isValidInput = true;
	        	            } catch(Exception e) {
	        	            	//System.out.println("Invalid choice. Please try again.");
	        	            	scanner.nextLine();
	        	            	break;
	        	            }
	                    }
	                    String selection;
	                    Request request;
	                     
	                    switch (subchoice) {
	                    	case 1:
	                    		System.out.print("Please enter Request ID: ");
	                    		selection = scanner.nextLine();
	                    		request = FYPMSApp.requestManager.getRequestByID(selection);
	                    		if(request == null) {System.out.print("Invalid ID."); break;}
	                    		if(request.getReceiverID() != coordinator.getUserID()) {System.out.print("This request is not addressed to you."); break;}
	                    		
	                    		if(request.approveRequest()) {
	                    			System.out.print("Request Approved!");
	                    			FYPMSApp.projectManager.updateProjectsStatus(request, FYPMSApp.requestManager);
	                    		}
	                    		
	                    		break;
	                    	case 2:
	                    		System.out.print("Please enter Reqeust ID: ");
	                    		selection = scanner.nextLine();
	                    		request = FYPMSApp.requestManager.getRequestByID(selection);
	                    		if(request == null) {System.out.print("Invalid ID."); break;}
	                    		if(request.getReceiverID() != coordinator.getUserID()) {System.out.print("This request is not addressed to you."); break;}
	                    		
	                    		FYPMSApp.requestManager.getRequestByID(selection).rejectRequest(); //should not be able to reject approved requests
	                    		System.out.print("Request Rejected!");
	                    		
	                    		break;
	                    	case 3:
	                    		break;
	                    	default:
	                            System.out.println("Invalid choice. Please try again.");
	                    } 
	                    if(subchoice==3) break;
                	}
                    break;
                case 4:
                	FYPMSApp.requestManager.checkIncoming(coordinator.getUserID(), UserType.STUDENT, false);
                	FYPMSApp.requestManager.checkIncoming(coordinator.getUserID(), UserType.SUPERVISOR, false);
                    break;
                case 5:
                    logout = true;
                    break;
                /*case 6:
                	System.out.println("Please enter new password: ");
                	String newpassword = scanner.nextLine();
                	coordinator.setPassword(newpassword);
                	System.out.println("Password changed!");
                    break;
                    
                	System.out.println("Please enter new password: ");
                	String newpassword = scanner.nextLine();
                	student.setPassword(newpassword);
           
                	System.out.println("Password changed!");
                    break;*/
                case 6:
                	System.out.println("Please enter new password: ");
                	String newpassword = scanner.nextLine();
                	
                	coordinator.setPassword(newpassword); // DOESNT CHANGE TO NEW PASSWORD
           
                	System.out.println("Password changed!");
                	//System.out.println("Password: " + newpassword);
                	System.out.println("Please relogin.");
                	logout = true; // force logout to verify effect
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
/*
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
    }*/
}
