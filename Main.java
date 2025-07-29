import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Delete Book");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear input buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author, true));
                    break;
                case 2:
                    library.viewBooks();
                    break;
                case 3:
                    System.out.print("Enter keyword (title or author): ");
                    String keyword = sc.nextLine();
                    library.searchBook(keyword);
                    break;
                case 4:
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowId = sc.nextInt();
                    library.borrowBook(borrowId);
                    break;
                case 5:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                    break;
                case 6:
                    System.out.print("Enter Book ID to delete: ");
                    int deleteId = sc.nextInt();
                    library.deleteBook(deleteId);
                    break;
                case 7:
                    System.out.println("📘 Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        } while (choice != 7);

        sc.close();
    }
}
