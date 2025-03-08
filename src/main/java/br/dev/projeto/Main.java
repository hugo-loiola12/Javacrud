package br.dev.projeto;

import br.dev.projeto.dao.UserDAO;
import br.dev.projeto.model.User;
import br.dev.projeto.util.DatabaseConnection;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static UserDAO userDAO;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Inicializar o DAO e criar a tabela se não existir
        userDAO = new UserDAO();
        userDAO.createTableIfNotExists();

        scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            showMenu();
            int option = getOption();

            switch (option) {
                case 1:
                    createUser();
                    break;
                case 2:
                    readAllUsers();
                    break;
                case 3:
                    readUserById();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        // Fechar recursos
        scanner.close();
        DatabaseConnection.closeConnection();
        System.out.println("Application terminated.");
    }

    private static void showMenu() {
        System.out.println("\n====== USER MANAGEMENT SYSTEM ======");
        System.out.println("1. Create a new user");
        System.out.println("2. List all users");
        System.out.println("3. Find user by ID");
        System.out.println("4. Update a user");
        System.out.println("5. Delete a user");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getOption() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opção inválida
        }
    }

    private static void createUser() {
        System.out.println("\n-- CREATE NEW USER --");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        User user = new User(name, email, age);
        userDAO.create(user);
    }

    private static void readAllUsers() {
        System.out.println("\n-- ALL USERS --");

        List<User> users = userDAO.findAll();

        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    private static void readUserById() {
        System.out.println("\n-- FIND USER BY ID --");

        System.out.print("Enter user ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        User user = userDAO.findById(id);

        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User not found with ID: " + id);
        }
    }

    private static void updateUser() {
        System.out.println("\n-- UPDATE USER --");

        System.out.print("Enter user ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        User existingUser = userDAO.findById(id);

        if (existingUser != null) {
            System.out.println("Current user data: " + existingUser);

            System.out.print("New name (press Enter to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                existingUser.setName(name);
            }

            System.out.print("New email (press Enter to keep current): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                existingUser.setEmail(email);
            }

            System.out.print("New age (press Enter to keep current): ");
            String ageStr = scanner.nextLine();
            if (!ageStr.isEmpty()) {
                existingUser.setAge(Integer.parseInt(ageStr));
            }

            userDAO.update(existingUser);
        } else {
            System.out.println("User not found with ID: " + id);
        }
    }

    private static void deleteUser() {
        System.out.println("\n-- DELETE USER --");

        System.out.print("Enter user ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        User existingUser = userDAO.findById(id);

        if (existingUser != null) {
            System.out.println("User to delete: " + existingUser);
            System.out.print(
                "Are you sure you want to delete this user? (y/n): "
            );
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("y")) {
                userDAO.delete(id);
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("User not found with ID: " + id);
        }
    }
}
