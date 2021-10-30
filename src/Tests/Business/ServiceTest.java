package Tests.Business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.ubbcluj.map.Business.Service;
import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Persistance.NetworkRepo;
import ro.ubbcluj.map.Persistance.UserRepository;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private UserRepository repo;
    private Service srv;
    private NetworkRepo relationships;
    @BeforeEach
    void setUp() {
        String fileName = "E:\\Facultate\\Anul2Sem1\\Metode avansate de programare\\Laborator\\Lab3_ToySocialNetwork\\src\\Tests\\Persistence\\testInput.csv";
        String networkFileName = "E:\\Facultate\\Anul2Sem1\\Metode avansate de programare\\Laborator\\Lab3_ToySocialNetwork\\src\\Tests\\Persistence\\testFriendships.csv";
        try{
            repo = new UserRepository(fileName);
            relationships = new NetworkRepo(repo,networkFileName);
            srv = new Service(repo, relationships);
        }catch (IOException e) {
            System.out.println("File not found");
        }
    }

    @Test
    void addRemoveUser() {
        srv.addUser("user1", "pass1", "passuser1@yahoo.com");
        assertEquals(srv.getNrOfUsers(), 4);
        srv.removeUser("passuser1@yahoo.com");
        assertEquals(srv.getNrOfUsers(), 3);


    }

    @Test
    void addRemoveFriendship() {
        srv.addFriendship("stef2001@gmail.com", "stefan@hotmail.com");
        ArrayList<User> users = srv.getUsersFriend("stefan@hotmail.com");
        assertEquals(users.get(0).getId(), "anamaria@yahoo.com");
        assertEquals(users.get(1).getId(), "stef2001@gmail.com");
        users = srv.getUsersFriend("stef2001@gmail.com");
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getId(), "stefan@hotmail.com");

        srv.removeFriendship("stefan@hotmail.com", "stef2001@gmail.com");
        users = srv.getUsersFriend("stef2001@gmail.com");
        assertEquals(users.size(), 0);

    }


    @Test
    void getUsers() {
    }
}