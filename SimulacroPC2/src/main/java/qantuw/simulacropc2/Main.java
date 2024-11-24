/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qantuw.simulacropc2;

import java.util.List;

import qantuw.simulacropc2.com_2312044.model.Book;
import qantuw.simulacropc2.com_2312044.repository.BookRepository;

public class Main{
 public static void main(String[] args) {
     
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "qantuw";

        BookRepository repository = new BookRepository(url, user, password);

        // Crear y guardar libros
        Book book1 = new Book.Builder()
                .setTitle("1984")
                .setAuthor("George Orwell")
                .setGenre("Dystopian")
                .setYear(1949)
                .build();

        Book book2 = new Book.Builder()
                .setTitle("To Kill a Mockingbird")
                .setAuthor("Harper Lee")
                .setGenre("Fiction")
                .setYear(1960)
                .build();

        repository.save(book1);
        repository.save(book2);

        // Consultar libros
        System.out.println("Books by author 'George Orwell':");
        List<Book> booksByAuthor = repository.findByAuthor("George Orwell");
        booksByAuthor.forEach(System.out::println);

        System.out.println("Books with title containing '1984':");
        List<Book> booksByTitle = repository.findByTitle("1984");
        booksByTitle.forEach(System.out::println);
    }
}