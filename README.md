# 📚 Library Management System

A comprehensive console-based Library Management System built using Object-Oriented Programming principles in Java. This system allows librarians to manage books, users, and track book issuance and returns efficiently.

## 🎯 Features

- **Book Management**
  - Add new books to the library
  - View all books in the system
  - Display available books
  - Search books by title or author
  - Track book issue status

- **User Management**
  - Register new users
  - View all registered users
  - Track user borrowing history
  - Enforce borrowing limits (max 3 books per user)

- **Issue & Return System**
  - Issue books to registered users
  - Return books with automatic fine calculation
  - 14-day lending period
  - Late fee calculation ($5 per day for overdue books)
  - Validation checks for all operations

- **Interactive Menu**
  - User-friendly console interface
  - Easy navigation through all features
  - Real-time feedback and error handling

## 🛠️ Technologies Used

- **Language:** Java (JDK 8 or higher)
- **IDE:** IntelliJ IDEA (recommended)
- **Libraries:** 
  - `java.time` for date operations
  - `java.util` for collections

## 📋 Prerequisites

Before running this project, ensure you have:

- Java Development Kit (JDK) 8 or higher installed
- IntelliJ IDEA or any Java IDE
- Basic understanding of Java programming

## 🚀 Getting Started

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Vedantsam/library-management-system.git
   ```

2. **Navigate to the project directory**
   ```bash
   cd library-management-system
   ```

3. **Open in IntelliJ IDEA**
   - Open IntelliJ IDEA
   - Select `File > Open`
   - Navigate to the project folder and click `OK`

### Running the Application

1. **Compile and Run**
   - Open `LibraryManagementSystem.java`
   - Right-click on the file
   - Select `Run 'LibraryManagementSystem.main()'`

2. **Using the Application**
   - The program starts with pre-loaded sample data
   - Follow the interactive menu to perform operations
   - Enter the corresponding number for each action

## 📖 Usage Examples

### Sample Data
The system comes pre-loaded with:

**Books:**
- B001: Python Programming by John Smith
- B002: Data Structures by Jane Doe
- B003: Machine Learning by Bob Johnson
- B004: Web Development by Alice Brown
- B005: Java Programming by David Wilson

**Users:**
- U001: Emma Watson (emma@email.com)
- U002: Harry Potter (harry@email.com)
- U003: Ron Weasley (ron@email.com)

### Example Operations

**Issue a Book:**
```
Enter your choice: 4
Enter Book ID: B001
Enter User ID: U001
✓ Book 'Python Programming' issued to 'Emma Watson'
  Issue Date: 2025-10-23
  Due Date: 2025-11-06
```

**Return a Book:**
```
Enter your choice: 5
Enter Book ID: B001
Enter User ID: U001
✓ Book 'Python Programming' returned successfully by 'Emma Watson'
```

**Search for Books:**
```
Enter your choice: 6
Enter search keyword: Java
============================================================
Search Results for 'Java'
============================================================
[B005] Java Programming by David Wilson - Available
============================================================
```

## 🏗️ Project Structure

```
library-management-system/
│
├── LibraryManagementSystem.java  # Main class with menu system
├── Book.java                      # Book class (embedded)
├── User.java                      # User class (embedded)
├── Library.java                   # Library class (embedded)
└── README.md                      # Project documentation
```

## 🎨 Class Structure

### Book Class
- **Attributes:** bookId, title, author, isbn, isIssued, issuedTo, issueDate, dueDate
- **Methods:** getters, setters, toString(), getDetails()

### User Class
- **Attributes:** userId, name, email, issuedBooks, maxBooks
- **Methods:** canIssueBook(), addBook(), removeBook(), toString()

### Library Class
- **Attributes:** name, books (HashMap), users (HashMap), issuePeriodDays
- **Methods:** addBook(), addUser(), issueBook(), returnBook(), displayBooks(), searchBook()

## 🔧 Configuration

You can customize the following parameters in the code:

- **Maximum books per user:** Change `maxBooks` in User class (default: 3)
- **Lending period:** Change `issuePeriodDays` in Library class (default: 14 days)
- **Late fee per day:** Modify the fine calculation in `returnBook()` method (default: $5/day)

## 🐛 Error Handling

The system handles various error scenarios:
- Duplicate book/user IDs
- Non-existent book/user IDs
- Attempting to issue already issued books
- User borrowing limit exceeded
- Invalid return operations
- Invalid menu choices

## 📝 Future Enhancements

- [ ] Add database integration (MySQL/PostgreSQL)
- [ ] Implement GUI using JavaFX or Swing
- [ ] Add book reservation system
- [ ] Generate reports (most borrowed books, overdue books)
- [ ] Email notifications for due dates
- [ ] Multi-copy book management
- [ ] Fine payment tracking
- [ ] User authentication and authorization
- [ ] Book categories and filtering


## 👨‍💻 Author

**Vedantsam**
- GitHub: [@Vedantsam](https://github.com/Vedantsam)


---

⭐ **If you found this project helpful, please give it a star!** ⭐
