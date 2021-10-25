package Tests.Entities;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import ro.ubbcluj.map.Entities.User;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class UserTest {

    private User u1;

    @BeforeEach
    void setUp() {
        u1= new User("stefan", "alex", "stefan@yahoo.com");
    }



    @Test
    public void testEquals() {
        User u2 = new User("stefan2", "alex2", "stefan@yahoo.com");
        Assertions.assertEquals(u1,u2);
        User u3 =new User("stefan2", "alex2", "st2efan@yahoo.com");
        Assertions.assertNotEquals(u2,u3);
    }

    @Test
    void getUserName() {
        Assertions.assertEquals(u1.getUserName(), "stefan");
    }

    @Test
    void setUserName() {
        u1.setUserName("user");
        Assertions.assertEquals(u1.getUserName(), "user");
    }

    @Test
    void getPassword() {
        Assertions.assertEquals(u1.getPassword(), "alex");

    }

    @Test
    void setPassword() {
        u1.setPassword("pass2");
        Assertions.assertEquals(u1.getPassword(), "pass2");
    }

    @Test
    void getId() {
        Assertions.assertEquals(u1.getId(),"stefan@yahoo.com");

    }

    @Test
    void setId() {
        u1.setId("email2");
        Assertions.assertEquals(u1.getId(),"email2");

    }
}