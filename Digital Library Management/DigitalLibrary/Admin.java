package DigitalLibrary;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    private ArrayList<Book> books;
    private Scanner scanner;

    public Admin(ArrayList<Book> books, Scanner scanner) {
        this.books = books;
        this.scanner = scanner;
    }

    public void menu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Exit");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book category: ");
        String category = scanner.nextLine();

        books.add(new Book(title, author, category));
        System.out.println("Book added successfully!");
    }

    private void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}
