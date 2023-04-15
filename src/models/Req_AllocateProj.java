package models;

import managers.FYPCoordinatorManager;

public class Req_AllocateProj extends Request{
	
	private Student student;//remove - i think shouldnt remove cause need reserve project (14/4/2023)
	//private Supervisor supervisor; //remove
	
	public Req_AllocateProj(Student student, Project project) {
		super();
		//this.student = student;
		//this.receiverID = FYPCoordinator.FYPCoordinatorID;
		this.senderType = UserType.STUDENT;
		this.receiverType = UserType.FYPCOORDINATOR;
		this.project = project;
		
		this.student = student;
		//this.supervisor = supervisor;
		
		project.reserveProject(student);
		student.reserveProject(project.getProjectID());
		
	}
/*	
	public Supervisor getSupervisor() {
		return this.project.getSupervisor();
	}
*/	
	public String getSenderID() {
		return this.student.getUserID();
	}
	
    public String getReceiverID() {
    	//return FYPMSApp.fypCoordinatorManager.getCoordinator().getUserID(); //think about this, need import manager and need global access
    	//return FYPCoordinator.FYPCoordinatorID
    	return null;
    }

	public boolean approveRequest() {
		if(!this.checkPending()) return false;
		if(!this.project.getSupervisor().allocateProject(project.getProjectID())) {
			System.out.println("Failed to approve request!");
			return false;
		}
		this.project.getStudent().allocateProject();
		project.allocateStudent();
		setRequestStatus(ReqStatus.APPROVED);
		return true;
	}
	
	public boolean rejectRequest() {
		if(!this.checkPending()) return false;
		this.project.getStudent().unreserveProject();
		project.unreserveProject();
		setRequestStatus(ReqStatus.REJECTED);
		return true;
	}
	
	protected void displayRequestType() {
    	System.out.println("Request: Allocate Project");
	}

}
