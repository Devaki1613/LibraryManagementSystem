import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private static final String FILE_NAME = "books.txt";

    public Library() {
        books = new ArrayList<>();
        loadBooksFromFile();
    }

    private void loadBooksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String title = data[1];
                String author = data[2];
                boolean available = Boolean.parseBoolean(data[3]);
                books.add(new Book(id, title, author, available));
            }
        } catch (IOException e) {
            System.out.println("No data file found. Starting with an empty library.");
        }
    }

    private void saveBooksToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                bw.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.isAvailable());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile();
        System.out.println("✅ Book added successfully!");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void searchBook(String keyword) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ No books found for keyword: " + keyword);
        }
    }

    public void borrowBook(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    saveBooksToFile();
                    System.out.println("✅ Book borrowed successfully.");
                } else {
                    System.out.println("❌ Book is already borrowed.");
                }
                return;
            }
        }
        System.out.println("❌ Book not found.");
    }

    public void returnBook(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    saveBooksToFile();
                    System.out.println("✅ Book returned successfully.");
                } else {
                    System.out.println("❌ This book was not borrowed.");
                }
                return;
            }
        }
        System.out.println("❌ Book not found.");
    }

    public void deleteBook(int bookId) {
        boolean removed = books.removeIf(book -> book.getId() == bookId);
        if (removed) {
            saveBooksToFile();
            System.out.println("✅ Book deleted.");
        } else {
            System.out.println("❌ Book not found.");
        }
    }
}
