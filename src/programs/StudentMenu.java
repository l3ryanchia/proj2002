package programs;

import java.util.Map;
import java.util.Scanner;

import models.*;
import models.Project.Status;
import models.Student.StudentStatus;

public class StudentMenu {
	Scanner scanner = new Scanner(System.in);
    protected static void displayMenu(Student student) {
    	boolean logout = false;
    	Scanner scanner = new Scanner(System.in);
    	
        while (!logout) {
        	boolean isValidInput = false;
        	int subchoice;
            int choice = 0;
            System.out.println("\nWelcome to FYP Management System - Students");
            System.out.println("1. View all available projects");
            System.out.println("2. View registered project");
            System.out.println("3. View request history");
            System.out.println("4. Change password");
            System.out.println("5. Logout");
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
                case 1: //view all projects
                	viewAllProjects(student);
                	/*if(student.getStatus()==StudentStatus.REGISTERED) {
                		System.out.println("You have already registered a project! Unable to view available projects.");
                		break;
                	}
                	
                	Map <String, Project> projectsList = FYPMSApp.projectManager.getProjectList();
                	projectsList = FYPMSApp.projectManager.filterByStatus(projectsList, Project.Status.AVAILABLE);
                	FYPMSApp.projectManager.displayProjects(projectsList);
                	
                	//FYPMSApp.projectManager.displayAllAvailableProjects(FYPMSApp.supervisorManager);
                	
                	while(true) {
                		isValidInput = false;
                		subchoice = 0;
                		System.out.println("1. Request for a project");
	                    System.out.println("2. Back");
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
	                    
	                    switch (subchoice) { //separate submenus into methods
	                    	case 1:
	                    		
	                    		if(student.getStatus()==Student.StudentStatus.RESERVED) {
	                    			System.out.println("You have already reserved a project!");
	                    			break;
	                    		}
	                    		
	                    		System.out.print("Please enter projectID: ");
	                    		String selection = scanner.nextLine();
	                    		
	                    		if(student.getProjectBlacklist().contains(selection)) {
	                    			System.out.println("You are not allowed to select this project!");
	                    			break;
	                    		}
	                    		
	                    		Project selectedProj = FYPMSApp.projectManager.getProject(selection);
	                    		Supervisor selectedSup = selectedProj.getSupervisor();
	                    		FYPMSApp.requestManager.addRequest(new Req_AllocateProj(student, selectedProj));
	                    		break;
	                    	case 2:
	                    		break;
	                    	default:
	                            System.out.println("Invalid choice. Please try again.");
	                    }
	                    if(subchoice==2) break;
                	}*/      
                    break;
                case 2: //display registered project
                	viewRegisteredProject(student);
                	/*if(student.getStatus()!=StudentStatus.REGISTERED) {
                		System.out.println("No project registered!");
                		break;
                	}
                	
                	Project registeredProj = FYPMSApp.projectManager.getProject(student.getProject());
                	registeredProj.displayProject();
                	
                	while(true) {
                		isValidInput = false;
                		subchoice = 0;
	                	System.out.println("1. Request to change title");
	                    System.out.println("2. Request to deregister FYP");
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
	                    	case 1: //change title
	                    		System.out.print("Please enter new title: ");
	                    		String newTitle = scanner.nextLine();
	                    		
	                    		FYPMSApp.requestManager.addRequest(new Req_ChangeTitle(registeredProj, newTitle));
	                    		break;
	                    	case 2: //deregister project
	                    		//seems abit inefficient here, to get the supervisor object need to first get the supervisor ID by getting the supervisor name
	                    		Supervisor registeredSup = registeredProj.getSupervisor();
	                    		FYPMSApp.requestManager.addRequest(new Req_DeallocateProj(student, registeredSup, registeredProj));
	                    		break;
	                    	case 3:
	                    		break;
	                    	default:
	                            System.out.println("Invalid choice. Please try again.");
	                    } 
	                    if(subchoice==3) break;
                	}*/
                    break;
                case 3://view request history
                	FYPMSApp.requestManager.checkOutgoing(student.getUserID(), false);
                    break;
                case 4: //change password
                	System.out.println("Please enter new password: ");
                	String newpassword = scanner.nextLine();
                	student.setPassword(newpassword);
           
                	System.out.println("Password changed!");
                	System.out.println("Please relogin.");
                	logout = true; // force logout to verify effect
                    break;
                case 5:
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public static void viewAllProjects(Student student) {
    	Scanner scanner = new Scanner(System.in);
    	if(student.getStatus()==StudentStatus.REGISTERED) {
    		System.out.println("You have already registered a project! Unable to view available projects.");
    		return;
    	}
    	
    	Map <String, Project> projectsList = FYPMSApp.projectManager.getProjectList();
    	projectsList = FYPMSApp.projectManager.filterByStatus(projectsList, Project.Status.AVAILABLE);
    	FYPMSApp.projectManager.displayProjects(projectsList);
    	
    	while(true) {
    		boolean isValidInput = false;
    		int subchoice = 0;
    		System.out.println("1. Request for a project");
            System.out.println("2. Back");
            System.out.print("Please choose an option: ");
            
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
            
            switch (subchoice) { //separate submenus into methods
            	case 1:
            		
            		if(student.getStatus()==Student.StudentStatus.RESERVED) {
            			System.out.println("You have already reserved a project!");
            			break;
            		}
            		
            		System.out.print("Please enter projectID: ");
            		String selection = scanner.nextLine();
            		
            		if(student.getProjectBlacklist().contains(selection)) {
            			System.out.println("You are not allowed to select this project!");
            			break;
            		}
            		
            		Project selectedProj = FYPMSApp.projectManager.getProject(selection);
            		
            		if(selectedProj.getStatus() != Status.AVAILABLE) {
            			System.out.println("You are not allowed to select this project!");
            			break;
            		}
            		
            		//Supervisor selectedSup = selectedProj.getSupervisor();
            		FYPMSApp.requestManager.addRequest(new Req_AllocateProj(student, selectedProj));
            		break;
            	case 2:
            		break;
            	default:
                    System.out.println("Invalid choice. Please try again.");
            }
            if(subchoice==2) return;
    	}
    }
    
    public static void viewRegisteredProject(Student student) {
    	Scanner scanner = new Scanner(System.in);
    	if(student.getStatus()!=StudentStatus.REGISTERED) {
    		System.out.println("No project registered!");
    		return;
    	}
    	
    	Project registeredProj = FYPMSApp.projectManager.getProject(student.getProject());
    	registeredProj.displayProject();
    	
    	while(true) {
    		boolean isValidInput = false;
    		int subchoice = 0;
        	System.out.println("1. Request to change title");
            System.out.println("2. Request to deregister FYP");
            System.out.println("3. Back");
            System.out.print("Please choose an option: ");
            
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
            	case 1: //change title
            		System.out.print("Please enter new title: ");
            		String newTitle = scanner.nextLine();
            		
            		FYPMSApp.requestManager.addRequest(new Req_ChangeTitle(registeredProj, newTitle));
            		break;
            	case 2: //deregister project
            		//seems abit inefficient here, to get the supervisor object need to first get the supervisor ID by getting the supervisor name
            		Supervisor registeredSup = registeredProj.getSupervisor();
            		FYPMSApp.requestManager.addRequest(new Req_DeallocateProj(student, registeredProj));
            		break;
            	case 3:
            		break;
            	default:
                    System.out.println("Invalid choice. Please try again.");
            } 
            if(subchoice==3) return;
    	}
    }    
}
