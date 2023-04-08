package models;

/*public class Request {
	public enum Status {PENDING, APPROVED, REJECTED}
	
    private String requestID;
    private User sender;
    private User receiver;
    private String type;
    private Status status;

    public Request(String requestID, User sender, User receiver, String type) {
        this.requestID = requestID;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.status = Status.PENDING;
    }

    // Getters and Setters
    // ...
}*/

public abstract class Request {
	public enum ReqStatus {PENDING, APPROVED, REJECTED}
	public enum UserType {STUDENT, SUPERVISOR, FYPCOORDINATOR};
	protected static int requestIDCounter=1;
	
    private String requestID;
    private ReqStatus status;
    protected String senderID;
    protected String receiverID;
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
    
    public String getSenderID() {
    	return this.senderID;
    }
    
    public String getReceiverID() {
    	return this.receiverID;
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
    
    protected void setRequestStatus(ReqStatus state) {
    	this.status = state;
    }
    
    public abstract boolean approveRequest();
    public abstract void rejectRequest();
    public abstract void displayRequestType();
    public void displayAdditionalInfo() {};
    
    public void displayRequest() {
    	System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    	System.out.println("RequestID: " + this.getRequestID() + " - " + this.getRequestStatus());
    	this.displayRequestType(); //may vary for each subclass
		System.out.println("SenderID: " + this.senderID + " || ReceiverID: " + this.receiverID);
    	System.out.printf("%10s %85s %25s \n", "PROJECT ID", "PROJECT TITLE", "SUPERVISOR NAME");
    	System.out.printf("%10s %85s %25s \n", this.project.getProjectID(), this.project.getTitle(), this.project.getSupervisor());
    	this.displayAdditionalInfo(); //may vary for each subclass
    	System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    };
}
