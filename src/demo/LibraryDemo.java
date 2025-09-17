package demo;

import Lending.StandardLendingPolicy;
import Service.Library;
import model.Book;
import model.Patron;

import java.util.Scanner;

public class LibraryDemo {
    public static void main(String[] args) {
        Library library = new Library(new StandardLendingPolicy());
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Add Patron");
            System.out.println("2. Update Patron");
            System.out.println("3. Remove Patron");
            System.out.println("4. Add Book");
            System.out.println("5. Update Book");
            System.out.println("6. Remove Book");
            System.out.println("7. Checkout Book");
            System.out.println("8. Return Book");
            System.out.println("9. View Available Books");
            System.out.println("10. View Borrowed Books");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Patron ID: ");
                    String pid = sc.nextLine();
                    System.out.print("Enter Patron Name: ");
                    String pname = sc.nextLine();
                    library.addPatron(new Patron(pname, pid));
                    System.out.println("✅ Patron added.");
                    break;

                case 2:
                    System.out.print("Enter Patron ID to update: ");
                    String upid = sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    library.updatePatron(upid, newName);
                    System.out.println("✅ Patron updated.");
                    break;

                case 3:
                    System.out.print("Enter Patron ID to remove: ");
                    String rid = sc.nextLine();
                    library.removePatron(rid);
                    System.out.println("✅ Patron removed.");
                    break;

                case 4:
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    library.addBook(new Book(title, author, isbn, year));
                    System.out.println("✅ Book added.");
                    break;

                case 5:
                    System.out.print("Enter ISBN of book to update: ");
                    String uisbn = sc.nextLine();
                    System.out.print("Enter new Title: ");
                    String ntitle = sc.nextLine();
                    System.out.print("Enter new Author: ");
                    String nauthor = sc.nextLine();
                    System.out.print("Enter new Year: ");
                    int nyear = sc.nextInt();
                    sc.nextLine();
                    library.updateBook(uisbn, ntitle, nauthor, nyear);
                    System.out.println("✅ Book updated.");
                    break;

                case 6:
                    System.out.print("Enter ISBN of book to remove: ");
                    String risbn = sc.nextLine();
                    library.removeBook(risbn);
                    System.out.println("✅ Book removed.");
                    break;

                case 7:
                    System.out.print("Enter ISBN: ");
                    String cisbn = sc.nextLine();
                    System.out.print("Enter Patron ID: ");
                    String cpid = sc.nextLine();
                    if(library.checkoutBook(cisbn,cpid)){
                        System.out.println("Checked out Successfully");
                    }
                    else {
                        System.out.println("Checkout failed (maybe unavailable or invalid).");
                    }
                    break;

                case 8:
                    System.out.print("Enter ISBN: ");
                    String risbn2 = sc.nextLine();
                    System.out.print("Enter Patron ID: ");
                    String rpid = sc.nextLine();
                    if(library.returnBook(risbn2,rpid)){
                        System.out.println("Book returned Successfully");
                    }
                    else {
                        System.out.println("Return failed (maybe not borrowed).");
                    }
                    break;

                case 9:
                    System.out.println("=== Available Books ===");
                    library.getAvailableBooks().forEach(System.out::println);
                    break;

                case 10:
                    System.out.println("=== Borrowed Books ===");
                    library.getBorrowedBooks().forEach(System.out::println);
                    break;

                case 0:
                    System.out.println("👋 Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("⚠️ Invalid choice, try again!");
            }
        }
    }
}
