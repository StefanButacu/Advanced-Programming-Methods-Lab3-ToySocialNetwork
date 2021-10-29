package ro.ubbcluj.map.Business;

import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Persistance.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Service {


    private UserRepository repo;

    public Service(UserRepository repo){
        this.repo = repo;
    }

    public void addUser(String userName, String password, String email) {
        // TODO
        //  -Data validation : - not empty strings, strong password ,email pattern?
        repo.add(new User(userName, password, email));
    }

    /**
     * Removes the user that has the param email
     * @param email - String
     * @throws IOException - if something happens with the saving in repo
     */
    public void removeUser(String email) {
        repo.delete(new User("","", email));
    }

    public int getNrOfUsers(){
        return repo.getSize();
    }


    public Collection<User> getUsers() {
        return repo.findAll();

    }
}
