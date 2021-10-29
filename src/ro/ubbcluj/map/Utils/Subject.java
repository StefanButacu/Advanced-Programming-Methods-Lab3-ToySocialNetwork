package ro.ubbcluj.map.Utils;

import ro.ubbcluj.map.Entities.User;

import java.util.ArrayList;

public abstract class Subject {


    ArrayList<Observer> obs;

    public Subject(){
        obs = new ArrayList<>();
    }

    /**
     * Notifies the observer about the Subject change
     */
    protected void notifyObservers(User u) {
        for (Observer o : obs)
            o.update(u);
    }

    /**
     * A new Observer signs in to "observe" a subject
     * @param o - observer
     */
    protected void addObserver(Observer o){
        if(o != null)
            obs.add(o);
    };


    /**
     * Removes the observer
     * @param o - Observer
     */
    protected void removeObserver(Observer o){
        obs.removeIf(x -> (x.equals(o)));
    };
}

