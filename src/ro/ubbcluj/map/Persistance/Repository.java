package ro.ubbcluj.map.Persistance;

import ro.ubbcluj.map.Entities.Entity;

import java.io.IOException;
import java.util.ArrayList;

interface  Repository <ID, E extends Entity<ID>>{


    /**
     *
     * @param e
     */
     void  add(Entity e) throws Exception;

    /**
     *
     * @param e
     * @return
     */
     Entity delete(Entity e) throws IOException;

    /**
     *
     * @param e
     */
     void update(Entity e);



}
