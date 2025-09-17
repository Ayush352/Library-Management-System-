package model;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private boolean isAvailable;

    public Book(String title, String author, String isbn, int publicationYear) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Book title cannot be empty.");
        if (author == null || author.isBlank())
            throw new IllegalArgumentException("Author cannot be empty.");
        if (isbn == null || isbn.isBlank())
            throw new IllegalArgumentException("ISBN cannot be empty.");
        if (publicationYear <= 0)
            throw new IllegalArgumentException("Invalid publication year.");

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ", " + publicationYear + ")";
    }
}
