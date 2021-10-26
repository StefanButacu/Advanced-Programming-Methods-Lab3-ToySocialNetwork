package ro.ubbcluj.map.Persistance;

import ro.ubbcluj.map.Entities.Entity;

import java.io.IOException;
import java.util.ArrayList;

interface  Repository <ID, E extends Entity<ID>>{


    /**
     *  Adds a new Entity into the persistence layer
     * @param e - Entity type
     * @throws Exception if the entity is duplicated
     */
     void  add(Entity e) throws Exception;

    /**
     * Remove the Entity e
     * @param e - Entity
     * @return Entity - the deleted entity
     *                - return null if is not found
     */
     Entity delete(Entity e) throws IOException;

    /**
     * Replace the Saved Entity that has the same ID as Param e
     * @param e - Entity
     */
     void update(Entity e);



}
