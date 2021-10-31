package ro.ubbcluj.map.Validators;

import ro.ubbcluj.map.Entities.User;
import ro.ubbcluj.map.Exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    /**
     *
     * @param u - user
     * @throws ValidationException - with the message
     */
    public void validate(User u){
        String errorMsg = "";
        if(u.getUserName().equals(""))
            errorMsg += "User name can't be empty\n";
        if(u.getPassword().equals(""))
            errorMsg += "Password can't be empty\n";
        if(u.getPassword().length() <= 10)
            errorMsg += "Password is too short\n";
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(u.getId());
        if(!matcher.matches())
            errorMsg += "Invalid email address\n";
        if(errorMsg.length() > 0)
            throw new ValidationException(errorMsg);
    }
}
