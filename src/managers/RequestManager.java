package managers;

import models.*;
import models.Request.UserType;

import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

public class RequestManager {
    private List<Request> requests; //change hash map??

    public RequestManager() {
        requests = new ArrayList<>();
    }

    //get request by request ID
    //get request by project ID
    
    public Request getRequest(int index) {
        return requests.get(index);
    }
    
    public Request getRequestByID(String requestID) {
    	Request output=null;
    	for(int i=0; i<requests.size(); i++) {
    		if(requests.get(i).getRequestID().equals(requestID)) {
    			output = requests.get(i);
    			break;
    		}
    	}
        return output;
    }
    
    public List<Request> getRequestByProjID(String projID) {
    	List <Request> output = new ArrayList<>();
    	for(int i=0; i<requests.size(); i++) {
    		if(requests.get(i).getProject().getProjectID().equals(projID)) {
    			output.add(requests.get(i));
    		}
    	}
        return output;
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    public int checkIncoming(String receiverID, models.Request.UserType sender, boolean pending) {			//1: stu  to sup | 2: stu to FYPcoord | 3: sup to FYPcoord
    	int count=0;
    	for (int i=0; i<requests.size(); i++) {
			if ((requests.get(i).getSenderType() == sender) && (requests.get(i).getReceiver().equals(receiverID))) {
				if(pending && requests.get(i).getRequestStatus() != models.Request.ReqStatus.PENDING) continue;
				requests.get(i).displayRequest();
				count++;
				continue;
			}
		}
    	return count;
    }

    public int checkOutgoing(String senderID, boolean pending) { 
    	int count = 0;
    	for (int i=0; i<requests.size(); i++) {
    		if (requests.get(i).getSenderID().equals(senderID)) {
   				if(pending && requests.get(i).getRequestStatus() != models.Request.ReqStatus.PENDING) continue;
    			requests.get(i).displayRequest();
    			count++;
   			}
   		}
    	return count;
    }
    
    public int displayRequestsFYPCoordinator(models.Request.UserType sender, boolean pending) {
    	int count = 0;
    	for (int i=0; i<requests.size(); i++) {
    		if ((requests.get(i).getSenderType() == sender) && requests.get(i).getReceiverType() == UserType.FYPCOORDINATOR) {
   				if(pending && requests.get(i).getRequestStatus() != models.Request.ReqStatus.PENDING) continue;
    			requests.get(i).displayRequest();
    			count++;
   			}
   		}
    	return count;
    }
    
    
}
