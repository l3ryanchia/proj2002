package models;

import models.Request.ReqStatus;
import models.Request.UserType;

public class Req_TransferStudent extends Request {
	//private Student student;
	//private Supervisor supervisorOld;//remove
	private Supervisor supervisorNew;
	
	public Req_TransferStudent(Supervisor supervisorNew, Project project) {
		super();
		//this.senderID = supervisorOld.getUserID();
		//this.receiverID = FYPCoordinator.FYPCoordinatorID;
		this.senderType = UserType.SUPERVISOR;
		this.receiverType = UserType.FYPCOORDINATOR;
		this.project = project;
		
		//this.student = student;
		this.supervisorNew = supervisorNew;
		//this.supervisorOld = supervisorOld;
	}
	
	public Supervisor getSupervisorOld() {
		return this.project.getSupervisor();
	}
	
	public Supervisor getSupervisorNew() {
		return supervisorNew;
	}
	
	public  String getSenderID() {
		return this.project.getSupervisor().getUserID();
		//return this.student.getUserID();	or this??
	}
	
    public String getReceiverID() {
    	//return FYPcoordinatorID
    }
	
	public boolean approveRequest() {
		if(!this.checkPending()) return false;
		//project.deallocateSupervisor();
		//project.allocateSupervisor(supervisorNew.getName());
		if(!supervisorNew.allocateProject(project.getProjectID())) {
			System.out.println("Failed to approve request!");
			return false;
		}
		this.getSupervisorOld().deallocateProject(project.getProjectID());
		
		project.setSupervisor(supervisorNew);
		//project.reallocateSupervisor(supervisorNew.getName());
		
		setRequestStatus(ReqStatus.APPROVED);
		return true;
	}
	
    public boolean rejectRequest() {
    	if(!this.checkPending()) return false;
    	setRequestStatus(ReqStatus.REJECTED);
    	return true;
    }
    
    protected void displayRequestType() {
    	System.out.println("Request: Transfer Student");
    }
    
    protected void displayAdditionalInfo() {
    	System.out.println("Proposed New Supervisor: " + this.supervisorNew.getUserID());
    }
}
