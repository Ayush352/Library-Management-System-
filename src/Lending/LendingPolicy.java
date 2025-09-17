package Lending;

import model.Book;
import model.Patron;

public interface LendingPolicy {
    boolean checkout(Book book, Patron patron);
    boolean returnBook(Book book, Patron patron);
}
