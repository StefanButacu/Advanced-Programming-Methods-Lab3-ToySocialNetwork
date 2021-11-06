package ro.ubbcluj.map;

import ro.ubbcluj.map.Business.Service;
import ro.ubbcluj.map.Persistance.NetworkRepo;
import ro.ubbcluj.map.Persistance.UserRepository;
import ro.ubbcluj.map.View.Ui;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            UserRepository repo = new UserRepository("src/ro/ubbcluj/map/users.csv");

            NetworkRepo relationships = new NetworkRepo(repo, "src/ro/ubbcluj/map/friends.csv");
            Service srv = new Service(repo, relationships);
            Ui ui = new Ui(srv);
            ui.run();
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
}
