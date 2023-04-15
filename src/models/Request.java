package models;

public abstract class Request {
	public enum ReqStatus {PENDING, APPROVED, REJECTED}
	public enum UserType {STUDENT, SUPERVISOR, FYPCOORDINATOR};
	protected static int requestIDCounter=1;
	
    private String requestID;
    private ReqStatus status;
    protected UserType senderType;
    protected UserType receiverType;
    protected Project project;

    public Request() {
        this.requestID = "R" + requestIDCounter;
        this.status = ReqStatus.PENDING;
        requestIDCounter++;
    }

    public String getRequestID() {
    	return this.requestID;
    }
    
    public UserType getSenderType() {
    	return this.senderType;
    }
    
    public UserType getReceiverType() {
    	return this.receiverType;
    }
    
	public Project getProject() {
		return this.project;
	}
    
    public ReqStatus getRequestStatus() {
    	return this.status;
    }
    
    protected boolean checkPending() {
    	if(this.getRequestStatus() != ReqStatus.PENDING) {
    		System.out.println(this.getRequestID() + " - Request has already been processed!");
    		return false;
    	} else return true;
    }
    
    protected void setRequestStatus(ReqStatus state) {
    	this.status = state;
    }
    
    public abstract String getSenderID();
    public abstract String getReceiverID();
    
    public String getReceiver() {
    	if(this.getReceiverType() == UserType.FYPCOORDINATOR) return "FYP COORDINATOR";
    	else return getReceiverID();
    };    
    
    public abstract boolean approveRequest();
    public abstract boolean rejectRequest();

    protected abstract void displayRequestType();
    protected void displayAdditionalInfo() {};
    protected void printNew() {
    	if(this.getRequestStatus()==ReqStatus.PENDING) System.out.println("NEW!!");
    }
    
    public void displayRequest() {
    	System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    	this.printNew();
    	System.out.println("RequestID: " + this.getRequestID() + " - " + this.getRequestStatus()); 
    	this.displayRequestType(); //may vary for each subclass
		System.out.println("Sender: " + this.getSenderID() + " || Receiver: " + this.getReceiver());
    	System.out.printf("%10s %85s %25s \n", "PROJECT ID", "PROJECT TITLE", "SUPERVISOR NAME");
    	System.out.printf("%10s %85s %25s \n", this.project.getProjectID(), this.project.getTitle(), this.project.getSupervisor().getName());
    	this.displayAdditionalInfo(); //may vary for each subclass
    	System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    };
}
