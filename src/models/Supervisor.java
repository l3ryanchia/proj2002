
package models;

import java.util.*;

public class Supervisor extends User {
    private ArrayList<String> projectIDs;
    private int numOfAllocated;
    private boolean coordinator;

    public Supervisor(String userID, String name) {
        super(userID, name);
        projectIDs = new ArrayList<String>();
        numOfAllocated = 0;
        coordinator = false;
    }
    
    public int getNumOfAllocated() {
    	return this.numOfAllocated;
    }
    
    public ArrayList<String> getProjIDs() {
    	return this.projectIDs;
    }
    
    public void addProject(String projectID) {
    	this.projectIDs.add(projectID);
    }
    
    public void setCoordinator(boolean bool) {
    	coordinator = bool;
    }
    
    public boolean coordinator() {
    	return coordinator;
    }
    
    public boolean allocateProject(String projID) {
    	if(this.numOfAllocated >= 2) {
    		System.out.println("Supervisor has reached maximum number of allocated projects!");
    		return false;
    	}
    	this.projectIDs.add(projID);
    	this.numOfAllocated++;
    	return true;
    }
    
    public void deallocateProject(String projID) {
    	this.numOfAllocated--;
    	this.projectIDs.remove(projID);
    }
       
}
