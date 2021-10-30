package Tests.Business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.ubbcluj.map.Business.Service;
import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Exceptions.RepoException;
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
    void deleteUserAndFriendship(){
            srv.addUser("Ion", "vasile", "ionvasile@yahoo.com");
            srv.addUser("felix", "sima", "felix@sima.com");
            srv.addUser("ana", "pop", "ana@pop.com");
            assertEquals(srv.getUsers().size(), 6);
            srv.addFriendship("ionvasile@yahoo.com", "felix@sima.com");
            srv.addFriendship("ionvasile@yahoo.com", "ana@pop.com");
            srv.addFriendship("ana@pop.com", "felix@sima.com");
            assertEquals(srv.getUsersFriend("ana@pop.com").size(), 2);
            srv.removeUser("ionvasile@yahoo.com");
            assertEquals(srv.getUsersFriend("ana@pop.com").size(), 1);

            assertEquals(srv.getUsersFriend("felix@sima.com").size(), 1);

            try {
                srv.getUsersFriend("ionvasile@yahoo.com");
            } catch (RepoException e) {
                assertEquals(e.getMessage(), "Non-existent id!");
            }
            srv.removeUser("felix@sima.com");
            srv.removeUser("ana@pop.com");

    }


    @Test
    void testCommunity() {
        srv.addUser("Ion", "vasile", "ionvasile@yahoo.com");
        srv.addUser("felix", "sima", "felix@sima.com");
        srv.addUser("ana", "pop", "ana@pop.com");

        srv.addFriendship("ionvasile@yahoo.com", "felix@sima.com");
        srv.addFriendship("felix@sima.com", "ana@pop.com");

        // anamaria + stefan
        // stef
        // ioan - felix - ana
        assertEquals(srv.getNrOfCommunities(), 3);

        ArrayList<ArrayList<String>> communities = srv.getCommunities();
        for(int i = 0 ; i < communities.size(); i++){
            System.out.print("Communinty nr" + i+ ": ");
            for(String s: communities.get(i)){
                System.out.print(s + ",");
            }
            System.out.println("+++++++++++");
        }

        srv.addFriendship("ionvasile@yahoo.com", "anamaria@yahoo.com");
        assertEquals(srv.getNrOfCommunities(), 2);


        srv.removeUser("ionvasile@yahoo.com");
        srv.removeUser("felix@sima.com");
        srv.removeUser("ana@pop.com");


    }


}