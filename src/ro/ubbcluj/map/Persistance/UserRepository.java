package ro.ubbcluj.map.Persistance;

import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Exceptions.MyException;

import java.io.*;
import java.util.Collection;
import java.util.List;

public class UserRepository extends AbstractFileRepo<String,User> {

    public UserRepository(String fileName) throws IOException {
        super(fileName);
    }


    @Override
    public User extractEntity(List<String> attrs) {
        if(attrs.size() != 3)
            throw new MyException("Invalid nr of params!");
        return new User(attrs.get(0), attrs.get(1), attrs.get(2));
    }



    @Override
    protected String createStringFromEntity(User user) {
        return user.getUserName()+";"+user.getPassword()+";"+user.getId();
    }


}
