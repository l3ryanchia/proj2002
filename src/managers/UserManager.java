package managers;

import models.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public User getUser(String userID) {
        return users.get(userID);
    }

    public void addUser(User user) {
        users.put(user.getUserID(), user);
    }

    public boolean authenticate(String userID, String password) {
        User user = users.get(userID);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public boolean changePassword(String userID, String newPassword) {
        User user = users.get(userID);
        if (user != null) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }
}
