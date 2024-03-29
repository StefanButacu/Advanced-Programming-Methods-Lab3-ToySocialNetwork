package ro.ubbcluj.map.Business;

import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Exceptions.RepoException;
import ro.ubbcluj.map.Persistance.NetworkRepo;
import ro.ubbcluj.map.Persistance.UserRepository;
import ro.ubbcluj.map.Validators.UserValidator;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Service {


    private final UserRepository repo;
    private final NetworkRepo relationships;
    private final UserValidator userValidator = new UserValidator();

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
        User u = new User(userName, password, email);
        userValidator.validate(u);
        repo.add(u);
    }

    /**
     * Removes the user that has the param email
     * @param email - String
     * @throws RepoException - if the user does not exists
     *
     */
    public void removeUser(String email) throws IOException {
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
    public void addFriendship(String user1, String user2) throws IOException {
        relationships.addFriendship(user1, user2);
    }

    /**
     * Removes the friendship between user1 and user2
     * @param user1 - String email of user
     * @param user2 - String email of user
     */
    public void removeFriendship(String user1, String user2) throws IOException {
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
     * @return - ArrayList<Users> - with the longest DFS chain
     */
    public ArrayList<User> getTheMostSociableCommunity(){
        Integer maxTimeDFS = Integer.MIN_VALUE;
        ArrayList<User> mostSociableCommunity = new ArrayList<>();
        ArrayList<ArrayList<String>> communities = getCommunities();
        HashMap<String, Boolean> visited = new HashMap<>();
        for(User u: getUsers()){
            visited.put(u.getId(), Boolean.FALSE);
        }
        ArrayList<Integer> aux = new ArrayList<>();
        for (ArrayList<String> community : communities) {

            for (int j = 0; j < community.size(); j++) {
                // Start from every vertex from this community
                // and get the maximum time
                // of dfs
                aux.add(0);
                DFS(community.get(j), aux, visited);
                if (aux.get(0) > maxTimeDFS) {
                    maxTimeDFS = aux.get(0);
                    // tre sa torn din comunitatea i in the most social community
                    mostSociableCommunity.clear();
                    for (String userEmail : community) {
                        User u = repo.findById(userEmail);
                        mostSociableCommunity.add(u);
                    }
                }
                aux.remove(0);
            }


        }

        return mostSociableCommunity;
    }

    boolean isValid(int k, Vector<String> path){
        String user1 = path.elementAt(k);
        String user2 = path.elementAt(k-1);
        if(!relationships.getFriends(user1).contains(user2))
            return false;
        for(int i = 0 ; i < path.size(); i++)
            for(int j = i +1 ; j < path.size(); j++)
                if(path.elementAt(i).equals(path.elementAt(j)))
                    return false;
        return true;



    }


    void verifyMaxPath( Vector<String> path,ArrayList<String> longestPath){
        if(path.size() > longestPath.size()){
            longestPath.clear();
            for(int i = 0 ; i < path.size(); i++){
                longestPath.add(path.elementAt(i));
            }
        }

    }
    void backtracking(int k, String dest, Vector<String> path, ArrayList<String> longestPath){
        ArrayList<String> searchSpace = relationships.getFriends(path.elementAt(k-1));
        for(String friend: searchSpace){
            if(k < path.size())
                path.set(k, friend);
            else
                path.add(friend);
            if(isValid(k,path)){
                if(path.elementAt(k).equals(dest))
                {
                    verifyMaxPath(path,longestPath);
                }

                else
                    backtracking(k+1,dest,path,longestPath);

            }

        }

    }

    public  ArrayList<String> longestPathBetween2Nodes(String first, String second){
        Vector<String> path = new Vector<>(150);
        ArrayList<String> longestPath = new ArrayList<>();

        path.add(0,first);
        backtracking(1, second, path,longestPath);
        return longestPath;


    }

    public ArrayList<User> getTheMostSociableCommunityVersion2(){

        ArrayList<ArrayList<String>> communities = getCommunities();
        ArrayList<String> longestPath = new ArrayList<>();
        for(ArrayList<String> community: communities){
            for(int i =0 ; i < community.size(); i++){
                for(int j = i + 1; j < community.size(); j++){
                    ArrayList<String> currentPath = longestPathBetween2Nodes(community.get(i),community.get(j));
                    if(currentPath.size() > longestPath.size()){
                        longestPath.clear();
                        longestPath.addAll(currentPath);
                    }
                }
            }
        }
        ArrayList<User> longestPathUsers = new ArrayList<>();
        for(String email: longestPath){
            User u = repo.findById(email);
            longestPathUsers.add(u);
        }

        return longestPathUsers;
    }


    private void DFS(String currentUser, ArrayList<Integer> aux, Map<String, Boolean> visited) {
        visited.put(currentUser, Boolean.TRUE);
        Integer time =  aux.get(0);
        time++;
        aux.set(0,time);
        ArrayList<String> friends = relationships.getFriends(currentUser);
        if(friends != null) {
            for (String s: friends){
                if(visited.get(s) == Boolean.FALSE){
                    DFS(s, aux, visited);

                }

            }

        }
    }

    /**
     *
     * @return - the number of Communities
     * A community is a network of friends
     */
    public ArrayList<ArrayList<String>> getCommunities(){
        // bad complexity - every time i want to get the communities
        // i search the whole graph. A better approach is to use
        // a UNION-FIND algorithm, every time i add a friendship
        // i UNION the sets something like that
        ArrayList<ArrayList<String>> communities = new ArrayList<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        for(User u: getUsers()){
            visited.put(u.getId(), Boolean.FALSE);
        }
        for(Map.Entry<String,Boolean> entry: visited.entrySet()){
            if(entry.getValue() == Boolean.FALSE){
            ArrayList<String> members= BFS(entry.getKey(), visited);
            communities.add(members);
            }


        }

        return communities;
    }

    /**
     *
     * @param start - User email
     * @param visited - Map of <String, Booolean> -String- userName, Boolean - visited
     * @return ArrayList<String> - the community
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
