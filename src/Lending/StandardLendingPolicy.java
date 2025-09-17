package Lending;

import model.Book;
import model.Patron;

public class StandardLendingPolicy implements LendingPolicy {

    @Override
    public boolean checkout(Book book, Patron patron) {
        if (book != null && patron != null && book.isAvailable()) {
            book.setAvailable(false);
            patron.borrowBook(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(Book book, Patron patron) {
        if (book != null && patron != null && patron.getBorrowedBooks().contains(book)) {
            book.setAvailable(true);
            patron.returnBook(book);
            return true;
        }
        return false;
    }
}