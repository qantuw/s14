/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qantuw.simulacropc2.com_2312044.service;

import qantuw.simulacropc2.com_2312044.model.Book;
import qantuw.simulacropc2.com_2312044.repository.BookRepository;
import java.sql.SQLException;
import java.util.List;


public class BookService {

     private final BookRepository bookRepository;
     
      public BookService(BookRepository repository) {
        this.bookRepository = repository;
    }

     public void addBook(Book book) {
        try {
            bookRepository.save(book);
            System.out.println("Book saved successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating book: " + e.getMessage());
        }
    }

    // Buscar libros por título
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    // Buscar libros por autor
    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public void updateBook(Book updatedBook) {
        bookRepository.update(updatedBook);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(int id) {
        bookRepository.delete(id);
    }

    public Book getBookById(int id) {
        return bookRepository.getBookById(id); // Consulta al repositorio
    }
}