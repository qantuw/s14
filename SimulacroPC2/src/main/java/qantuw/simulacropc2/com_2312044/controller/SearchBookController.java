/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qantuw.simulacropc2.com_2312044.controller;

import java.util.List;
import qantuw.simulacropc2.com_2312044.model.Book;

import java.util.stream.Collectors;
import qantuw.simulacropc2.com2312044.view.LibraryUI;

public class SearchBookController extends BaseController {

    public SearchBookController(LibraryUI libraryUI) {
        super(libraryUI);
    }

    public List<Book> getBooksByQuery(String query) {
        List<Book> books = getBookService().getAllBooks().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query) ||
                        book.getAuthor().toLowerCase().contains(query))
                .collect(Collectors.toList());
        return books;
    }

    public List<Book> getBooksByGenre(String query) {
        System.out.println(query);
        String genre = query.toLowerCase();
        List<Book> books = getBookService().getAllBooks().stream()
                .filter(book -> book.getGenre().toLowerCase().contains(genre))
                .collect(Collectors.toList());
        return books;
    }

    public Book getBookById(int selectedBookId) {
        return getBookService().getBookById(selectedBookId);
    }

    public List<Book> getAllBooks() {
        return getBookService().getAllBooks();
    }
}