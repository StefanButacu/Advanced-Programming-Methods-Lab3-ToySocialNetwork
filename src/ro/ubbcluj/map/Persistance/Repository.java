package ro.ubbcluj.map.Persistance;

import ro.ubbcluj.map.Entities.Entity;
import ro.ubbcluj.map.Exceptions.RepoException;

import java.io.IOException;

interface  Repository <ID, E extends Entity<ID>>{


    /**
     *  Adds a new Entity into the persistence layer
     * @param e - Entity type
     */
     E add(E e) throws RepoException;

    /**
     * Remove the Entity e
     * @param e - Entity
     * @return Entity - the deleted entity
     *                - return null if is not found
     */
     E delete(E e) throws RepoException;

    /**
     * Replace the Saved Entity that has the same ID as Param e
     * @param e - Entity
     */
     void update(E e);


    /**
     *
     * @param id -ID
     * @return - Entity if there is one with same id,
     *         - null otherwise
     */
     E findById(ID id) throws RepoException;

    /**
     *
     * @return - iterable object with all Entities
     */
    Iterable<E> findAll();


}
