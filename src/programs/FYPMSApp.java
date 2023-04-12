package programs;

import java.io.*;
import java.util.*;
import managers.*;
import models.*;
import serializers.*;

public class FYPMSApp {
    // Attributes to store loaded data
	protected static StudentManager studentManager;
	protected static SupervisorManager supervisorManager;
	protected static ProjectManager projectManager;
	protected static FYPCoordinatorManager fypCoordinatorManager;
	protected static RequestManager requestManager;

    // Constructor
    public FYPMSApp() {
        studentManager = new StudentManager();
        supervisorManager = new SupervisorManager();
        projectManager = new ProjectManager();
        fypCoordinatorManager = new FYPCoordinatorManager();
        requestManager = new RequestManager();
    }
    /*
    public SupervisorManager getSupervisorManager() {
    	return supervisorManager;
    }*/

    public static void main(String[] args) {
        FYPMSApp app = new FYPMSApp();
        app.loadData();
        app.run();
    }

    // Load data from files
    private void loadData() {
        // Load students
        List<Student> students = StudentSerializer.readStudentsFromFile("database/studentList.csv");
        for (Student student : students) {
            studentManager.addStudent(student);
        }

        // Load supervisors
        List<Supervisor> supervisors = SupervisorSerializer.readSupervisorsFromFile("database/facultyList.csv");
        for (Supervisor supervisor : supervisors) {
            supervisorManager.addSupervisor(supervisor);
        }

        // Load projects
        List<Project> projects = ProjectSerializer.readProjectsFromFile("database/projects.csv");
        for (Project project : projects) {
            projectManager.addProject(project);
        }

        // Load FYP coordinator
        FYPCoordinator coordinator = FYPCoordinatorSerializer.readCoordinatorFromFile("database/fypCoordinator.csv");
        fypCoordinatorManager.setCoordinator(coordinator, supervisorManager);
    }

    // The main loop of the application
    private void run() {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
        	boolean isValidInput = false;
            System.out.println("\nWelcome to FYP Management System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Please choose an option: ");
            
            int choice=0;
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
                    login();
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
    
    private void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your userID (CASE-SENSITIVE): ");
        String userID = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Check if user is a student
        if (studentManager.getStudent(userID) != null) {
            Student student = studentManager.getStudent(userID);

            if (student.authenticate(password)) {
                System.out.println("Login successful!");
                // Allow user to change password or perform other student-related tasks
                StudentMenu.displayMenu(student);
            } else {
                System.out.println("Invalid password.");
            }
        }
        // Check if user is a supervisor
        else if (supervisorManager.getSupervisor(userID) != null) {
            Supervisor supervisor = supervisorManager.getSupervisor(userID);

            if (supervisor.authenticate(password)) {
                System.out.println("Login successful!");
                

                // Check if the user is also the FYP coordinator
                FYPCoordinator coordinator = fypCoordinatorManager.getCoordinator();
                
                if (fypCoordinatorManager.getCoordinator().getUserID().equals(userID)) {
                	System.out.println("Login as: ");
                	System.out.println("1. FYP Coordinator");
                	System.out.println("2. Supervisor");
                	int selection = scanner.nextInt();
                	scanner.nextLine();
                	
                	if(selection==1) {
	                    System.out.println("Welcome, FYP coordinator!");
	                    FYPCoordinatorMenu.displayMenu(coordinator);
	                    // Allow user to perform FYP coordinator-related tasks
                	} else {
                		System.out.println("Welcome, Supervisor!");
                        SupervisorMenu.displayMenu(supervisor);
                    	// Allow user to perform supervisor-related tasks
                	}
                } else {
                    System.out.println("Welcome, Supervisor!");
                    SupervisorMenu.displayMenu(supervisor);
                	// Allow user to perform supervisor-related tasks
                }
            } else {
                System.out.println("Invalid password.");
            }
        } else {
            System.out.println("Invalid userID.");
        }
    }

}
// push test 1 2 3 30 mar 16:10