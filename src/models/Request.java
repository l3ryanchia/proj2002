package models;

public class Request {
    private String requestID;
    private User sender;
    private User receiver;
    private String type;
    private String status;

    public Request(String requestID, User sender, User receiver, String type) {
        this.requestID = requestID;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.status = "pending";
    }

    // Getters and Setters
    // ...
}
