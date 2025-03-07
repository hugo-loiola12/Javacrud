package br.dev.projeto.dao;


import br.dev.projeto.model.User;
import br.dev.projeto.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // CREATE - Inserir um novo usuário
    public void create(User user) {
        String sql = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getAge());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
                rs.close();
                System.out.println("User created successfully! ID: " + user.getId());
            }
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    // READ - Listar todos os usuários
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error finding users: " + e.getMessage());
        }

        return users;
    }

    // READ - Buscar usuário por ID
    public User findById(int id) {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error finding user: " + e.getMessage());
        }

        return user;
    }

    // UPDATE - Atualizar um usuário
    public void update(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, age = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getAge());
            stmt.setInt(4, user.getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User updated successfully! ID: " + user.getId());
            } else {
                System.out.println("No user found with ID: " + user.getId());
            }
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    // DELETE - Remover um usuário
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User deleted successfully! ID: " + id);
            } else {
                System.out.println("No user found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    // CREATE TABLE - Criar a tabela de usuários se não existir
    public void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100) UNIQUE NOT NULL, " +
                "age INTEGER" +
                ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'users' is ready");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
}