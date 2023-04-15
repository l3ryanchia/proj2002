
package programs;

import java.util.Scanner;
import java.util.Map;

import models.*;
import models.Project.Status;
import models.Request.UserType;

public class SupervisorMenu {
    protected static void displayMenu(Supervisor supervisor) {
        boolean logout = false;
        Scanner scanner = new Scanner(System.in);

        while (!logout) {
        	boolean isValidInput = false;
            int choice = 0;
            System.out.println("\nWelcome to FYP Management System - Supervisors");
            System.out.println("1. Create a new project");
            System.out.println("2. View my projects");
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
                	createNewProject(supervisor, scanner);
                    break;
                    
                case 2:
                	viewProjects(supervisor, scanner);
                    break;
                    
                case 3:
                    viewPendingRequests(supervisor, scanner);
                    break;
                    
                case 4:
                    viewRequestHistory(supervisor);
                    break;
                    
                case 5:
                    logout = true;
                    break;
                
                case 6:
                	System.out.println("Please enter new password: ");
                	String newpassword = scanner.nextLine();
                	supervisor.setPassword(newpassword);
           
                	System.out.println("Password changed!");
                	System.out.println("Please relogin.");
                	logout = true; // force logout to verify effect
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public static void createNewProject(Supervisor supervisor, Scanner scanner) {
        System.out.print("Enter project title: ");
        String title = scanner.nextLine();
  
    	Project newProj = FYPMSApp.projectManager.addProject(title, supervisor);
    	
    	System.out.println("Project created successfully!");
    	if(supervisor.getNumOfAllocated() >= 2) newProj.setStatus(Status.UNAVAILABLE);
    }
    
    public static void viewProjects(Supervisor supervisor, Scanner scanner) {
    	Map <String, Project> projectsList = FYPMSApp.projectManager.getProjectList();
    	projectsList = FYPMSApp.projectManager.filterBySupervisor(projectsList, supervisor.getUserID());

    	if(FYPMSApp.projectManager.displayProjects(projectsList) == 0) {
    		System.out.println("You have no projects under your name.");
    		return;
    	}
    	
    	while(true) {
    		boolean isValidInput = false;
    		int subchoice = 0;
    		System.out.println("\n1. Modify title");
    		System.out.println("2. Transfer student");
            System.out.println("3. Back");
            System.out.print("Please choose an option: ");
            
            while (!isValidInput) {
	            try {
	            	subchoice = scanner.nextInt();
	            	scanner.nextLine(); // Consume the newline
	            	isValidInput = true;
	            } catch(Exception e) {
	            	System.out.println("Invalid choice. Please try again.");
	            	scanner.nextLine();
	            	break;
	            }
            }
            
            switch (subchoice) {
                case 1:
                	System.out.print("Please enter projectID: ");
            		String selection = scanner.nextLine();
            		
            		Project project = FYPMSApp.projectManager.getProject(selection);
            		
            		if(project == null) {
            			System.out.println("Invalild Project ID!");
            			break;
            		}
            		
            		if(!project.getSupervisor().equals(supervisor)) {
            			System.out.println("You did not submit this project!");
            			break;
            		}
            		
            		System.out.print("Please enter new title: ");
            		String newTitle = scanner.nextLine();
            			
            		project.setTitle(newTitle);
            		
                	break;
                	
                case 2:
                	System.out.print("Please enter projectID for transfer of student: ");
            		String changeID = scanner.nextLine();	
            	
            		Project selectedProj = FYPMSApp.projectManager.getProject(changeID);
            		if(!selectedProj.getSupervisor().equals(supervisor)) {
            			System.out.println("You did not submit this project!");
            			break;
            		}
            		
            		if (selectedProj.getStatus() != Status.ALLOCATED) {
        				System.out.print("This project has not been allocated to a student.");
        				break;
        			}
            		
            		System.out.print("Please enter supervisorID for replacement: ");
            		String newSupervisorID = scanner.nextLine();
            		
            		Supervisor newSup = FYPMSApp.supervisorManager.getSupervisor(newSupervisorID);
            		if (newSup == null) {
            			System.out.print("Supervisor does not exist in our system."); 
            			break;
            		}
            		
            		FYPMSApp.requestManager.addRequest(new Req_TransferStudent(newSup, selectedProj));
            		
                	break;
                case 3:
                	break;
                	
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            if(subchoice==3) break;
    	}
    }
    
    public static void viewPendingRequests(Supervisor supervisor, Scanner scanner) {
    	System.out.println("PENDING INCOMING REQUESTS:");
    	if(FYPMSApp.requestManager.checkIncoming(supervisor.getUserID(), UserType.STUDENT, true) == 0) {
    		System.out.println("You have no pending incoming request.");
    	}
    	System.out.println("PENDING OUTGOING REQUESTS:");
    	if(FYPMSApp.requestManager.checkOutgoing(supervisor.getUserID(), true) == 0) {
    		System.out.println("You have no pending outgoing request.");
    	}
    	
    	while(true) {
    		boolean isValidInput = false;
    		int subchoice = 0;
    		System.out.println("\n1. Approve a request:");
    		System.out.println("2. Reject a request:");
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
            
            models.Request request;
            switch (subchoice) {
                case 1:
                	System.out.print("Please enter requestID: ");
            		String toApprove = scanner.nextLine();
            		request = FYPMSApp.requestManager.getRequestByID(toApprove);
            		if(request == null) {System.out.print("Invalid ID."); break;}
            		if(request.getReceiverID() != supervisor.getUserID()) {System.out.print("This request is not addressed to you."); break;}
            		
            		if(request.approveRequest()) System.out.print("Request Approved!");
                	break;
                	
                case 2:
                	System.out.print("Please enter requestID: ");
            		String toReject = scanner.nextLine();
            		request = FYPMSApp.requestManager.getRequestByID(toReject);
            		if(request == null) {System.out.print("Invalid ID."); break;}
            		if(request.getReceiverID() != supervisor.getUserID()) {System.out.print("This request is not addressed to you."); break;}
            		
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
    
    public static void viewRequestHistory(Supervisor supervisor) {
    	System.out.println("INCOMING REQUEST HISTORY:");
    	if(FYPMSApp.requestManager.checkIncoming(supervisor.getUserID(), UserType.STUDENT, false) == 0) {
    		System.out.println("You have no incoming request history.");
    	}
    	System.out.println("OUTGOING REQUEST HISTORY:");
    	if(FYPMSApp.requestManager.checkOutgoing(supervisor.getUserID(), false) == 0) {
    		System.out.println("You have no outgoing request history.");
    	}
    	
    }

}