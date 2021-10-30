package ro.ubbcluj.map.Business;

import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Persistance.NetworkRepo;
import ro.ubbcluj.map.Persistance.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Service {


    private UserRepository repo;
    private NetworkRepo relationships;


    public Service(UserRepository repo, NetworkRepo relationships){
        this.repo = repo;
        this.relationships = relationships;
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

    public void addFriendship(String user1, String user2){
        relationships.addFriendship(user1, user2);
    }

    public void removeFriendship(String user1, String user2){
        relationships.removeFriendship(user1, user2);

    }

    public int getNumberOfComunities(){
        return 0;
    }


    public Collection<User> getUsers() {
        return repo.findAll();

    }
}
