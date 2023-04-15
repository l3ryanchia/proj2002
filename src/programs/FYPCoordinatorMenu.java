package programs;

import java.util.Scanner;
import java.util.Map;

import models.*;
import models.Request.UserType;

public class FYPCoordinatorMenu {
    protected static void displayMenu(Supervisor coordinator) {
        boolean logout = false;
        Scanner scanner = new Scanner(System.in);

        while (!logout) {
        	boolean isValidInput = false;
            int choice = 0;
            System.out.println("\nWelcome to FYP Management System - FYP Coordinator");
            System.out.println("1. View projects by status");
            System.out.println("2. Generate project details report");
            System.out.println("3. View pending requests");
            System.out.println("4. View request history");
            System.out.println("5. Logout");
            System.out.println("6. Change password");
            System.out.print("Please choose an option: ");

            while (!isValidInput) {
	            try {
	            	choice = scanner.nextInt();
	            	scanner.nextLine(); // Consume the newline
	            	isValidInput = true;
	            } catch(Exception e) {
	            	scanner.nextLine();
	            	break;
	            }
            }

            switch (choice) {
                case 1:
                	viewProjectByStatus();
                    break;
                case 2:
                	generateProjectDetails(scanner);
                    break;
                case 3:
                	viewPendingRequests(coordinator, scanner);
                    break;
                case 4:
                	viewRequestHistory(coordinator);
                    break;
                case 5:
                    logout = true;
                    break;
                case 6:
                	System.out.println("Please enter new password: ");
                	String newpassword = scanner.nextLine();
                	
                	coordinator.setPassword(newpassword); 
           
                	System.out.println("Password changed!");
                	System.out.println("Please relogin.");
                	logout = true; // force logout to verify effect
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public static void viewProjectByStatus() {
    	Map <String, Project> projectsToDisplay;
    	
    	for (Project.Status status: Project.Status.values()) {
    		System.out.println(status +" PROJECTS: ");
        	projectsToDisplay = FYPMSApp.projectManager.filterByStatus(FYPMSApp.projectManager.getProjectList(), status);
        	FYPMSApp.projectManager.displayProjects(projectsToDisplay);
    	}
    }
    
    public static void generateProjectDetails(Scanner scanner) {
    	Map <String, Project> projectsList = FYPMSApp.projectManager.getProjectList();
    	
    	int displayReport=0, filter=0;
    	while(displayReport != 2) {
    		displayReport = 0; filter = 0;
    		boolean isValidInput = false;
        	System.out.println("1. Add search filter");
        	System.out.println("2. Display project details report");

        	while (!isValidInput) {
	            try {
	            	displayReport = scanner.nextInt();
	            	scanner.nextLine(); // Consume the newline
	            	isValidInput = true;
	            } catch(Exception e) {
	            	scanner.nextLine();
	            	break;
	            }
            }
        	
        	switch(displayReport) {
        		case 1:
                	System.out.println("Filter by: ");
                	System.out.println("1. Project Status");
                	System.out.println("2. Project Supervisor");
                	System.out.println("3. Student allocated to project");
                	isValidInput = false;
                	
                	while (!isValidInput) {
        	            try {
        	            	filter = scanner.nextInt();
        	            	scanner.nextLine(); // Consume the newline
        	            	isValidInput = true;
        	            } catch(Exception e) {
        	            	scanner.nextLine();
        	            	break;
        	            }
                    }
                	
                	switch(filter) {
                		case 1:
                			String statusFilter;
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
                			String supersivorFilter;
                			System.out.println("Enter supervisor ID: ");
                			supersivorFilter = scanner.nextLine();
                			
                			projectsList = FYPMSApp.projectManager.filterBySupervisor(projectsList, supersivorFilter);
                			break;
                		case 3:
                			String studentFilter;
                			System.out.println("Enter student ID: ");
                			studentFilter = scanner.nextLine();
                			
                			projectsList = FYPMSApp.projectManager.filterByStudent(projectsList, studentFilter);
                			break;	
                		default:
                			System.out.println("Invalid input.");
                			break;
                	}
                	break;
        		case 2:
        			FYPMSApp.projectManager.displayProjects(projectsList);
        			break;
        		default: 
        			System.out.println("Invalid input.");
        	}
    	}
    }
    
    public static void viewPendingRequests(Supervisor coordinator, Scanner scanner) {
    	System.out.println("PENDING REQUESTS FROM STUDENTS:");
    	if(FYPMSApp.requestManager.displayRequestsFYPCoordinator(models.Request.UserType.STUDENT, true)==0) System.out.println("No pending requests.");
    	System.out.println("PENDING REQUESTS FROM SUPERVISORS:");
    	if(FYPMSApp.requestManager.displayRequestsFYPCoordinator(models.Request.UserType.SUPERVISOR, true)==0) System.out.println("No pending requests.");
    	while(true) {
    		boolean isValidInput = false;
    		int subchoice = 0;
    		System.out.println("\n1. Approve a request");
    		System.out.println("2. Reject a request");
            System.out.println("3. Back");
            System.out.print("Please choose an option: ");
            
            while (!isValidInput) {
	            try {
	            	subchoice = scanner.nextInt();
	            	scanner.nextLine(); // Consume the newline
	            	isValidInput = true;
	            } catch(Exception e) {
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
            		if(request.getReceiverType() != UserType.FYPCOORDINATOR) {System.out.print("This request is not addressed to you."); break;}
            		
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
            		if(request.getReceiverType() != UserType.FYPCOORDINATOR) {System.out.print("This request is not addressed to you."); break;}
            		
            		if(request.rejectRequest()) System.out.print("Request Rejected!");
            		
            		break;
            	case 3:
            		break;
            	default:
                    System.out.println("Invalid choice. Please try again.");
            } 
            if(subchoice==3) break;
    	}
    }
    
    public static void viewRequestHistory(Supervisor coordinator) {
    	FYPMSApp.requestManager.displayRequestsFYPCoordinator(UserType.STUDENT, false);
    	FYPMSApp.requestManager.displayRequestsFYPCoordinator(UserType.SUPERVISOR, false);
    }
    
}
