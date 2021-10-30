package ro.ubbcluj.map.Persistance;

import ro.ubbcluj.map.Entities.Friendship;
import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Utils.MyPair;
import ro.ubbcluj.map.Utils.Observer;

import javax.print.DocFlavor;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class NetworkRepo {


    // input.csv
    // stefbutacu@gmail.com,firend1@gmail.com,friend2@gmail.com
    // friend1@gmail.com, stefbutacu@gmail.com
    // friend2@gmail.com, stefbutacu@gmail.com

    private HashMap<String, ArrayList<String> > relationships;
    private UserRepository userRepo;
    private String fileName;
    private int size;  // TOT TIMPUL IN SIZE O SA FIE CATE RANDURI AM IN FISIER

    public NetworkRepo(UserRepository repo, String fileName){
        relationships = new HashMap<>();
        this.userRepo = repo;
        this.fileName = fileName;
        this.size = 0;
        loadFromFile();
        }


    /**
     * Adds a new friendship if it doesn't exist between user with emailUser1 and emailUser2
     * @param emailUser1 - String
     * @param emailUser2 - String
     */
    public void addFriendship(String emailUser1, String emailUser2){
        // user1 doesnt have any friends
        relationships.computeIfAbsent(emailUser1, k -> new ArrayList<String>());
        // TODO - if user2 is already friend with user 1 do smth
        relationships.get(emailUser1).add(emailUser2);
        this.size++;
        // userul nu avea niciun prieten
        relationships.computeIfAbsent(emailUser2, k -> new ArrayList<String>());
        relationships.get(emailUser2).add(emailUser1);
        this.size++;

    }

    /**
     * Removes the friendship between the 2 users
     * @param emailUser1 - String
     * @param emailUser2 - String
     */
    public void removeFriendship(String emailUser1, String emailUser2){
        // TODO - if they are not friends do smth
        if( relationships.get(emailUser1) != null && relationships.get(emailUser2) != null ) {
            relationships.get(emailUser1).removeIf(x -> x.equals(emailUser2));
            relationships.get(emailUser2).removeIf(x -> x.equals(emailUser1));
            this.size--;
            this.size--;
            writeToFile();
        }
    }

    /**
     *
     * @param userEmail - string
     * @return - an ArrayList of Strings = email of other users
     *         - or null if the user has no friends :(
     */
    public ArrayList<String> getFriends(String userEmail){
        return relationships.get(userEmail);
    }
    /**
     *
     * @return - the number of bidirectional friendships
     */
    public int getSize() {
       return size;
    }


    public void loadFromFile(){

        /// user1;user2;
        /// user2;user1;
        /// userK;userI;
        //....
        /// userI;userK;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line=br.readLine()) != null){
                if(line.equals(""))
                    continue;
                String[] attrs = line.split(";");
                String firstEmail = attrs[0];
                String secondEmail = attrs[1];
                /////////
                relationships.computeIfAbsent(firstEmail, k -> new ArrayList<String>());
                // TODO - if user2 is already friend with user 1 do smth
                relationships.get(firstEmail).add(secondEmail);
                this.size++;

                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile() {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName))) {
            String line = "";
            for(Map.Entry<String, ArrayList<String> > entry: relationships.entrySet()){
                for(String friend : entry.getValue()) {
                    line = entry.getKey() + ";" + friend;
                    br.write(line);
                    br.newLine();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
