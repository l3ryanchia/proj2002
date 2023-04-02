package models;

import models.Request.ReqStatus;
import models.Request.UserType;

public class Req_DeallocateProj extends Request{
	
	private Student student;
	private Supervisor supervisor;
	
	public Req_DeallocateProj(Student student, Supervisor supervisor, Project project) {
		super();
		this.senderID = student.getUserID();
		this.receiverID = FYPCoordinator.FYPCoordinatorID;
		this.senderType = UserType.STUDENT;
		this.receiverType = UserType.FYPCOORDINATOR;
		this.project = project;
		
		this.student = student;
		this.supervisor = supervisor;
	}
	
	public void approveRequest() {
		student.deallocateProject(project.getProjectID());
		//reverse of: supervisor.addProj(project.getProjectID());
		project.deallocateStudent();
		setRequestStatus(ReqStatus.APPROVED);
	}
	
	public void rejectRequest() {
		setRequestStatus(ReqStatus.REJECTED);
	}
	
	public void displayRequest() {
    	System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Request: Deallocate Project - "+ this.getRequestStatus());
		System.out.println("SenderID: " + this.senderID);
		System.out.println("ReceiverID: " + this.receiverID);
    	System.out.printf("%10s %85s %25s \n", "PROJECT ID", "PROJECT TITLE", "SUPERVISOR NAME");
    	System.out.printf("%10s %85s %25s \n", this.project.getProjectID(), this.project.getTitle(), this.project.getSupervisor());
    	System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
	}

}
