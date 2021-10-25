package ro.ubbcluj.map.Business;

import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Persistance.UserRepository;

import java.util.ArrayList;

public class Service {


    private UserRepository repo;

    public Service(UserRepository repo){
        this.repo = repo;
    }

    public void addUser(String userName, String password, String email) throws Exception {
        // TODO
        //  -Data validation : - not empty strings, strong password ,email pattern?
        repo.add(new User(userName, password, email));
    }


    public int getNrOfUsers(){
        return repo.getSize();
    }


    public ArrayList<User> getUsers() {
        return repo.getUsers();

    }
}
