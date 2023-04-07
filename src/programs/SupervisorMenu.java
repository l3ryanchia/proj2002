
package programs;

import java.util.Scanner;
import java.util.List;
import models.*;
import models.Project.Status;
import models.Request.UserType;

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
            int subchoice;
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                	Project newProj = supervisor.createProject();
                	FYPMSApp.projectManager.addProject(newProj);
                    break;
                    
                case 2:
                    //viewProjects(supervisor, scanner);
                	if(FYPMSApp.projectManager.displayFilter(null, supervisor.getName(), FYPMSApp.supervisorManager) == 0) {
                		System.out.println("You have no projects under your name.");
                		break;
                	}
                	
                	while(true) {
                		System.out.println("1. Modify title");
                		System.out.println("2. Transfer student");
	                    System.out.println("3. Back");
	                    System.out.print("Please choose an option: ");
	                    
	                    subchoice = scanner.nextInt();
	                    scanner.nextLine(); 
	                    switch (subchoice) {
		                    case 1:
		                    	System.out.print("Please enter projectID: ");
	                    		String selection = scanner.nextLine();
	                    		
	                    		if (supervisor.getProjIDs().contains(selection)) {
	                    			System.out.print("Please enter new title: ");
	                    			String newTitle = scanner.nextLine();
	                    			Project project = FYPMSApp.projectManager.getProject(selection);
	                    			project.setTitle(newTitle);
	                    		}
	                    		else {
	                    			System.out.println("You did not submit this project!");
	                    		}
		                    	break;
		                    	
		                    case 2:
		                    	System.out.print("Please enter projectID for transfer of student: ");
	                    		String changeID = scanner.nextLine();
	                    		System.out.print("Please enter supervisorID for replacement: ");
	                    		String supervisorNewID = scanner.nextLine();
	                    		
	                    		if (supervisor.getProjIDs().contains(changeID)) {
	                    			Project selectedProj = FYPMSApp.projectManager.getProject(changeID);
	                    			if (selectedProj.getStatus() != Status.ALLOCATED) {
	                    				System.out.print("This project is not allocated.");
	                    				break;
	                    			}
	                    			Supervisor newSup = FYPMSApp.supervisorManager.getSupervisor(supervisorNewID);
	                    			if (newSup != null) FYPMSApp.requestManager.addRequest(new Req_TransferStudent(supervisor, newSup, selectedProj));
	                    			else System.out.print("Supervisor does not exist in our system.");	
	                    		}
	                    		else System.out.println("You did not submit this project!");
		                    	break;
		                    	
		                    case 3:
		                    	break;
		                    	
		                    default:
	                            System.out.println("Invalid choice. Please try again.");
	                    }
	                    if(subchoice==3) break;
                	}
                    break;
                    
                case 3:
                    //viewPendingRequests(supervisor, scanner);
                	if(FYPMSApp.requestManager.checkIncoming(supervisor.getUserID(), UserType.STUDENT, true) == 0) {
                		System.out.println("You have no pending request");
                		break;
                	}
                	
                	while(true) {
                		System.out.println("1. Approve request:");
                		System.out.println("2. Reject request:");
	                    System.out.println("3. Back");
	                    System.out.print("Please choose an option: ");
	                    
	                    subchoice = scanner.nextInt();
	                    scanner.nextLine(); 
	                    models.Request request;
	                    switch (subchoice) {
		                    case 1:
		                    	System.out.print("Please enter requestID: ");
	                    		String toApprove = scanner.nextLine();
	                    		request = FYPMSApp.requestManager.getRequestByID(toApprove);
	                    		if(request == null) System.out.print("Invalid ID.");
	                    		if(request.getReceiverID() != supervisor.getUserID()) System.out.print("This request is not addressed to you.");
	                    		request.approveRequest();
		                    	break;
		                    	
		                    case 2:
		                    	System.out.print("Please enter requestID: ");
	                    		String toReject = scanner.nextLine();
	                    		request = FYPMSApp.requestManager.getRequestByID(toReject);
	                    		if(request == null) System.out.print("Invalid ID.");
	                    		if(request.getReceiverID() != supervisor.getUserID()) System.out.print("This request is not addressed to you.");
	                    		request.rejectRequest();
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
                    //viewRequestHistory(supervisor, scanner);
                	//FYPMSApp.requestManager.checkIncoming(supervisor.getUserID(), UserType.STUDENT, true);
                	FYPMSApp.requestManager.checkIncoming(supervisor.getUserID(), UserType.STUDENT, false);
                	//FYPMSApp.requestManager.checkOutgoing(supervisor.getUserID(), true);
                	FYPMSApp.requestManager.checkOutgoing(supervisor.getUserID(), false);
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
        
