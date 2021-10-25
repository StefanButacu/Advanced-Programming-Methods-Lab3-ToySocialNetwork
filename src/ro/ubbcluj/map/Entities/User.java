package ro.ubbcluj.map.Entities;

import java.util.Objects;

/**
 * User class extends Entity<ID>, where ID is String type
 * ID = User's email
 */
public class User extends Entity<String>{
    private String userName;
    private String password;


    public User(String userName, String password, String email){
        super(email);
        this.userName = userName;
        this.password = password;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
