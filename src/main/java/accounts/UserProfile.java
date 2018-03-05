package accounts;

public class UserProfile {
    private final String login;
    private final String email;
    private final String password;


    public UserProfile(String login, String password, String email) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public UserProfile(String login, String password) {
        this.login = login;
        this.email = "";
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
