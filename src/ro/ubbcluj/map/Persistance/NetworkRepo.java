package ro.ubbcluj.map.Persistance;

import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Utils.Observer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NetworkRepo extends Observer {


    // input.csv
    // stefbutacu@gmail.com,firend1@gmail.com,friend2@gmail.com
    // friend1@gmail.com, stefbutacu@gmail.com
    // friend2@gmail.com, stefbutacu@gmail.com


    private    LinkedHashMap<String, ArrayList<String> > relationships;

    private String fileName;
    public NetworkRepo(String fileName){
        loadFromFile();

    }

    /**
     * Adds a new friendship if it doesn't exist between user with emailUser1 and emailUser2
     * @param emailUser1 - String
     * @param emailUser2 - String
     */
    public void addFriendship(String emailUser1, String emailUser2){

    }

    /**
     * Removes the friendship between the 2 users
     * @param emailUser1 - String
     * @param emailUser2 - String
     */
    public void removeFriendship(String emailUser1, String emailUser2){

    }

    public int getNrOfFriendships(){
        return 0;
    }
    private void loadFromFile(){

        // open file
        // read line
        // emailLeft = line.split(",")[0];
        // friendsI = lien.split(",") i = [1.... N]
        // relationships.add(emailLeft, friendI) ..
    }


    private void writeToFile(){

    }

    /**
     *
     * @param u - User, the deleted one
     */
    @Override
    public void update(User u) {
            // remove the entry that has key u.getId
            // remove from list if there is u.getId
            // for now
    }
}
