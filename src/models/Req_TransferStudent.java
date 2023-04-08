package models;

import models.Request.ReqStatus;
import models.Request.UserType;

public class Req_TransferStudent extends Request {
	//private Student student;
	private Supervisor supervisorOld;
	private Supervisor supervisorNew;
	
	public Req_TransferStudent(Supervisor supervisorOld, Supervisor supervisorNew, Project project) {
		super();
		this.senderID = supervisorOld.getUserID();
		this.receiverID = FYPCoordinator.FYPCoordinatorID;
		this.senderType = UserType.SUPERVISOR;
		this.receiverType = UserType.FYPCOORDINATOR;
		this.project = project;
		
		//this.student = student;
		this.supervisorNew = supervisorNew;
		this.supervisorOld = supervisorOld;
	}
	
	public Supervisor getSupervisorOld() {
		return supervisorOld;
	}
	
	public Supervisor getSupervisorNew() {
		return supervisorNew;
	}
	
	public boolean approveRequest() {
		//project.deallocateSupervisor();
		//project.allocateSupervisor(supervisorNew.getName());
		if(!supervisorNew.allocateProject(project.getProjectID())) {
			System.out.println("Failed to approve request!");
			return false;
		}
		supervisorOld.deallocateProject(project.getProjectID());
		
		project.setSupervisor(supervisorNew.getName());
		//project.reallocateSupervisor(supervisorNew.getName());
		
		setRequestStatus(ReqStatus.APPROVED);
		return true;
	}
	
    public void rejectRequest() {
    	setRequestStatus(ReqStatus.REJECTED);
    }
    
    public void displayRequestType() {
    	System.out.println("Request: Transfer Student");
    }
}
