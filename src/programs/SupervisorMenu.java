
package programs;

import java.util.Scanner;
import java.util.List;
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
        	int subchoice;
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
	            	//System.out.println("Invalid choice. Please try again.");
	            	scanner.nextLine();
	            	break;
	            }
            }

            switch (choice) {
                case 1:
                	createNewProject(supervisor);
                	/*Project newProj = supervisor.createProject();
                	FYPMSApp.projectManager.addProject(newProj);
                	if(supervisor.getNumOfAllocated() >= 2) FYPMSApp.projectManager.makeUnavailable(supervisor, FYPMSApp.requestManager);*/
                    break;
                    
                case 2:
                	viewProject(supervisor, scanner);
                	/*Map <String, Project> projectsList = FYPMSApp.projectManager.getProjectList();
                	projectsList = FYPMSApp.projectManager.filterBySupervisor(projectsList, supervisor.getName());

                	if(FYPMSApp.projectManager.displayProjects(projectsList) == 0) {
                		System.out.println("You have no projects under your name.");
                		break;
                	}
                	
                	while(true) {
                		isValidInput = false;
                		subchoice = 0;
                		System.out.println("1. Modify title");
                		System.out.println("2. Transfer student");
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
	                    
	                    switch (subchoice) {
		                    case 1:
		                    	System.out.print("Please enter projectID: ");
	                    		String selection = scanner.nextLine();
	                    		
	                    		if (supervisor.getProjIDs().contains(selection)) { //or if project.getSupervisor() is him, then dunnid the ProjID list?
	                    			System.out.print("Please enter new title: ");
	                    			String newTitle = scanner.nextLine();
	                    			Project project = FYPMSApp.projectManager.getProject(selection);
	                    			project.setTitle(newTitle);
	                    		}
	                    		else {
	                    			System.out.println("You did not submit this project!");
	                    		}
		                    	break;
		                    	
		                    case 2: // reorganised this part to improve readability
		                    	
		                    	System.out.print("Please enter projectID for transfer of student: ");
	                    		String changeID = scanner.nextLine(); //can someone help add the exception handling here	
	                    	
	                    		Project selectedProj = FYPMSApp.projectManager.getProject(changeID);
	                    		if(!selectedProj.getSupervisor().equals(supervisor.getName())) {
	                    			System.out.println("You did not submit this project!");
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
	                    		
	                    		/*
	                    		if (supervisor.getProjIDs().contains(changeID)) {
	                    			
	                    			if (selectedProj.getStatus() != Status.ALLOCATED) {
	                    				System.out.print("This project has not been allocated to a student.");
	                    				break;
	                    			}
	                    			Supervisor newSup = FYPMSApp.supervisorManager.getSupervisor(supervisorNewID);
	                    			if (newSup != null) FYPMSApp.requestManager.addRequest(new Req_TransferStudent(supervisor, newSup, selectedProj));
	                    			else System.out.print("Supervisor does not exist in our system.");	
	                    		}
	                    		else System.out.println("You did not submit this project!");
	                    		**end/
	                    		
		                    	break;
		                    case 3:
		                    	break;
		                    	
		                    default:
	                            System.out.println("Invalid choice. Please try again.");
	                    }
	                    if(subchoice==3) break;
                	}*/
                    break;
                    
                case 3:
                    viewPendingRequests(supervisor, scanner);
                	/*if(FYPMSApp.requestManager.checkIncoming(supervisor.getUserID(), UserType.STUDENT, true) == 0) {
                		System.out.println("You have no pending incoming request.");
                	}
                	if(FYPMSApp.requestManager.checkOutgoing(supervisor.getUserID(), true) == 0) {
                		System.out.println("You have no pending outgoing request.");
                	}
                	
                	
                	while(true) {
                		isValidInput = false;
                		subchoice = 0;
                		System.out.println("1. Approve a request:");
                		System.out.println("2. Reject a request:");
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
                	}*/
                    break;
                    
                case 4:
                    viewRequestHistory(supervisor);
                	//FYPMSApp.requestManager.checkIncoming(supervisor.getUserID(), UserType.STUDENT, true);
                	if ((FYPMSApp.requestManager.checkIncoming(supervisor.getUserID(), UserType.STUDENT, false) == 0) && (FYPMSApp.requestManager.checkOutgoing(supervisor.getUserID(), false) == 0)) {
                		System.out.println("Request history is empty.");
                	}
                	
                	//add outgoing history
                	
                	//FYPMSApp.requestManager.checkOutgoing(supervisor.getUserID(), true);
                    break;
                    
                case 5:
                    logout = true;
                    break;
                
                case 6:
                	System.out.println("Please enter new password: ");
                	String newpassword = scanner.nextLine();
                	supervisor.setPassword(newpassword);
           
                	System.out.println("Password changed!");
                	//System.out.println("Password: " + newpassword); // supervisor works
                	System.out.println("Please relogin.");
                	logout = true; // force logout to verify effect
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public static void createNewProject(Supervisor supervisor) {
    	Project newProj = supervisor.createProject();
    	FYPMSApp.projectManager.addProject(newProj);
    	if(supervisor.getNumOfAllocated() >= 2) FYPMSApp.projectManager.makeUnavailable(supervisor, FYPMSApp.requestManager);

    }
    
    public static void viewProject(Supervisor supervisor, Scanner scanner) {
    	//Scanner scanner = new Scanner(System.in);
    	Map <String, Project> projectsList = FYPMSApp.projectManager.getProjectList();
    	projectsList = FYPMSApp.projectManager.filterBySupervisor(projectsList, supervisor.getName());

    	if(FYPMSApp.projectManager.displayProjects(projectsList) == 0) {
    		System.out.println("You have no projects under your name.");
    		return;
    	}
    	
    	while(true) {
    		boolean isValidInput = false;
    		int subchoice = 0;
    		System.out.println("1. Modify title");
    		System.out.println("2. Transfer student");
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
            
            switch (subchoice) {
                case 1:
                	System.out.print("Please enter projectID: ");
            		String selection = scanner.nextLine();
            		
            		if (supervisor.getProjIDs().contains(selection)) { //or if project.getSupervisor() is him, then dunnid the ProjID list?
            			System.out.print("Please enter new title: ");
            			String newTitle = scanner.nextLine();
            			Project project = FYPMSApp.projectManager.getProject(selection);
            			project.setTitle(newTitle);
            		}
            		else {
            			System.out.println("You did not submit this project!");
            		}
                	break;
                	
                case 2: // reorganised this part to improve readability
                	
                	System.out.print("Please enter projectID for transfer of student: ");
            		String changeID = scanner.nextLine(); //can someone help add the exception handling here	
            	
            		Project selectedProj = FYPMSApp.projectManager.getProject(changeID);
            		if(!selectedProj.getSupervisor().equals(supervisor.getName())) {
            			System.out.println("You did not submit this project!");
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
            		
            		/*
            		if (supervisor.getProjIDs().contains(changeID)) {
            			
            			if (selectedProj.getStatus() != Status.ALLOCATED) {
            				System.out.print("This project has not been allocated to a student.");
            				break;
            			}
            			Supervisor newSup = FYPMSApp.supervisorManager.getSupervisor(supervisorNewID);
            			if (newSup != null) FYPMSApp.requestManager.addRequest(new Req_TransferStudent(supervisor, newSup, selectedProj));
            			else System.out.print("Supervisor does not exist in our system.");	
            		}
            		else System.out.println("You did not submit this project!");
            		*/
            		
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
    	//Scanner scanner = new Scanner(System.in);
    	if(FYPMSApp.requestManager.checkIncoming(supervisor.getUserID(), UserType.STUDENT, true) == 0) {
    		System.out.println("You have no pending incoming request.");
    	}
    	if(FYPMSApp.requestManager.checkOutgoing(supervisor.getUserID(), true) == 0) {
    		System.out.println("You have no pending outgoing request.");
    	}
    	
    	if((FYPMSApp.requestManager.checkOutgoing(supervisor.getUserID(), true) == 0) && (FYPMSApp.requestManager.checkIncoming(supervisor.getUserID(), UserType.STUDENT, true) == 0))
    		return;
    	
    	while(true) {
    		boolean isValidInput = false;
    		int subchoice = 0;
    		System.out.println("1. Approve a request:");
    		System.out.println("2. Reject a request:");
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
    	if ((FYPMSApp.requestManager.checkIncoming(supervisor.getUserID(), UserType.STUDENT, false) == 0) && (FYPMSApp.requestManager.checkOutgoing(supervisor.getUserID(), false) == 0)) {
    		System.out.println("Request history is empty.");
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
        
