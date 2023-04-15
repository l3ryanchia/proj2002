package models;

public class Req_AllocateProj extends Request{
	
	private Student student;
	
	public Req_AllocateProj(Student student, Project project) {
		super();
		this.senderType = UserType.STUDENT;
		this.receiverType = UserType.FYPCOORDINATOR;
		this.project = project;
		
		this.student = student;
		
		project.reserveProject(student);
		student.reserveProject(project.getProjectID());
		
	}

	public String getSenderID() {
		return this.student.getUserID();
	}
	
    public String getReceiverID() {
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
