import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private List<Package> packageDatabase = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to our Courier Service System");
            System.out.println("1. Add Package");
            System.out.println("2. Display All Packages");
            System.out.println("3. Search Package"); // we can update and remove package after search
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // addPackage();
                    break;
                case 2:
                    // displayPackages();
                    break;
                case 3:
                    // searchPackage();
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
