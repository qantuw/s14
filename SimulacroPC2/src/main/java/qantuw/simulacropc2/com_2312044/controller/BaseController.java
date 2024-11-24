/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qantuw.simulacropc2.com_2312044.controller;

import qantuw.simulacropc2.com2312044.view.LibraryUI;
import qantuw.simulacropc2.com_2312044.repository.BookRepository;
import qantuw.simulacropc2.com_2312044.service.BookService;

/**
 *
 * @author ASUS
 */
public class BaseController {
    
 private final BookService bookService;
    private final LibraryUI libraryUI;

    public BaseController(LibraryUI libraryUI) {
        this.libraryUI = libraryUI;
        BookRepository bookRepository = new BookRepository("jdbc:mysql://localhost:3306/library", "root", "set2502Ale");
        bookService = new BookService(bookRepository);
    }

    public BookService getBookService() {
        return bookService;
    }

    public LibraryUI getLibraryUI() {
        return libraryUI;
    }
}
