import java.util.*;
import java.time.LocalDate;

class Book {
    String isbn, title, author, genre;
    boolean available = true;

    Book(String i, String t, String a, String g) {
        isbn = i; title = t; author = a; genre = g;
    }
}

class Member {
    String id, name;
    Member(String i, String n) { id = i; name = n; }
}

public class LibraryManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Member> members = new ArrayList<>();

    static void addBook() {
        System.out.print("ISBN: "); String i = sc.nextLine();
        System.out.print("Title: "); String t = sc.nextLine();
        System.out.print("Author: "); String a = sc.nextLine();
        System.out.print("Genre: "); String g = sc.nextLine();
        books.add(new Book(i, t, a, g));
        System.out.println("Book added successfully!");
    }

    static void addMember() {
        System.out.print("Member ID: "); String i = sc.nextLine();
        System.out.print("Name: "); String n = sc.nextLine();
        members.add(new Member(i, n));
        System.out.println("Member registered!");
    }

    static void displayBooks(boolean onlyAvailable) {
        for (Book b : books) {
            if (!onlyAvailable || b.available) {
                System.out.println("ISBN: " + b.isbn);
                System.out.println("Title: " + b.title);
                System.out.println("Author: " + b.author);
                System.out.println("Genre: " + b.genre);
                System.out.println("Status: " + (b.available ? "Available" : "Borrowed"));
                System.out.println("----------------------------------------");
            }
        }
    }

    static void searchBooks() {
        System.out.print("Keyword: ");
        String k = sc.nextLine().toLowerCase();
        for (Book b : books) {
            if (b.title.toLowerCase().contains(k) ||
                b.author.toLowerCase().contains(k) ||
                b.genre.toLowerCase().contains(k)) {
                System.out.println("ISBN: " + b.isbn);
                System.out.println("Title: " + b.title);
                System.out.println("Author: " + b.author);
                System.out.println("Genre: " + b.genre);
                System.out.println("Status: " + (b.available ? "Available" : "Borrowed"));
                System.out.println("----------------------------------------");
            }
        }
    }

    static void borrowBook() {
        System.out.print("Member ID: "); String mid = sc.nextLine();
        System.out.print("Book ISBN: "); String isbn = sc.nextLine();

        Book book = books.stream().filter(b -> b.isbn.equals(isbn)).findFirst().orElse(null);
        Member mem = members.stream().filter(m -> m.id.equals(mid)).findFirst().orElse(null);

        if (book == null || mem == null || !book.available) {
            System.out.println("Borrow failed.");
            return;
        }
        book.available = false;
        LocalDate due = LocalDate.now().plusDays(14);
        System.out.println("Book borrowed successfully!");
        System.out.println("Member: " + mem.name);
        System.out.println("Book: " + book.title);
        System.out.println("Due Date: " + due);
    }

    static void returnBook() {
        System.out.print("Book ISBN: ");
        String isbn = sc.nextLine();
        for (Book b : books) {
            if (b.isbn.equals(isbn)) {
                b.available = true;
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Add Book 2.Add Member 3.All Books 4.Available Books");
            System.out.println("5.Search 6.Borrow 7.Return 8.Exit");
            System.out.print("Choice: ");
            int c = Integer.parseInt(sc.nextLine());

            switch (c) {
                case 1 -> addBook();
                case 2 -> addMember();
                case 3 -> displayBooks(false);
                case 4 -> displayBooks(true);
                case 5 -> searchBooks();
                case 6 -> borrowBook();
                case 7 -> returnBook();
                case 8 -> System.exit(0);
            }
        }
    }
}
