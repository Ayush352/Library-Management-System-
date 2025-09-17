package model;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String name;
    private String patronId;
    private List<Book> borrowingHistory;
    private List<Book> borrowedBooks;

    public Patron(String name, String patronId) {

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Patron name cannot be empty.");
        if (patronId == null || patronId.isBlank())
            throw new IllegalArgumentException("Patron ID cannot be empty.");

        this.name = name;
        this.patronId = patronId;
        this.borrowingHistory = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPatronId() {
        return patronId;
    }

    public List<Book> getBorrowingHistory() {
        return borrowingHistory;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (book == null){
            throw new IllegalArgumentException("Book cannot be null.");
        }
        borrowedBooks.add(book);
        borrowingHistory.add(book);
    }

    public void returnBook(Book book) {
        if (book == null){
            throw new IllegalArgumentException("Book cannot be null.");
        }
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Patron: " + name + " (ID: " + patronId + ")";
    }
}
