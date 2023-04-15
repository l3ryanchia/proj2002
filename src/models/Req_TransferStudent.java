package models;

import models.Request.ReqStatus;
import models.Request.UserType;

public class Req_TransferStudent extends Request {

	private Supervisor supervisorOld;//do we need only one?
	private Supervisor supervisorNew;
	
	public Req_TransferStudent(Supervisor supervisorNew, Project project) {
		super();
		this.senderType = UserType.SUPERVISOR;
		this.receiverType = UserType.FYPCOORDINATOR;
		this.project = project;
		
		this.supervisorOld = project.getSupervisor();
		this.supervisorNew = supervisorNew;
		
	}
	
	public Supervisor getSupervisorOld() {
		return supervisorOld;
		//return this.project.getSupervisor();//wrong after approved
	}
	
	public Supervisor getSupervisorNew() {
		return supervisorNew;
	}
	
	public String getSenderID() {
		return this.supervisorOld.getUserID();
	}
	
    public String getReceiverID() {
    	//return FYPcoordinatorID
    	return null;
    }
	
	public boolean approveRequest() {
		if(!this.checkPending()) return false;
		if(!supervisorNew.allocateProject(project.getProjectID())) {
			System.out.println("Failed to approve request!");
			return false;
		}
		this.getSupervisorOld().deallocateProject(project.getProjectID());
		
		project.setSupervisor(supervisorNew);
		
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
