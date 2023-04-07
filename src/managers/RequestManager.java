package managers;

import models.*;

import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

public class RequestManager {
    private List<Request> requests;

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
    
    public Request getRequestByProjID(String projID) {
    	Request output=null;
    	for(int i=0; i<requests.size(); i++) {
    		if(requests.get(i).getProject().getProjectID().equals(projID)) {
    			output = requests.get(i);
    			break;
    		}
    	}
        return output;
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    // Other methods related to requests can be added here
    /*
    public void displayStudentRequests() {					//should this be specific to a certain student? nobody can see all of students req right?
    	for(int i=0; i<requests.size(); i++) {
    		if(requests.get(i).getSenderType() != models.Request.UserType.STUDENT) continue;
    		requests.get(i).displayRequest();
    	}
    }
    */
    //FYPCoord function call: checkIncoming(FYPCoordinator.getUserID(), models.Request.UserType.STUDENT, true)
    //FYPCoord function call: checkIncoming(FYPCoordinator.getUserID(), models.Request.UserType.SUPERVISOR, true)
    
    public int checkIncoming(String receiverID, models.Request.UserType userType, boolean pending) {			//1: stu  to sup | 2: stu to FYPcoord | 3: sup to FYPcoord
    	int count=0;
    	for (int i=0; i<requests.size(); i++) {
			if ((requests.get(i).getSenderType() == userType) && (requests.get(i).getReceiverID() == receiverID)) {
				if(pending && requests.get(i).getRequestStatus() != models.Request.ReqStatus.PENDING) continue;
				requests.get(i).displayRequest();
				count++;
				continue;
			}
		}
    	return count;
    	
    	/*switch(choice){
    		case 1:
    			for (int i=0; i<requests.size(); i++) {
    				if ((requests.get(i).getSenderType() == models.Request.UserType.STUDENT) && (requests.get(i).getReceiverID() == receiverID)) {
    					requests.get(i).displayRequest();
    					continue;
    				}
    			}
    			break;
    			
    		case 2:
    			for (int i=0; i<requests.size(); i++) {
    				if ((requests.get(i).getSenderType() == models.Request.UserType.STUDENT) && (requests.get(i).getReceiverID() == receiverID)) {
    					requests.get(i).displayRequest();
    					continue;
    				}
    			}
    			break;
    			
    		case 3:
    			for (int i=0; i<requests.size(); i++) {
    				if ((requests.get(i).getSenderType() == models.Request.UserType.SUPERVISOR) && (requests.get(i).getReceiverID() == receiverID)) {
    					requests.get(i).displayRequest();
    					continue;
    				}
    			}
    			break;
    	}*/
    }
    /*
    public void checkOutgoing(String senderID, boolean pending) {		//only student and sup
    	if (pending) {
    		for (int i=0; i<requests.size(); i++) {
        		if ((requests.get(i).getSenderID() == senderID) && (requests.get(i).getRequestStatus() == models.Request.ReqStatus.PENDING)) {
       				requests.get(i).displayRequest();
      				continue;
       			}
       		}
    	}
    	else {
	    	for (int i=0; i<requests.size(); i++) {
	    		if (requests.get(i).getSenderID() == senderID) {
	    			requests.get(i).displayRequest();
	  				continue;
	   			}
	   		}
    	}
    }*/
    
    //proposed concise version
    public int checkOutgoing(String senderID, boolean pending) { 
    	int count = 0;
    	for (int i=0; i<requests.size(); i++) {
    		if (requests.get(i).getSenderID() == senderID) {
   				if(pending && requests.get(i).getRequestStatus() != models.Request.ReqStatus.PENDING) continue;
    			requests.get(i).displayRequest();
    			count++;
   			}
   		}
    	return count;
    }
    
    
    
}
