package managers;

import models.Request;

import java.util.ArrayList;
import java.util.List;

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
}
