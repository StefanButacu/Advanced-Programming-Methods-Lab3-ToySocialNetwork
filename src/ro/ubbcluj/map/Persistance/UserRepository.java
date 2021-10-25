package ro.ubbcluj.map.Persistance;

import ro.ubbcluj.map.Entities.Entity;
import ro.ubbcluj.map.Entities.User;

import java.io.*;
import java.util.ArrayList;

public class UserRepository implements Repository<String, User> {
    private String fileName;
    private ArrayList<User> entities;

    public UserRepository(String fileName) throws IOException {
        super();
        this.fileName = fileName;
        entities = new ArrayList<>();
        loadFromFile();

    }
    void loadFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while((line = br.readLine())!= null){
            String[] attrs = line.split(",");
            String userName = attrs[0];
            String cryptedPasswd = attrs[1];
            // TODO
            // Crypted or no passwd
            String email = attrs[2];
            User u = new User(userName, cryptedPasswd, email);
            entities.add(u);
        }
        br.close();

    }

    public int getSize(){
        return entities.size();

    }

    @Override
    public void add(Entity e) throws Exception{
        for(User user: entities){
            if(user.equals(e))
                throw new Exception("Duplicated user!");
        }
        entities.add((User) e);
        writeToFile();
    }

    /**
     * Overwrites the content of the fileName with the existing users in memory
     * @throws IOException - if cant open the file
     */
    private void writeToFile() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,false));
        for(User u:entities){
            bw.write(u.getUserName() + ',' +u.getPassword()+','+u.getId());
            bw.newLine();
        }
        bw.close();

    }

    @Override
    public Entity delete(Entity e) throws IOException {
        entities.removeIf(
                e::equals
        );
        writeToFile();
        return null;
    }

    @Override
    public void update(Entity e) {

    }
}
