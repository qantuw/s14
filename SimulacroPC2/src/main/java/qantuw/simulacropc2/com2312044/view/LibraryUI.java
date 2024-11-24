/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qantuw.simulacropc2.com2312044.view;


import qantuw.simulacropc2.com_2312044.controller.SearchBookController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import qantuw.simulacropc2.com_2312044.controller.EditBookController;
import qantuw.simulacropc2.com_2312044.controller.RegisterBookController;
import qantuw.simulacropc2.com_2312044.model.Book;
   public class LibraryUI extends JFrame {
    private final JTextField titleInput = new JTextField(20);
    private final JTextField authorInput = new JTextField(20);
    private final JTextField genreInput = new JTextField(20);
    private final JTextField yearInput = new JTextField(20);

    private final JButton addBookButton = new JButton("Add Book");
    private final JButton refreshButton = new JButton("Refresh");
    private final JButton editButton = new JButton("Edit Selected");
    private final JButton deleteButton = new JButton("Delete Selected");

    private final JTextField searchField = new JTextField(20); // Campo de búsqueda
    private final JButton searchButton = new JButton("Search"); // Botón de búsqueda
    private final JButton filterButton = new JButton("Filter"); // Botón de filtro

    private final JComboBox<String> genreFilterComboBox = new JComboBox<>(new String[]{
            "All Genres", "Fiction", "Non-Fiction", "Science", "History"}); // Filtro por género

    private final JTable bookTable;
    private final DefaultTableModel tableModel;

    //Controllers
    private final RegisterBookController registerBookController;
    private final SearchBookController searchBookController;
    private final EditBookController editBookController;

    public LibraryUI() {

        //Nos aseguramos que el controlador para el Registro se cree
        registerBookController = new RegisterBookController(this);
        searchBookController = new SearchBookController(this);
        editBookController = new EditBookController(this);

        setTitle("Library Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());

        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleInput);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorInput);
        inputPanel.add(new JLabel("Genre:"));
        inputPanel.add(genreInput);
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(yearInput);
        inputPanel.add(addBookButton);

        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Search (Author/Title):"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        searchPanel.add(new JLabel("Filter by Genre:"));
        searchPanel.add(genreFilterComboBox);
        searchPanel.add(filterButton);

        // Tabla de libros
        String[] columnNames = {"ID", "Title", "Author", "Genre", "Year"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);

        // Panel que combina búsqueda y tabla
        JPanel searchAndTablePanel = new JPanel(new BorderLayout());
        searchAndTablePanel.add(searchPanel, BorderLayout.NORTH); // Búsqueda arriba
        searchAndTablePanel.add(new JScrollPane(bookTable), BorderLayout.CENTER); // Tabla debajo

        // Botones de acción
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(refreshButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Agregar componentes
        add(inputPanel, BorderLayout.NORTH); // Panel de entrada arriba
        add(searchAndTablePanel, BorderLayout.CENTER); // Búsqueda y tabla en el centro
        add(buttonPanel, BorderLayout.SOUTH); // Botones abajo

        addListeners();
    }

    public void showEditDialog(Book book, Consumer<Book> onUpdate) {
        JTextField titleField = new JTextField(book.getTitle());
        JTextField authorField = new JTextField(book.getAuthor());
        JTextField genreField = new JTextField(book.getGenre());
        JTextField yearField = new JTextField(String.valueOf(book.getYear()));

        Object[] message = {
                "Title:", titleField,
                "Author:", authorField,
                "Genre:", genreField,
                "Year:", yearField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Edit Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int updatedYear = Integer.parseInt(yearField.getText());
                Book updatedBook = new Book.Builder()
                        .setId(book.getId())
                        .setTitle(titleField.getText())
                        .setAuthor(authorField.getText())
                        .setGenre(genreField.getText())
                        .setYear(updatedYear)
                        .build();
                onUpdate.accept(updatedBook);
            } catch (NumberFormatException e) {
                showError("Year must be a number!");
            }
        }
    }

    public void clearInputFields() {
        titleInput.setText("");
        authorInput.setText("");
        genreInput.setText("");
        yearInput.setText("");
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void updateBookTable(List<Book> books) {
        tableModel.setRowCount(0);
        for (Book book : books) {
            tableModel.addRow(new Object[]{
                    book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getYear()
            });
        }
    }

    //Selecciona el id del libro
    public int getSelectedBookId() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow >= 0) {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    //Listeners
    private void addListeners() {
        // Agregar libro
        addBookButton.addActionListener(e -> {
            try {
                String title = titleInput.getText();
                String author = authorInput.getText();
                String genre = genreInput.getText();
                int year = Integer.parseInt(yearInput.getText());

                Book newBook = new Book.Builder()
                        .setTitle(title)
                        .setAuthor(author)
                        .setGenre(genre)
                        .setYear(year)
                        .build();
                registerBookController.addBook(newBook);
                updateBookTable(searchBookController.getAllBooks()); //Actualizar tabla
                clearInputFields();
            } catch (NumberFormatException ex) {
                showError("Year must be a number!");
            }
        });

        // Botón de búsqueda
        searchButton.addActionListener(e -> {
            String query = searchField.getText().toLowerCase();
            List<Book> selectedBooks = searchBookController.getBooksByQuery(query);
            updateBookTable(selectedBooks);
        });

        // Botón de filtro
        filterButton.addActionListener(e -> {
            String genre = (String) genreFilterComboBox.getSelectedItem();
            List<Book> selectedBooks;
            if(genreFilterComboBox.getSelectedIndex()==0){
                selectedBooks = searchBookController.getAllBooks();
            }else {
                selectedBooks = searchBookController.getBooksByGenre(genre);
            }

            updateBookTable(selectedBooks);
        });

        // Refrescar tabla
        refreshButton.addActionListener(e -> updateBookTable(searchBookController.getAllBooks()));

        // Editar libro
        editButton.addActionListener(e -> {
            int selectedBookId = getSelectedBookId();
            if (selectedBookId != -1) {
                Book book = searchBookController.getBookById(selectedBookId);
                if (book != null) {
                    showEditDialog(book, updatedBook -> {
                        editBookController.updateBook(updatedBook);
                        updateBookTable(searchBookController.getAllBooks());
                    });
                }
            } else {
                showMessage("Please select a book to edit.");
            }
        });

        // Eliminar libro
        deleteButton.addActionListener(e -> {
            int selectedBookId = getSelectedBookId();
            if (selectedBookId != -1) {
                int confirmation = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete this book?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    editBookController.deleteBook(selectedBookId);
                    updateBookTable(searchBookController.getAllBooks());
                }
            } else {
                showMessage("Please select a book to delete.");
            }
        });
    }


}