package ro.ubbcluj.map;

import ro.ubbcluj.map.Business.Service;
import ro.ubbcluj.map.Persistance.UserRepository;
import ro.ubbcluj.map.View.Ui;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        System.out.println("Starting...");
        // TODO
        //  Tests for Repository (75%) - only for update/find
        //  Decide how to implement the password persistence?? Always keep the encrypted form? Or in file is encrypted and in memory decrypted?
        //  Service tests
        //  - Remove user, what happens with his friendships??
        //  - Add Friendship, FriendShips repo???
        //  - User repo is a subject And Friendship Repo is an observer( when i delete an user, i delete his friendships)
        //  - Depending on how much data i duplicate ( when i update an user, i update friendships )
        //                                              ( FriendShip(User u1, User u2) - u1,u2 = references
        //  - Maybe have a Singleton scanner object in Ui class
        UserRepository repo = new UserRepository("src/ro/ubbcluj/map/users.csv");
        Service srv = new Service(repo);
        Ui ui = new Ui(srv);
        ui.run();
    }
}
