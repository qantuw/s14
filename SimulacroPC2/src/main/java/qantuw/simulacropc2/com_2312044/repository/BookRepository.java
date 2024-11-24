/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qantuw.simulacropc2.com_2312044.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import qantuw.simulacropc2.com_2312044.model.Book;

public class BookRepository {
     private final String url;
    private final String user;
    private final String password;

    public BookRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void save(Book book) {
        String sql = "INSERT INTO books (title, author, genre, year) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());
            stmt.setInt(4, book.getYear());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving book: " + e.getMessage());
        }
    }

    public List<Book> findByTitle(String title) {
        String sql = "SELECT * FROM books WHERE title LIKE ?";
        List<Book> books = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + title + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(buildBookFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding books by title: " + e.getMessage());
        }
        return books;
    }

    public List<Book> findByAuthor(String author) {
        String sql = "SELECT * FROM books WHERE author LIKE ?";
        List<Book> books = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + author + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(buildBookFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding books by author: " + e.getMessage());
        }
        return books;
    }

    private Book buildBookFromResultSet(ResultSet rs) throws SQLException {
        return new Book.Builder()
                .setId(rs.getInt("id"))
                .setTitle(rs.getString("title"))
                .setAuthor(rs.getString("author"))
                .setGenre(rs.getString("genre"))
                .setYear(rs.getInt("year"))
                .build();
    }

    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book", e);
        }
    }

    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, genre = ?, year = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setInt(4, book.getYear());
            statement.setInt(5, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating book", e);
        }
    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                books.add(new Book.Builder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setAuthor(resultSet.getString("author"))
                        .setGenre(resultSet.getString("genre"))
                        .setYear(resultSet.getInt("year"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving books", e);
        }
        return books;
    }

    public Book getBookById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Book.Builder()
                            .setId(resultSet.getInt("id"))
                            .setTitle(resultSet.getString("title"))
                            .setAuthor(resultSet.getString("author"))
                            .setGenre(resultSet.getString("genre"))
                            .setYear(resultSet.getInt("year"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
