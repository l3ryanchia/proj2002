package models;

public class Req_ChangeTitle extends Request {
	
	private String newTitle;
	
	public Req_ChangeTitle(Project project, String newTitle) {
		super();
		this.senderID = project.getStudent();
		this.receiverID = project.getSupervisor();
		this.senderType = UserType.STUDENT;
		this.receiverType = UserType.SUPERVISOR;
		this.project = project;
		
		this.newTitle = newTitle;
	}
	
	public void approveRequest() {
		project.setTitle(newTitle);
		setRequestStatus(ReqStatus.APPROVED);
	}
	
	public void rejectRequest() {
		setRequestStatus(ReqStatus.REJECTED);
	}
	
	public void displayRequestType() {
    	System.out.println("Request: Change Title");
	}
	
	public void displayAdditionalInfo() {
    	System.out.println("Proposed Title: " + this.newTitle);
    };
	
}
