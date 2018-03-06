package accounts;

import db.DBService;

import java.util.Map;
import java.util.HashMap;

public class AccountService {
    private final DBService dbService;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        dbService = new DBService();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile newUser) {
        try {
            dbService.addUser(newUser.getLogin(), newUser.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserProfile getUserByLogin(String login) {
        try {
            return dbService.getUserByLogin(login).getUserProfile();
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
            return null;
        }
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }

}
