package ro.ubbcluj.map.Persistance;

import ro.ubbcluj.map.Entities.Entity;
import ro.ubbcluj.map.Exceptions.RepoException;

import java.io.*;
import java.util.*;

abstract public class AbstractFileRepo<ID, E extends Entity<ID>> implements Repository<ID, E> {

    protected Map<ID, E> entities;


    String fileName;
    public AbstractFileRepo(String fileName) throws IOException {
        this.fileName = fileName;
        entities = new HashMap<>();
        loadFromFile();

    }

    /**
     * Adds a new entity
     * @param e - Entity type
     *          - if is null return null
     * @return - E entity - if the add was with success
     * @throws RepoException - if - the entity is null
     *                         if - there is duplicated id
     */
    @Override
    public E add(E e){
        if( e == null)
            throw new RepoException("Null entity!");
        if( entities.get(e.getId()) != null)
            throw new RepoException("Duplicated id!");
        entities.put(e.getId(), e);
        writeToFile();
        return e;

    }

    /**
     *
     * @param e - Entity
     * @return - null if the object is not present
     *         - the old object otherwise
     * @throws RepoException - if the Entity does not exist
     */
    @Override
    public E delete(E e)  {
        if(!entities.containsKey(e.getId()))
            throw new RepoException("Non-existent id!");
        E e_old = entities.get(e.getId());
        entities.remove(e.getId());
        writeToFile();
        return e_old;
    }


    /**
     * @param id -ID
     * @return - Entity if there is one with same id,
     * @throws RepoException - if Entity with id does not exist
     */
    @Override
    public E findById(ID id) {
        if( entities.get(id) == null)
            throw new RepoException("Non-existent id!");
        else
            return entities.get(id);
    }

    ////////// NOT REQUIRED update
    @Override
    public void update(E e) {    };
    /**
     *
     * @return - collection of entities
     */
    public Collection<E> findAll(){
        return entities.values();

    }

    /**
     *
     * @return - the number of entities
     */
    public int getSize(){
        return entities.values().size();
    }

    /**
     * Loads from file the data specific to the repo
     */
     public void loadFromFile() throws IOException {
         try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
             String line;
             while ((line = br.readLine()) != null) {
                 if(line.equals(""))
                     continue;
                 List<String> attrs = Arrays.asList(line.split(";"));
                 E e = this.extractEntity(attrs);
                 this.add(e);
             }
         } catch (IOException e) {
             throw e;
         }
     }

         /**
     * Writes to the file data from repo
     */
         public void writeToFile(){

             try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
                for(E e: findAll()){
                    bw.write(createStringFromEntity(e));
                    bw.newLine();

                }

             } catch (IOException e) {
                 e.printStackTrace();
             }

         }

    /**
     *
     * @param attrs - list of strings
     * @return - an Entity type
     */
    abstract protected E extractEntity(List<String> attrs) ;

    /**
     *
     * @param e - Entity type
     * @return - a String = Entity representation in file
     */
    abstract protected String createStringFromEntity(E e);
}
