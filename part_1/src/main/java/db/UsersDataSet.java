package db;

import javax.persistence.*;
import java.io.Serializable;

import accounts.UserProfile;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public UsersDataSet() {

    }

    public UsersDataSet(long id, String login, String password, String email) {
        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);
        this.setEmail(email);
    }

    public UsersDataSet(String login, String password) {
        this.setId(-1);
        this.setLogin(login);
        this.setPassword(password);
        this.setEmail(null);
    }

    public UserProfile getUserProfile() {
        return new UserProfile(this.login, this.password, this.email);
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
