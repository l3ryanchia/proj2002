package models;

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
	
	public Supervisor getSupervisor() {
		return this.supervisor;
	}
	
	public boolean approveRequest() {
		student.deallocateProject(project.getProjectID());
		//reverse of: supervisor.addProj(project.getProjectID());
		project.deallocateStudent();
		supervisor.deallocateProject(project.getProjectID());
		setRequestStatus(ReqStatus.APPROVED);
		return true;
	}
	
	public void rejectRequest() {
		setRequestStatus(ReqStatus.REJECTED);
	}
	
	public void displayRequestType() {
		System.out.println("Request: Deallocate Project");
	}

}
