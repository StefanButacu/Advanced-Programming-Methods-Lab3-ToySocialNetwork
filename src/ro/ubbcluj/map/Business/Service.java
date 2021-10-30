package ro.ubbcluj.map.Business;

import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Exceptions.RepoException;
import ro.ubbcluj.map.Persistance.NetworkRepo;
import ro.ubbcluj.map.Persistance.UserRepository;

import java.util.*;

public class Service {


    private final UserRepository repo;
    private final NetworkRepo relationships;


    public Service(UserRepository repo, NetworkRepo relationships){
        this.repo = repo;
        this.relationships = relationships;
    }

    /**
     * Adds a new user to the social network
     * @param userName - String
     * @param password - String
     * @param email - String
     */
    public void addUser(String userName, String password, String email) {
        // TODO
        //  -Data validation : - not empty strings, strong password ,email pattern?
        repo.add(new User(userName, password, email));
    }

    /**
     * Removes the user that has the param email
     * @param email - String
     * @throws RepoException - if the user does not exists
     *
     */
    public void removeUser(String email) {
        repo.findById(email);
        ArrayList<String> userFriends = relationships.getFriends(email);
        if (userFriends != null){
            for(int i = userFriends.size()-1 ; i >= 0; i--){
                String friend = userFriends.get(i);
                relationships.removeFriendship(email, friend);
            }
        }
        repo.delete(new User("","", email));
    }

    /**
     *
     * @return - the number of users in social network
     */
    public int getNrOfUsers(){
        return repo.getSize();
    }

    /**
     * Adds a friendship between user1 and user2
     * @param user1 - String email of user
     * @param user2 - String email of user
     */
    public void addFriendship(String user1, String user2){
        relationships.addFriendship(user1, user2);
    }

    /**
     * Removes the friendship between user1 and user2
     * @param user1 - String email of user
     * @param user2 - String email of user
     */
    public void removeFriendship(String user1, String user2){
        relationships.removeFriendship(user1, user2);

    }


    /**
     *
     * @return - int = the number of communities
     *
     */
    public int getNrOfCommunities(){
        return getCommunities().size();


    }


    /**
     *
     * @return - the number of Communities
     * A community is a network of friends
     */
    public ArrayList<ArrayList<String>> getCommunities(){

        ArrayList<ArrayList<String>> components = new ArrayList<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        for(User u: getUsers()){
            visited.put(u.getId(), Boolean.FALSE);
        }
        for(Map.Entry<String,Boolean> entry: visited.entrySet()){
            if(entry.getValue() == Boolean.FALSE){
            ArrayList<String> members= BFS(entry.getKey(), visited);
            components.add(members);
            }


        }

        return components;
    }

    /**
     *
     * @param start - User email
     * @param visited - Map of <String, Booolean> -String- userName, Boolean - visited
     * @return
     */
    ArrayList<String> BFS(String start,Map<String, Boolean> visited){
        visited.put(start, Boolean.TRUE);
        LinkedList<String> Queue = new LinkedList<>();
        Queue.add(start);
        ArrayList<String> community = new ArrayList<>();
        while(!Queue.isEmpty()){
            String currentFriend = Queue.pop();
            community.add(currentFriend);
            if (relationships.getFriends(currentFriend) != null) {

                for (String nextFriend : relationships.getFriends(currentFriend)) {
                    if (!visited.get(nextFriend)) {
                        visited.put(nextFriend, Boolean.TRUE);
                        Queue.add(nextFriend);
                    }
                }
            }
        }
        return community;

    }

    /**
     *
     * @param user - String email of user
     * @return - An ArrayList<User> contains the friends of user
     */
    public ArrayList<User> getUsersFriend(String user) {
        ArrayList<String> friendsEmails = relationships.getFriends(user);
        ArrayList<User> friends = new ArrayList<>();

        for (String s : friendsEmails) {
            User u = repo.findById(s);
            friends.add(u);

        }
        return friends;
    }


    /**
     *
     * @return - A collection of users
     */
    public Collection<User> getUsers() {
        return repo.findAll();

    }
}
