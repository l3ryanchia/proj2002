package models;

public class Req_DeallocateProj extends Request{
	
	private Student student;
	//private Supervisor supervisor;//remove
	
	public Req_DeallocateProj(Student student, Project project) {
		super();
		//this.senderID = student.getUserID();
		//this.receiverID = FYPCoordinator.FYPCoordinatorID;
		this.senderType = UserType.STUDENT;
		this.receiverType = UserType.FYPCOORDINATOR;
		this.project = project;
		
		this.student = student;
		//this.supervisor = supervisor;
	}
	
	public Supervisor getSupervisor() { //need?
		return this.project.getSupervisor();
	}
	
	//getstudent
	public Student getSender() {
		return this.student;
	}
	
	public String getSenderID() {
		return this.student.getUserID();
	}
	
    public String getReceiverID() {
    	return null;
    }
	
	public boolean approveRequest() {
		if(!this.checkPending()) return false;
		this.project.getStudent().deallocateProject();
		this.project.getSupervisor().deallocateProject(project.getProjectID());
		project.deallocateStudent();
		setRequestStatus(ReqStatus.APPROVED);
		return true;
	}
	
	public boolean rejectRequest() {
		if(!this.checkPending()) return false;
		setRequestStatus(ReqStatus.REJECTED);
		return true;
	}
	
	protected void displayRequestType() {
		System.out.println("Request: Deallocate Project");
	}

}
