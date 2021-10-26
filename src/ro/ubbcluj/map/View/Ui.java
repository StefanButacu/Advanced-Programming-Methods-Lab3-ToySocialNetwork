package ro.ubbcluj.map.View;

import ro.ubbcluj.map.Business.Service;
import ro.ubbcluj.map.Entities.User;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.SortedMap;

public class Ui {

    Service srv;
    public Ui (Service srv){
        this.srv = srv;
    }

    /**
     * Add user menu
     * @throws Exception
     */
    public void addUser() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("User name: ");
        String userName = in.next();
        System.out.print("Password: ");
        String userPassword = in.next();
        System.out.print("Email: ");
        String userEmail = in.next();
        srv.addUser(userName, userPassword, userEmail);
        System.out.println("Added completed");
    }

    public void getNrOfUsers(){
        System.out.println(srv.getNrOfUsers());
    }

    private void printMenu(){
        System.out.println("==========");
        System.out.println("0. Exit");
        System.out.println("1. Print users");
        System.out.println("2. Add user");
        System.out.println("3. Remove user");
        // System.out.println("4. Nr of users");
        System.out.print(">>>");
    }

    public void removeUser() throws IOException {
        System.out.print("Enter the email of the user that you want to remove: ");
        Scanner in = new Scanner(System.in);
        String email = in.next();
        srv.removeUser(email);
        System.out.println("Deletion completed");

    }
    public void run() throws Exception {
        Scanner in = new Scanner(System.in);
        while (true){
            printMenu();
            try {
                int cmd = in.nextInt();
                switch (cmd) {
                    case 0:
                        return;
                    case 1:
                        printUsers();
                        break;
                    case 2:
                        addUser();
                        break;
                    case 3:
                        removeUser();
                        break;
                    default:
                        System.out.println("Invalid command");

                }
            }catch (Exception e){
                System.out.println(e.getMessage());

            }


        }

    }

    private void printUsers() {
        System.out.format("%20s%20s%20s\n", "UserName", "Password","Email");
        for(User u: srv.getUsers()){
            System.out.format("%20s%20s%20s\n", u.getUserName(), u.getPassword(),u.getId());

        }
    }
}
