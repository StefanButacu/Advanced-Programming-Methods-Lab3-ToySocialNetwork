package ro.ubbcluj.map;

import ro.ubbcluj.map.Business.Service;
import ro.ubbcluj.map.Persistance.NetworkRepo;
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
        //
        UserRepository repo = new UserRepository("src/ro/ubbcluj/map/users.csv");
        NetworkRepo relationships = new NetworkRepo(repo, "src/ro/ubbcluj/map/friends.csv");
        Service srv = new Service(repo,relationships);
        Ui ui = new Ui(srv);
        ui.run();
    }
}
/*
    Buna seara, sunt din grupa 222/1 si as avea o nelamurire cu privire la tema de la lab 3 la MAP.
    Eu nu prea "simt" nevoia de un un obiect friendship, ci mai degraba as reprezenta aceste prietenii prin
    adresele de email ale userilor. Vreau sa am un Network repo care sa tina datele intr-un Map< String, Lista<String>>.
    In Service voi apela NetworkRepo.addFriendship(string1, string2), unde voi face si validarile necesare
    (sa existe cei 2 useri cu adresele de email). In metoda addFriend as putea sa arunc o exceptie daca cei 2
    erau deja prieteni, iar la stergere daca incerc sa sterg o prietenie care nu exista
    tot la fel as arunca o exceptie.
    Si cand parsez la server aceasta lista de adiacenta pot identifica usor userii fiindca am acces la repoul de useri.
    Si la incarcarea relatiilor va fi ceva de forma
    email1@.com,friend1@.com,friend2@.com\nfriend1@.com,email1@.com\n,@friend2,@email1@.com\n...

 */
