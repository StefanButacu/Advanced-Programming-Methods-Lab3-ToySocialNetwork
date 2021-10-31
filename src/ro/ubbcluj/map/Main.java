package ro.ubbcluj.map;

import ro.ubbcluj.map.Business.Service;
import ro.ubbcluj.map.Persistance.NetworkRepo;
import ro.ubbcluj.map.Persistance.UserRepository;
import ro.ubbcluj.map.View.Ui;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        // TODO
        //  Decide how to implement the password persistence?? Always keep the encrypted form? Or in file is encrypted and in memory decrypted?

        UserRepository repo = new UserRepository("src/ro/ubbcluj/map/users.csv");
        NetworkRepo relationships = new NetworkRepo(repo, "src/ro/ubbcluj/map/friends.csv");
        Service srv = new Service(repo,relationships);
        Ui ui = new Ui(srv);
        ui.run();
    }
}
