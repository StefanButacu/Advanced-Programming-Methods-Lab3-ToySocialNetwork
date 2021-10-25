package ro.ubbcluj.map.View;

import ro.ubbcluj.map.Business.Service;
import ro.ubbcluj.map.Entities.User;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.SortedMap;

public class Ui {

    Service srv;
    public Ui (Service srv){
        this.srv = srv;
    }

    public void addUser() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("User name: ");
        String userName = in.next();
        System.out.print("Password: ");
        String userPassword = in.next();
        System.out.print("Email: ");
        String userEmail = in.next();
        srv.addUser(userName, userPassword, userEmail);

    }

    public void getNrOfUsers(){
        System.out.println(srv.getNrOfUsers());
    }

    private void printMenu(){
        System.out.println("==========");
        System.out.println("0. Exit");
        System.out.println("1. Print users");
        System.out.println("2. Add user");
       // System.out.println("2. Nr of users");
        System.out.print(">>>");
    }

    public void run() throws Exception {
        Scanner in = new Scanner(System.in);
        while (true){
            printMenu();
            try {
                int cmd = in.nextInt();
                switch (cmd) {
                    case 0:
                        break;
                    case 1:
                        printUsers();
                        break;
                    case 2:
                        addUser();
                        break;
                    case 3:
                        getNrOfUsers();
                        break;
                    default:
                        System.out.println("Optiune invalida");

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
