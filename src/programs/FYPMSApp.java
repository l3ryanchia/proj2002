package programs;

import java.io.*;
import java.util.*;
import managers.*;
import models.*;
import serializers.*;

public class FYPMSApp {
    // Attributes to store loaded data
    private StudentManager studentManager;
    private SupervisorManager supervisorManager;
    private ProjectManager projectManager;
    private FYPCoordinatorManager fypCoordinatorManager;

    // Constructor
    public FYPMSApp() {
        studentManager = new StudentManager();
        supervisorManager = new SupervisorManager();
        projectManager = new ProjectManager();
        fypCoordinatorManager = new FYPCoordinatorManager();
    }

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
        fypCoordinatorManager.setCoordinator(coordinator);
    }

    // The main loop of the application
    private void run() {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.println("Welcome to FYP Management System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

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

            if (student.getPassword().equals(password)) {
                System.out.println("Login successful!");
                // Allow user to change password or perform other student-related tasks
            } else {
                System.out.println("Invalid password.");
            }
        }
        // Check if user is a supervisor
        else if (supervisorManager.getSupervisor(userID) != null) {
            Supervisor supervisor = supervisorManager.getSupervisor(userID);

            if (supervisor.getPassword().equals(password)) {
                System.out.println("Login successful!");

                // Check if the user is also the FYP coordinator
                if (fypCoordinatorManager.getCoordinator().getUserID().equals(userID)) {
                    System.out.println("Welcome, FYP coordinator!");
                    // Allow user to perform FYP coordinator-related tasks
                } else {
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
