package com.menuapp;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.menuapp.entity.User;
import com.menuapp.service.UserService;

@SpringBootApplication
public class MenuAppApplication implements CommandLineRunner {

    @Autowired
    private UserService service;

    public static void main(String[] args) {
        SpringApplication.run(MenuAppApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Signup");
            System.out.println("2. Login");
            System.out.println("3. View All Users");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    signup(sc);
                    break;
                case 2:
                    login(sc);
                    break;
                case 3:
                    viewUsers();
                    break;
                case 4:
                    System.out.println("Thank You");
                    System.exit(0);
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    private void signup(Scanner sc) {
        User user = new User();

        System.out.print("Enter Name: ");
        user.setName(sc.next());

        System.out.print("Enter Email: ");
        user.setEmail(sc.next());

        System.out.print("Enter Password: ");
        user.setPassword(sc.next());

        service.signup(user);
        System.out.println("Signup Successful");
    }

    private void login(Scanner sc) {
        System.out.print("Enter Email: ");
        String email = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        Optional<User> user = service.login(email, password);

        if (user.isPresent()) {
            System.out.println("Login Successful");
            System.out.println("Welcome " + user.get().getName());
        } else {
            System.out.println("Invalid Credentials");
        }
    }

    private void viewUsers() {
        List<User> users = service.getAllUsers();

        System.out.println("\n--- USERS LIST ---");
        for (User u : users) {
            System.out.println(u.getId() + " | " + u.getName() + " | " + u.getEmail());
        }
    }
}
