package ro.ubbcluj.map.Entities;

import ro.ubbcluj.map.Utils.MyPair;

public class Friendship extends Entity<MyPair<String>> {

    public String idFriend1;
    public String idFriend2;
    public Friendship(String u1, String u2){
        super(new MyPair<String>(u1,u2));
        this.idFriend1 = u1;
        this.idFriend2 = u2;
    }

}
