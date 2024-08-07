import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private List<Package> packageDatabase = new ArrayList<>();

    public static void main(String[] args) {
        Pages pages = new Pages();
        pages.navigate();
    }

    // Method to add a package
    private void addPackage() {
        System.out.println("Enter package details...");
        // Implementation code for adding a package
    }

    // Method to display all packages
    private void displayPackages() {
        System.out.println("Displaying all packages...");
        // Implementation code for displaying packages
    }

    // Method to search for a package
    private void searchPackage() {
        System.out.println("Enter package ID to search...");
        // Implementation code for searching a package
    }

    // Nested Pages class for navigation
    public static class Pages {
        private Scanner scanner;
        private int currentPage;

        public Pages() {
            this.scanner = new Scanner(System.in);
            this.currentPage = 1;
        }

        public void navigate() {
            while (true) {
                switch (currentPage) {
                    case 1:
                        showPage1();
                        break;
                    case 2:
                        showPage2();
                        break;
                    case 3:
                        showPage3();
                        break;
                    default:
                        System.out.println("Invalid page number.");
                        break;
                }
            }
        }

        private void showPage1() {
            System.out.println("\nWelcome to Page 1");
            System.out.println("1. Go to Courier Service System");
            System.out.println("2. Go to Page 2");
            System.out.println("3. Go to Page 3");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            handleChoice(choice);
        }

        private void showPage2() {
            System.out.println("\nWelcome to Page 2");
            System.out.println("1. Go to Page 1");
            System.out.println("2. Go to Courier Service System");
            System.out.println("3. Go to Page 3");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            handleChoice(choice);
        }

        private void showPage3() {
            System.out.println("\nWelcome to Page 3");
            System.out.println("1. Go to Page 1");
            System.out.println("2. Go to Page 2");
            System.out.println("3. Go to Courier Service System");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            handleChoice(choice);
        }

        private void handleChoice(int choice) {
            switch (choice) {
                case 1:
                    currentPage = 1;
                    break;
                case 2:
                    currentPage = 2;
                    break;
                case 3:
                    currentPage = 3;
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        private void showCourierServiceSystem() {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nWelcome to our Courier Service System");
                System.out.println("1. Add Package");
                System.out.println("2. Display All Packages");
                System.out.println("3. Search Package");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        // Add package
                        // main.addPackage();
                        break;
                    case 2:
                        // Display packages
                        // main.displayPackages();
                        break;
                    case 3:
                        // Search package
                        // main.searchPackage();
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
