package Tests.Persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.ubbcluj.map.Entities.Friendship;
import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Persistance.NetworkRepo;
import ro.ubbcluj.map.Persistance.UserRepository;
import ro.ubbcluj.map.Utils.MyPair;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class NetworkRepoTest {


    private UserRepository userRepo;
    private NetworkRepo networkRepo;
    @BeforeEach
    void setUp() {
        String fileName = "E:\\Facultate\\Anul2Sem1\\Metode avansate de programare\\Laborator\\Lab3_ToySocialNetwork\\src\\Tests\\Persistence\\testInput.csv";
        String friendshipsFileName = "E:\\Facultate\\Anul2Sem1\\Metode avansate de programare\\Laborator\\Lab3_ToySocialNetwork\\src\\Tests\\Persistence\\testFriendships.csv";
        try{
            userRepo = new UserRepository(fileName);
            networkRepo = new NetworkRepo(userRepo, friendshipsFileName);

        }catch (Exception e) {
            System.out.println("File not found");

        }
    }


    @Test
    void add() throws Exception {
        networkRepo.addFriendship("anamaria@yahoo.com", "stef2001@gmail.com");
        assertEquals(networkRepo.getSize(), 4);
        // assertNull(networkRepo.add(f));
        networkRepo.removeFriendship("anamaria@yahoo.com", "stef2001@gmail.com"); // to keep the testInputFile clean

    }

    @Test
    void delete() throws Exception {

        assertEquals(networkRepo.getSize(), 2);
        networkRepo.addFriendship("stef2001@gmail.com", "stefan@hotmail.com");
        assertEquals(networkRepo.getSize(), 4);
        networkRepo.removeFriendship("stef2001@gmail.com", "stefan@hotmail.com");
        // assertEquals(networkRepo.delete(f).getId(), new MyPair<String>("stef2001@gmail.com", "stefan@hotmail.com"));
        assertEquals(networkRepo.getSize(), 2);
        // assertNull(networkRepo.delete(f)); // delete 2 times the same user
       }

    @Test
    void getSize() {
        assertEquals(networkRepo.getSize(), 2);
    }

}