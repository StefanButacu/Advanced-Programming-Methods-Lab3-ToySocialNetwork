package Tests.Persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Persistance.UserRepository;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private  UserRepository repo;
    @BeforeEach
    void setUp() {
        String fileName = "E:\\Facultate\\Anul2Sem1\\Metode avansate de programare\\Laborator\\Lab3_ToySocialNetwork\\src\\Tests\\Persistence\\testInput.csv";
        try{
            repo = new UserRepository(fileName);
        }catch (IOException e) {
            System.out.println("File not found");
        }
    }


    @Test
    void getSize() {
        assertEquals(repo.getSize(), 3);
    }

    @Test
    void add() throws Exception {
        User u = new User("ana1", "maria2", "test@yahoo.com");
        repo.add(u);
        assertEquals(repo.getSize(), 4);
        try{
            User u2 = new User("ana1", "maria2", "test@yahoo.com");
            repo.add(u2);
        }
        catch (Exception e){
            assertEquals(e.getMessage(), "Duplicated user!");
        }
        repo.delete(u); // to keep the testInputFile clean

    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}