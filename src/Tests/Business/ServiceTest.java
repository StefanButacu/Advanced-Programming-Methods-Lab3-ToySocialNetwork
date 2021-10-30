package Tests.Business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.ubbcluj.map.Business.Service;
import ro.ubbcluj.map.Persistance.NetworkRepo;
import ro.ubbcluj.map.Persistance.UserRepository;

import java.io.IOException;

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
    void getUsers() {
    }
}