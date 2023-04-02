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

//still finding a way to do this, thinking of using subclasses for 3 diff types of requests and storing in 2D matrix
public class Request {
	public enum Status {PENDING, APPROVED, REJECTED}
	
    private String requestID;
    private int type;
    private Status status;

    public Request(String requestID, User sender, User receiver, String type) {
        this.requestID = requestID;
        this.type = type;
        this.status = Status.PENDING;
    }

    // Getters and Setters
    // ...
}
