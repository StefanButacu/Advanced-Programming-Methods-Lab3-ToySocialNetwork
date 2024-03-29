package Tests.Persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Exceptions.RepoException;
import ro.ubbcluj.map.Persistance.UserRepository;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private  UserRepository repo;
    {
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


        try {
            User u2 = new User("ana1", "maria2", "test@yahoo.com");
            repo.add(u2);
        }
        catch (RepoException e){
            assertEquals(e.getMessage(), "Duplicated id!");

        }

        repo.delete(u); // to keep the testInputFile clean

    }

    @Test
    void delete() {
        assertEquals(repo.getSize(), 3);
        User u = new User("andrei","stefan","stefan@hotmail.com");
        assertEquals(repo.delete(u).getId(), "stefan@hotmail.com");
        assertEquals(repo.getSize(), 2);

        try{
            repo.delete(u); // delete 2 times the same user
        }
        catch(RepoException e){
            assertEquals(e.getMessage(),"Non-existent id!");

        }

        repo.add(u); // keep input test clean

    }
    @Test
    void findById() {
        // find user
        String id = "stefan@hotmail.com";
        User u = repo.findById(id);
        assertEquals(u.getUserName(), "andrei");
        id = "bla";
        try{
            repo.findById(id);
        }
        catch (RepoException e) {
            assertEquals(e.getMessage(), "Non-existent id!");
        }
    }
}