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
        //  Tests for Repository (75%)
        //  Decide how to implement the password persistence?? Always keep the encrypted form? Or in file is encrypted and in memory decrypted?
        //  Load from file Users
        //  CRUD tests
        //  Service
        //  -Add user
        //  -Add Friendship, FriendShips repo???
        //
        UserRepository repo = new UserRepository("src/ro/ubbcluj/map/users.csv");
        Service srv = new Service(repo);
        Ui ui = new Ui(srv);
        ui.run();
    }
}
