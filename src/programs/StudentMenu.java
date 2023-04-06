package programs;

import java.util.Scanner;

import models.*;
import models.Student.StudentStatus;

public class StudentMenu {
    protected static void displayMenu(Student student) {
    	boolean logout = false;
    	Scanner scanner = new Scanner(System.in);
    	
        while (!logout) {
            System.out.println("\nWelcome to FYP Management System - Students");
            System.out.println("1. View all available projects");
            System.out.println("2. View registered project");
            System.out.println("3. View request history");
            System.out.println("4. Change password");
            System.out.println("5. Logout");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            
            int subchoice;
            switch (choice) {
                case 1: //view all projects
                	if(student.getStatus()==StudentStatus.REGISTERED) {
                		System.out.println("You have already registered a project! Unable to view available projects.");
                		break;
                	}
                	
                	FYPMSApp.projectManager.displayAllAvailableProjects(FYPMSApp.supervisorManager);
                	while(true) {
                		System.out.println("1. Request for a project");
	                    System.out.println("2. Back");
	                    System.out.print("Please choose an option: ");
	                    
	                    subchoice = scanner.nextInt();
	                    scanner.nextLine(); 
	                    switch (subchoice) {
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
	                    		Supervisor selectedSup = FYPMSApp.supervisorManager.getSupervisor(FYPMSApp.supervisorManager.getSupervisorID(selectedProj.getSupervisor()));
	                    		FYPMSApp.requestManager.addRequest(new Req_AllocateProj(student, selectedSup, selectedProj));
	                    		break;
	                    	case 2:
	                    		break;
	                    	default:
	                            System.out.println("Invalid choice. Please try again.");
	                    } 
	                    if(subchoice==2) break;
                	}      
                    break;
                case 2: //display registered project
                	if(student.getStatus()!=StudentStatus.REGISTERED) {
                		System.out.println("No project registered!");
                		break;
                	}
                	
                	Project registeredProj = FYPMSApp.projectManager.getProject(student.getProject());
                	registeredProj.displayProject();
                	
                	while(true) {
	                	System.out.println("1. Request to change title");
	                    System.out.println("2. Request to deregister FYP");
	                    System.out.println("3. Back");
	                    System.out.print("Please choose an option: ");
	                    
	                    subchoice = scanner.nextInt();
	                    scanner.nextLine();
	                    
	                    switch (subchoice) {
	                    	case 1: //change title
	                    		System.out.print("Please enter new title: ");
	                    		String newtitle = scanner.nextLine();
	                    		
	                    		FYPMSApp.requestManager.addRequest(new Req_ChangeTitle(registeredProj, newtitle));
	                    		break;
	                    	case 2: //deregister project
	                    		Supervisor registeredSup = FYPMSApp.supervisorManager.getSupervisor(registeredProj.getSupervisor());
	                    		FYPMSApp.requestManager.addRequest(new Req_DeallocateProj(student, registeredSup, registeredProj));
	                    		break;
	                    	case 3:
	                    		break;
	                    	default:
	                            System.out.println("Invalid choice. Please try again.");
	                    } 
	                    if(subchoice==3) break;
                	}
                    break;
                case 3://view request history
                    //FYPMSApp.requestManager.displayStudentRequests();
                	FYPMSApp.requestManager.checkOutgoing(student.getUserID(), false);
                    break;
                case 4: //change password
                	System.out.println("Please enter new password: ");
                	String newpassword = scanner.nextLine();
                	student.setPassword(newpassword);
                	System.out.println("Password changed!");
                    break;
                case 5:
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
