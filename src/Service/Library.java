package Service;

import Lending.LendingPolicy;
import model.Book;
import model.Patron;

import java.util.*;

public class Library {

    private Map<String, Book> books = new HashMap<>();
    private Map<String, Patron> patrons = new HashMap<>();
    private LendingPolicy lendingPolicy;

    public Library(LendingPolicy lendingPolicy){
        if (lendingPolicy == null) {
            throw new IllegalArgumentException("Lending policy cannot be null.");
        }
        this.lendingPolicy = lendingPolicy;
    }

    //Book Management

    public void addBook(Book book){
        if (book == null){
            throw new IllegalArgumentException("Book cannot be null.");
        }
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN already exists.");
        }
        books.put(book.getIsbn(), book);
    }

    public void removeBook(String isbn){
        if (!books.containsKey(isbn)) {
            throw new NoSuchElementException("No book found with ISBN: " + isbn);
        }
        books.remove(isbn);
    }

    public void updateBook(String isbn, String newTitle, String newAuthor, int newYear){
        if (!books.containsKey(isbn)) {
            throw new NoSuchElementException("No book found with ISBN: " + isbn);
        }
        books.put(isbn, new Book(newTitle, newAuthor, isbn, newYear));
    }

    public List<Book> searchByTitle(String title){
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        List<Book> results = new ArrayList<>();
        for(Book book: books.values()){
            if(book.getTitle().equalsIgnoreCase(title)){
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> searchByAuthor(String author){
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }
        List<Book> results = new ArrayList<>();
        for(Book book: books.values()){
            if(book.getAuthor().equalsIgnoreCase(author)){
                results.add(book);
            }
        }
        return results;
    }

    public Book searchByISBN(String isbn){
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        return books.get(isbn);
    }

    //Patron Management

    public void addPatron(Patron patron){
        if (patron == null) {
            throw new IllegalArgumentException("Patron cannot be null.");
        }
        if (patrons.containsKey(patron.getPatronId())) {
            throw new IllegalArgumentException("Patron with this ID already exists.");
        }
        patrons.put(patron.getPatronId(), patron);
    }

    public void removePatron(String patronId){
        if (!patrons.containsKey(patronId)) {
            throw new NoSuchElementException("No patron found with ID: " + patronId);
        }
        patrons.remove(patronId);
    }

    public void updatePatron(String patronId, String newName){
        if (!patrons.containsKey(patronId))
            throw new NoSuchElementException("No patron found with ID: " + patronId);
        patrons.put(patronId, new Patron(newName, patronId));
    }

    public boolean checkoutBook(String isbn, String patronId) {
        Book book = books.get(isbn);
        Patron patron = patrons.get(patronId);

        if (book == null) {
            throw new NoSuchElementException("Book not found.");
        }
        if (patron == null) {
            throw new NoSuchElementException("Patron not found.");
        }

        return lendingPolicy.checkout(books.get(isbn), patrons.get(patronId));
    }

    public boolean returnBook(String isbn, String patronId) {

        Book book = books.get(isbn);
        Patron patron = patrons.get(patronId);

        if (book == null) {
            throw new NoSuchElementException("Book not found.");
        }
        if (patron == null) {
            throw new NoSuchElementException("Patron not found.");
        }

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
