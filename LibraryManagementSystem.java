import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

// Book Class
class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;
    private String issuedTo;
    private LocalDate issueDate;
    private LocalDate dueDate;

    public Book(String bookId, String title, String author, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false;
        this.issuedTo = null;
        this.issueDate = null;
        this.dueDate = null;
    }

    // Getters
    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isIssued() { return isIssued; }
    public String getIssuedTo() { return issuedTo; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }

    // Setters
    public void setIssued(boolean issued) { isIssued = issued; }
    public void setIssuedTo(String issuedTo) { this.issuedTo = issuedTo; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    @Override
    public String toString() {
        String status = isIssued ? "Issued to " + issuedTo : "Available";
        return String.format("[%s] %s by %s - %s", bookId, title, author, status);
    }

    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("\nBook ID: ").append(bookId);
        details.append("\nTitle: ").append(title);
        details.append("\nAuthor: ").append(author);
        details.append("\nISBN: ").append(isbn);
        details.append("\nStatus: ").append(isIssued ? "Issued" : "Available");

        if (isIssued) {
            details.append("\nIssued to: ").append(issuedTo);
            details.append("\nIssue Date: ").append(issueDate);
            details.append("\nDue Date: ").append(dueDate);
        }
        return details.toString();
    }
}

// User Class
class User {
    private String userId;
    private String name;
    private String email;
    private List<String> issuedBooks;
    private int maxBooks;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.issuedBooks = new ArrayList<>();
        this.maxBooks = 3; // Maximum books a user can borrow
    }

    // Getters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<String> getIssuedBooks() { return issuedBooks; }
    public int getMaxBooks() { return maxBooks; }

    public boolean canIssueBook() {
        return issuedBooks.size() < maxBooks;
    }

    public void addBook(String bookId) {
        issuedBooks.add(bookId);
    }

    public void removeBook(String bookId) {
        issuedBooks.remove(bookId);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - Books issued: %d",
                userId, name, email, issuedBooks.size());
    }
}

// Library Class
class Library {
    private String name;
    private Map<String, Book> books;
    private Map<String, User> users;
    private int issuePeriodDays;

    public Library(String name) {
        this.name = name;
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.issuePeriodDays = 14; // Default issue period
    }

    public boolean addBook(Book book) {
        if (books.containsKey(book.getBookId())) {
            System.out.println("❌ Book with ID " + book.getBookId() + " already exists!");
            return false;
        }
        books.put(book.getBookId(), book);
        System.out.println("✓ Book '" + book.getTitle() + "' added successfully!");
        return true;
    }

    public boolean addUser(User user) {
        if (users.containsKey(user.getUserId())) {
            System.out.println("❌ User with ID " + user.getUserId() + " already exists!");
            return false;
        }
        users.put(user.getUserId(), user);
        System.out.println("✓ User '" + user.getName() + "' registered successfully!");
        return true;
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Books in " + name);
        System.out.println("=".repeat(60));
        for (Book book : books.values()) {
            System.out.println(book);
        }
        System.out.println("=".repeat(60) + "\n");
    }

    public void displayAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (!book.isIssued()) {
                availableBooks.add(book);
            }
        }

        if (availableBooks.isEmpty()) {
            System.out.println("No available books at the moment.");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Available Books");
        System.out.println("=".repeat(60));
        for (Book book : availableBooks) {
            System.out.println(book);
        }
        System.out.println("=".repeat(60) + "\n");
    }

    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No registered users.");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Registered Users");
        System.out.println("=".repeat(60));
        for (User user : users.values()) {
            System.out.println(user);
        }
        System.out.println("=".repeat(60) + "\n");
    }

    public boolean issueBook(String bookId, String userId) {
        // Validate book exists
        if (!books.containsKey(bookId)) {
            System.out.println("❌ Book with ID " + bookId + " not found!");
            return false;
        }

        // Validate user exists
        if (!users.containsKey(userId)) {
            System.out.println("❌ User with ID " + userId + " not found!");
            return false;
        }

        Book book = books.get(bookId);
        User user = users.get(userId);

        // Check if book is already issued
        if (book.isIssued()) {
            System.out.println("❌ Book '" + book.getTitle() + "' is already issued to " + book.getIssuedTo() + "!");
            return false;
        }

        // Check if user can issue more books
        if (!user.canIssueBook()) {
            System.out.println("❌ User '" + user.getName() + "' has reached maximum book limit (" + user.getMaxBooks() + ")!");
            return false;
        }

        // Issue the book
        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(issuePeriodDays);

        book.setIssued(true);
        book.setIssuedTo(user.getName());
        book.setIssueDate(issueDate);
        book.setDueDate(dueDate);

        user.addBook(bookId);

        System.out.println("✓ Book '" + book.getTitle() + "' issued to '" + user.getName() + "'");
        System.out.println("  Issue Date: " + issueDate);
        System.out.println("  Due Date: " + dueDate);
        return true;
    }

    public boolean returnBook(String bookId, String userId) {
        // Validate book exists
        if (!books.containsKey(bookId)) {
            System.out.println("❌ Book with ID " + bookId + " not found!");
            return false;
        }

        // Validate user exists
        if (!users.containsKey(userId)) {
            System.out.println("❌ User with ID " + userId + " not found!");
            return false;
        }

        Book book = books.get(bookId);
        User user = users.get(userId);

        // Check if book is issued
        if (!book.isIssued()) {
            System.out.println("❌ Book '" + book.getTitle() + "' is not currently issued!");
            return false;
        }

        // Check if book is issued to this user
        if (!user.getIssuedBooks().contains(bookId)) {
            System.out.println("❌ Book '" + book.getTitle() + "' is not issued to user '" + user.getName() + "'!");
            return false;
        }

        // Calculate fine if overdue
        LocalDate returnDate = LocalDate.now();
        long daysOverdue = ChronoUnit.DAYS.between(book.getDueDate(), returnDate);

        if (daysOverdue > 0) {
            int fine = (int) daysOverdue * 5; // $5 per day fine
            System.out.println("⚠️  Book is " + daysOverdue + " day(s) overdue!");
            System.out.println("   Fine: $" + fine);
        }

        // Return the book
        book.setIssued(false);
        book.setIssuedTo(null);
        book.setIssueDate(null);
        book.setDueDate(null);

        user.removeBook(bookId);

        System.out.println("✓ Book '" + book.getTitle() + "' returned successfully by '" + user.getName() + "'");
        return true;
    }

    public void searchBook(String keyword) {
        keyword = keyword.toLowerCase();
        List<Book> results = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(keyword) ||
                    book.getAuthor().toLowerCase().contains(keyword)) {
                results.add(book);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No books found matching '" + keyword + "'");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Search Results for '" + keyword + "'");
        System.out.println("=".repeat(60));
        for (Book book : results) {
            System.out.println(book);
        }
        System.out.println("=".repeat(60) + "\n");
    }

    public String getName() {
        return name;
    }
}

// Main Class with Interactive Menu
public class LibraryManagementSystem {
    private static Library library;
    private static Scanner scanner;

    public static void main(String[] args) {
        library = new Library("Vedant Central Library");
        scanner = new Scanner(System.in);

        initializeSampleData();

        runMenu();
    }

    private static void initializeSampleData() {
        // Add sample books
        library.addBook(new Book("B001", "Python Programming", "John Smith", "978-0-123456-78-9"));
        library.addBook(new Book("B002", "Data Structures", "Jane Doe", "978-0-234567-89-0"));
        library.addBook(new Book("B003", "Machine Learning", "Bob Johnson", "978-0-345678-90-1"));
        library.addBook(new Book("B004", "Web Development", "Alice Brown", "978-0-456789-01-2"));
        library.addBook(new Book("B005", "Java Programming", "David Wilson", "978-0-567890-12-3"));

        // Add sample users
        library.addUser(new User("U001", "Emma Watson", "emma@email.com"));
        library.addUser(new User("U002", "Harry Potter", "harry@email.com"));
        library.addUser(new User("U003", "Ron Weasley", "ron@email.com"));
    }

    private static void runMenu() {
        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    displayAllBooks();
                    break;
                case 2:
                    displayAvailableBooks();
                    break;
                case 3:
                    displayAllUsers();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    searchBook();
                    break;
                case 7:
                    addNewBook();
                    break;
                case 8:
                    registerNewUser();
                    break;
                case 9:
                    System.out.println("\n✓ Thank you for using " + library.getName() + "!");
                    System.out.println("Goodbye!\n");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice! Please try again.\n");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        " + library.getName().toUpperCase());
        System.out.println("=".repeat(60));
        System.out.println("1. Display All Books");
        System.out.println("2. Display Available Books");
        System.out.println("3. Display All Users");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Search Book");
        System.out.println("7. Add New Book");
        System.out.println("8. Register New User");
        System.out.println("9. Exit");
        System.out.println("=".repeat(60));
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void displayAllBooks() {
        library.displayBooks();
    }

    private static void displayAvailableBooks() {
        library.displayAvailableBooks();
    }

    private static void displayAllUsers() {
        library.displayUsers();
    }

    private static void issueBook() {
        System.out.print("\nEnter Book ID: ");
        String bookId = scanner.nextLine().trim();
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();
        System.out.println();
        library.issueBook(bookId, userId);
    }

    private static void returnBook() {
        System.out.print("\nEnter Book ID: ");
        String bookId = scanner.nextLine().trim();
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();
        System.out.println();
        library.returnBook(bookId, userId);
    }

    private static void searchBook() {
        System.out.print("\nEnter search keyword (title or author): ");
        String keyword = scanner.nextLine().trim();
        library.searchBook(keyword);
    }

    private static void addNewBook() {
        System.out.println("\n--- Add New Book ---");
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        System.out.println();

        library.addBook(new Book(bookId, title, author, isbn));
    }

    private static void registerNewUser() {
        System.out.println("\n--- Register New User ---");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.println();

        library.addUser(new User(userId, name, email));
    }
}