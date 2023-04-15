package programs;

import java.util.*;
import managers.*;
import models.*;


public class FYPMSApp {
    // Singleton instance
	private static FYPMSApp instance; 
	// Attributes to store loaded data
	protected static StudentManager studentManager;
	protected static SupervisorManager supervisorManager;
	protected static ProjectManager projectManager;
	protected static FYPCoordinatorManager fypCoordinatorManager;
	protected static RequestManager requestManager;

    // Constructor
    private FYPMSApp() {
    	studentManager = ReadCSV.loadStudentData();
        supervisorManager = ReadCSV.loadSupervisorData();
        projectManager = ReadCSV.loadProjectData(supervisorManager);
        fypCoordinatorManager = ReadCSV.loadFYPCoordinator(supervisorManager);
        requestManager = new RequestManager();
    }
    
    public static FYPMSApp getInstance() {
        if (instance == null) {
            instance = new FYPMSApp();
          }
        return instance;
    }

    public static void main(String[] args) {
        FYPMSApp app = FYPMSApp.getInstance();
        app.run();
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
	            	scanner.nextLine();
	            	break;
	            }
            }            

            switch (choice) {
                case 1:
                    login(scanner);
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
    
    private void login(Scanner scanner) {

        System.out.print("Enter your userID (CASE-SENSITIVE): ");
        String userID = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        
        if (studentManager.getStudent(userID) != null) { // Check if user is a student
            Student student = studentManager.getStudent(userID);

            if (student.authenticate(password)) {
                System.out.println("Login successful!");
                StudentMenu.displayMenu(student); // Allow user to perform student-related tasks
            } else {
                System.out.println("Invalid password.");
            }
        }
        
        else if (supervisorManager.getSupervisor(userID) != null) { // Check if user is a supervisor
            Supervisor supervisor = supervisorManager.getSupervisor(userID);

            if (supervisor.authenticate(password)) {
                System.out.println("Login successful!");

                if (fypCoordinatorManager.getCoordinator().getUserID().equals(userID)) { // Check if the user is also the FYP coordinator
                	System.out.println("Login as: ");
                	System.out.println("1. FYP Coordinator");
                	System.out.println("2. Supervisor");
                	int selection = scanner.nextInt();
                	scanner.nextLine();
                	
                	if(selection==1) { //exception handling
	                    System.out.println("Welcome, FYP coordinator!");
	                    FYPCoordinatorMenu.displayMenu(supervisor); // Allow user to perform FYP coordinator-related tasks
                	} else {
                		System.out.println("Welcome, Supervisor!");
                        SupervisorMenu.displayMenu(supervisor);
                    	
                	}
                } else {
                    System.out.println("Welcome, Supervisor!");
                    SupervisorMenu.displayMenu(supervisor); // Allow user to perform supervisor-related tasks
                }
            } else {
                System.out.println("Invalid password.");
            }
        } else {
            System.out.println("Invalid userID.");
        }
    }
}