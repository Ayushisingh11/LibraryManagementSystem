//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Book {
    private String title;
    private String author;
    private int edition;
    private int totalCopies;
    private int availableCopies;
    private int  rating;

    public Book(String title, String author, int edition, int totalCopies) {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.rating = 0;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    // Method to borrow a book
    public boolean borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            return true;
        }
        return false;
    }

    // Method to return a book
    public void returnBook() {
        availableCopies++;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", edition=" + edition +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                ", rating=" + rating +
                '}';
    }
    static class Library{
    private List<Book> books;

    public Library(){
        this.books=new ArrayList<>();
    }
    //method to add a book to library
        public void addBook(Book book){
        books.add(book);
        System.out.println("Book added" + book.getTitle());
        }

    //method to delete a book
        public void deleteBook(Book book){
        books.remove(book);
        System.out.println("Book deleted" + book.getTitle());
        }
    //method to read all books
        public void readBook(){
        if(books.isEmpty()){
            System.out.println("No Books found");}
        for(Book book : books){
            System.out.println(book);
        }
        }
    //method to search a book by title
    class BookNotFoundException extends Exception {
        public BookNotFoundException(String message) {
            super(message);
        }
    }


        public Book searchByTitle(String title) throws BookNotFoundException {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    return book;
                }
            }
            throw new BookNotFoundException("Book not found " + title);
        }
        //Method to search for a book by author
        public List<Book> searchByAuthor(String author) throws BookNotFoundException {
            List<Book> result = new ArrayList<>();
            for (Book book : books) {
                if (book.getAuthor().equalsIgnoreCase(author)) {
                    result.add(book);
                }
            }
            return result;

        }
        // Method to update book rating
        public void updateRating(Book book, int rating) {
            book.setRating(rating);
            System.out.println("Rating updated successfully for: " + book.getTitle());
        }
        }
    }


    public class LibraryManagementSystem {
    public static void main(String[] args) throws Book.Library.BookNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Book.Library library = new Book.Library();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search By Title");
            System.out.println("4. Search By Author");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Update Rating");
            System.out.println("8. List of all books");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nEnter book details:");
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Edition: ");
                    int edition = scanner.nextInt();
                    System.out.print("Total Copies: ");
                    int totalCopies = scanner.nextInt();
                    Book newBook = new Book(title, author, edition, totalCopies);
                    library.addBook(newBook);
                    break;
                case 2:
                    System.out.print("\nEnter title of the book to remove: ");
                    String removeTitle = scanner.nextLine();
                    Book bookToRemove = library.searchByTitle(removeTitle);
                    if (bookToRemove != null) {
                        library.deleteBook(bookToRemove);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("\nEnter title of the book to search: ");
                    String searchTitle = scanner.nextLine();
                    Book foundByTitle = library.searchByTitle(searchTitle);
                    if (foundByTitle != null) {
                        System.out.println("Book found: " + foundByTitle);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.print("\nEnter author of the book to search: ");
                    String searchAuthor = scanner.nextLine();
                    List<Book> foundByAuthor = library.searchByAuthor(searchAuthor);
                    if (!foundByAuthor.isEmpty()) {
                        System.out.println("Books found by " + searchAuthor + ":");
                        for (Book book : foundByAuthor) {
                            System.out.println(book);
                        }
                    } else {
                        System.out.println("No books found by " + searchAuthor + ".");
                    }
                    break;
                case 5:
                    System.out.print("\nEnter title of the book to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    Book borrowBook = library.searchByTitle(borrowTitle);
                    if (borrowBook != null) {
                        if (borrowBook.borrowBook()) {
                            System.out.println("Book borrowed successfully: " + borrowTitle);
                        } else {
                            System.out.println("No copies available for borrowing.");
                        }
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 6:
                    System.out.print("\nEnter title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    Book returnBook = library.searchByTitle(returnTitle);
                    if (returnBook != null) {
                        returnBook.returnBook();
                        System.out.println("Book returned successfully: " + returnTitle);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 7:
                    System.out.print("\nEnter title of the book to update rating: ");
                    String updateTitle = scanner.nextLine();
                    Book updateBook = library.searchByTitle(updateTitle);
                    if (updateBook != null) {
                        System.out.print("Enter new rating for " + updateTitle + ": ");
                        int newRating = scanner.nextInt();
                        library.updateRating(updateBook, newRating);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 8:
                    System.out.print("list of all books:\n ");
                    library.readBook();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

