/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qantuw.simulacropc2.com_2312044.controller;

import java.util.List;
import qantuw.simulacropc2.com2312044.view.LibraryUI;
import qantuw.simulacropc2.com_2312044.model.Book;

public class RegisterBookController extends BaseController{

    public RegisterBookController(LibraryUI libraryUI) {
        super(libraryUI);
    }

    public void addBook(Book book){
        try {
            getBookService().addBook(book);
            getLibraryUI().showMessage("Book added successfully!");
            getLibraryUI().clearInputFields();
        } catch (NumberFormatException ex) {
            getLibraryUI().showError("Year must be a number!");
        }
    }
}