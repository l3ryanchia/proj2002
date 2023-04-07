package models;

import models.Request.ReqStatus;
import models.Request.UserType;

public class Req_TransferStudent extends Request {
	//private Student student;
	private Supervisor supervisorOld;
	private Supervisor supervisorNew;
	
	public Req_TransferStudent(Supervisor supervisorOld, Supervisor supervisorNew, Project project) {
		super();
		this.senderID = supervisorNew.getUserID();
		this.receiverID = FYPCoordinator.FYPCoordinatorID;
		this.senderType = UserType.SUPERVISOR;
		this.receiverType = UserType.FYPCOORDINATOR;
		this.project = project;
		
		//this.student = student;
		this.supervisorNew = supervisorNew;
		this.supervisorOld = supervisorOld;
	}
	
	public void approveRequest() {
		project.deallocateSupervisor();
		project.allocateStudent(supervisorNew.getName());
		supervisorOld.deallocateProject(project.getProjectID());
		supervisorNew.allocateProject(project.getProjectID());
		
		setRequestStatus(ReqStatus.APPROVED);
	}
	
    public void rejectRequest() {
    	setRequestStatus(ReqStatus.REJECTED);
    }
    
    public void displayRequestType() {
    	System.out.println("Request: Transfer Student");
    }
}
