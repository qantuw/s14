/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qantuw.simulacropc2.com_2312044.model;

/**
 *
 * @author ASUS
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int year;

    private Book(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.author = builder.author;
        this.genre = builder.genre;
        this.year = builder.year;
    }

    public static class Builder {
        private int id;
        private String title;
        private String author;
        private String genre;
        private int year;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
    @Override
    public String toString() {
        return String.format("Book{id=%d, title='%s', author='%s', genre='%s', year=%d}",
                id, title, author, genre, year);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

   
    }
     
      