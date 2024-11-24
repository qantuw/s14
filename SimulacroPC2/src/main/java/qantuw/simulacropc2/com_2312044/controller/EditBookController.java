/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qantuw.simulacropc2.com_2312044.controller;

import qantuw.simulacropc2.com2312044.view.LibraryUI;
import qantuw.simulacropc2.com_2312044.model.Book;

/**
 *
 * @author ASUS
 */
public class EditBookController extends BaseController {
    public EditBookController(LibraryUI libraryUI) {
        super(libraryUI);
    }

    public void updateBook(Book updatedBook){
        getBookService().updateBook(updatedBook);
    }

    public void deleteBook(int idBook){
        getBookService().deleteBook(idBook);
    }
}