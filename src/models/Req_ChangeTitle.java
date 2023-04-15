package models;

public class Req_ChangeTitle extends Request {
	
	private String newTitle;
	
	public Req_ChangeTitle(Project project, String newTitle) { 
		super();

		this.senderType = UserType.STUDENT;
		this.receiverType = UserType.SUPERVISOR;
		this.project = project;
		
		this.newTitle = newTitle;
	}
	
	public  String getSenderID() {
		return this.project.getStudent().getUserID();
	}
	
    public String getReceiverID() {
    	return this.project.getSupervisor().getUserID();
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
	
	protected void displayRequestType() {
    	System.out.println("Request: Change Title");
	}
	
	protected void displayAdditionalInfo() {
    	System.out.println("Proposed Title: " + this.newTitle);
    }
	
}
