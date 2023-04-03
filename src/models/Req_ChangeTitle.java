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
	
	public void displayRequest() {
    	System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Request: Change Title - "+ this.getRequestStatus());
		System.out.println("SenderID: " + this.senderID);
		System.out.println("ReceiverID: " + this.receiverID);
		//System.out.println("Status: " + this.getRequestStatus());
    	System.out.printf("%10s %85s %25s \n", "PROJECT ID", "PROJECT TITLE", "SUPERVISOR NAME");
    	System.out.printf("%10s %85s %25s \n", this.project.getProjectID(), this.project.getTitle(), this.project.getSupervisor());
    	System.out.println("Proposed Title: " + this.newTitle);
    	System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
	}
	
}
