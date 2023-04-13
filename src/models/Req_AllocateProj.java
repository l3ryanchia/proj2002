package models;

public class Req_AllocateProj extends Request{
	
	private Student student;//remove - i think shouldnt remove cause need reserve project (14/4/2023)
	//private Supervisor supervisor; //remove
	
	public Req_AllocateProj(Student student, Project project) {//pass the studentID
		super();
		this.student = student;
		//this.receiverID = FYPCoordinator.FYPCoordinatorID;
		this.senderType = UserType.STUDENT;
		this.receiverType = UserType.FYPCOORDINATOR;
		this.project = project;
		
		//this.student = student;
		//this.supervisor = supervisor;
		
		student.reserveProject(project.getProjectID());
		project.reserveProject(student); //change
	}
	
	public Supervisor getSupervisor() {
		return this.project.getSupervisor();
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
		if(!this.project.getSupervisor().allocateProject(project.getProjectID())) {
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
	
	public boolean rejectRequest() {
		if(!this.checkPending()) return false;
		student.unreserveProject();
		project.unreserveProject();
		setRequestStatus(ReqStatus.REJECTED);
		return true;
	}
	
	protected void displayRequestType() {
    	System.out.println("Request: Allocate Project");
	}

}
