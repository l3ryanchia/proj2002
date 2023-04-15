package models;

import java.util.*;


//public class FYPCoordinator {
//    private String name;
//    private String email;
//
//    public FYPCoordinator(String name, String email) {
//        this.name = name;
//        this.email = email;
//    }
//
//    // Other methods for the FYPCoordinator class
//}
//


public class FYPCoordinator extends Supervisor {
	public static String FYPCoordinatorID;
	
    public FYPCoordinator(String userID, String name) {
        super(userID, name);
        FYPCoordinatorID = userID;
    }

    // Other methods for the FYPCoordinator class can be added here
}
