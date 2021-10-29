package ro.ubbcluj.map.Utils;

import ro.ubbcluj.map.Entities.Entity;
import ro.ubbcluj.map.Entities.User;

public abstract class Observer {

    /**
     * Updates the class depending on the subject signal
     */
    abstract public void update(User e);

}
