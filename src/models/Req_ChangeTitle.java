package models;

public class Req_ChangeTitle extends Request {
	
	private String newTitle;
	
	public Req_ChangeTitle(Project project, String supervisorID, String newTitle) { 
		super();
		this.senderID = project.getStudent();
		this.receiverID = supervisorID;  //think of way to remove supervisorID as input
		this.senderType = UserType.STUDENT;
		this.receiverType = UserType.SUPERVISOR;
		this.project = project;
		
		this.newTitle = newTitle;
	}
	
	public boolean approveRequest() {
		if(!this.checkPending()) return false;
		project.setTitle(newTitle);
		setRequestStatus(ReqStatus.APPROVED);
		return true;
	}
	
	public boolean rejectRequest() {
		if(!this.checkPending()) return false;
		setRequestStatus(ReqStatus.REJECTED);
		return true;
	}
	
	public void displayRequestType() {
    	System.out.println("Request: Change Title");
	}
	
	public void displayAdditionalInfo() {
    	System.out.println("Proposed Title: " + this.newTitle);
    }
	
}
