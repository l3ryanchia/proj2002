package managers;

import models.Project;
import models.Request;
import models.Request.UserType;
import models.Project.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestManager {
    private List<Request> requests;

    public RequestManager() {
        requests = new ArrayList<>();
    }

    public Request getRequest(int index) {
        return requests.get(index);
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    // Other methods related to requests can be added here
    
    public void displayStudentRequests() {
    	for(int i=0; i<requests.size(); i++) {
    		if(requests.get(i).getSenderType() != UserType.STUDENT) continue;
    		requests.get(i).displayRequest();
    	}
    }
}
