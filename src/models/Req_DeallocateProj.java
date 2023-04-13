package models;

public class Req_DeallocateProj extends Request{
	
	//private Student student;//remove
	//private Supervisor supervisor;//remove
	
	public Req_DeallocateProj(Student student, Supervisor supervisor, Project project) {
		super();
		//this.senderID = student.getUserID();
		//this.receiverID = FYPCoordinator.FYPCoordinatorID;
		this.senderType = UserType.STUDENT;
		this.receiverType = UserType.FYPCOORDINATOR;
		this.project = project;
		
		//this.student = student;
		//this.supervisor = supervisor;
	}
	
	public Supervisor getSupervisor() {
		return this.project.getSupervisor();
	}
	
	//getstudent
	public Student getSender() {
		return this.project.getStudent();
	}
	
	public  String getSenderID() {
		return this.project.getStudent().getUserID();
		//return this.student.getUserID();	or this??
	}
	
    public String getReceiverID() {
    	//return FYPcoordinatorID
    }
	
	public boolean approveRequest() {
		if(!this.checkPending()) return false;
		this.project.getStudent().deallocateProject(project.getProjectID());
		//reverse of: supervisor.addProj(project.getProjectID());
		project.deallocateStudent();
		this.project.getSupervisor().deallocateProject(project.getProjectID());
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
