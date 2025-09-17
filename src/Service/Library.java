package Service;

import Lending.LendingPolicy;
import model.Book;
import model.Patron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private Map<String, Book> books = new HashMap<>();
    private Map<String, Patron> patrons = new HashMap<>();
    private LendingPolicy lendingPolicy;

    public Library(LendingPolicy lendingPolicy){
        this.lendingPolicy = lendingPolicy;
    }

    //Book Management

    public void addBook(Book book){
        books.put(book.getIsbn(), book);
    }

    public void removeBook(String isbn){
        books.remove(isbn);
    }

    public void updateBook(String isbn, String newTitle, String newAuthor, int newYear){
        Book book = books.get(isbn);
        if(book!= null){
            books.put(isbn, new Book(newTitle, newAuthor,isbn, newYear));
        }
    }

    public List<Book> searchByTitle(String title){
        List<Book> results = new ArrayList<>();
        for(Book book: books.values()){
            if(book.getTitle().equalsIgnoreCase(title)){
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> searchByAuthor(String author){
        List<Book> results = new ArrayList<>();
        for(Book book: books.values()){
            if(book.getAuthor().equalsIgnoreCase(author)){
                results.add(book);
            }
        }
        return results;
    }

    public Book searchByISBN(String isbn){
        return books.get(isbn);
    }

    //Patron Management

    public void addPatron(Patron patron){
        patrons.put(patron.getPatronId(), patron);
    }

    public void removePatron(String patronId){
        patrons.remove(patronId);
    }

    public void updatePatron(String personId, String newName){
        patrons.put(personId, new Patron(newName, personId));
    }

    public boolean checkoutBook(String isbn, String patronId) {
        return lendingPolicy.checkout(books.get(isbn), patrons.get(patronId));
    }

    public boolean returnBook(String isbn, String patronId) {
        return lendingPolicy.returnBook(books.get(isbn), patrons.get(patronId));
    }

    // Inventory
    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                available.add(book);
            }
        }
        return available;
    }

    public List<Book> getBorrowedBooks() {
        List<Book> borrowed = new ArrayList<>();
        for (Book book : books.values()) {
            if (!book.isAvailable()) {
                borrowed.add(book);
            }
        }
        return borrowed;
    }



}
