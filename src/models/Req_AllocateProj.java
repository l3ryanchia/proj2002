package models;

public class Req_AllocateProj extends Request{
	
	private Student student;
	private Supervisor supervisor;
	
	public Req_AllocateProj(Student student, Supervisor supervisor, Project project) {
		super();
		this.senderID = student.getUserID();
		this.receiverID = FYPCoordinator.FYPCoordinatorID;
		this.senderType = UserType.STUDENT;
		this.receiverType = UserType.FYPCOORDINATOR;
		this.project = project;
		
		this.student = student;
		this.supervisor = supervisor;
		
		student.reserveProject(project.getProjectID());
		project.reserveProject();
	}
	
	public boolean approveRequest() {
		if(!supervisor.allocateProject(project.getProjectID())) {
			System.out.println("Failed to approve request!");
			return false;
		}
		student.allocateProject(project.getProjectID());
		//if supervisor more than 2 projs, makeUnavailable()
		project.allocateStudent(student.getUserID());
		//project.allocateSupervisor(supervisor.getName()); //?
		setRequestStatus(ReqStatus.APPROVED);
		return true;
	}
	
	public void rejectRequest() {
		student.unreserveProject();
		project.unreserveProject();
		setRequestStatus(ReqStatus.REJECTED);
	}
	
	public void displayRequestType() {
    	System.out.println("Request: Allocate Project");
	}

}
