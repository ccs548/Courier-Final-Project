import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MailingServiceUI {
    private Scanner scanner;
    private SimpleDateFormat dateFormatter;
    private MailingService mailingService;

    public MailingServiceUI() {
        scanner = new Scanner(System.in);
        mailingService = new MailingService();
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("=====================================================");
            System.out.println("                       Main Menu                     ");
            System.out.println("=====================================================");
            System.out.println("1. Estimate Cost");
            System.out.println("2. Add Package");
            System.out.println("3. Display All Packages");
            System.out.println("4. Search Package");
            System.out.println("5. Edit Package");
            System.out.println("6. Delete Package");  // New option for deleting a package
            System.out.println("7. View Revision History");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    estimateCost();
                    break;
                case 2:
                    addPackage();
                    break;
                case 3:
                    displayPackages();
                    break;
                case 4:
                    searchPackage();
                    break;
                case 5:
                    editPackage();  // Ensure this method is called correctly
                    break;
                case 6:
                    deletePackage();  // Call the new method to delete a package
                    break;
                case 7:
                    viewRevisionHistory();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private void deletePackage() {
        System.out.print("Enter the Tracking ID of the package to delete: ");
        int trackingId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = mailingService.deletePackage(trackingId);
        if (success) {
            System.out.println("Package deleted successfully.");
        } else {
            System.out.println("No package found with that Tracking ID.");
        }
    }

    private void estimateCost() {
        System.out.println("=====================================================");
        System.out.println("                Estimate Shipping Cost               ");
        System.out.println("=====================================================");
        System.out.print("Enter the weight of the package (in pounds): ");
        double weight = scanner.nextDouble();
        double cost = mailingService.estimateCost(weight);
        System.out.printf("The estimated cost is $%.2f\n", cost);
    }

    private void addPackage() {
        System.out.println("=====================================================");
        System.out.println("                    Add a Package                    ");
        System.out.println("=====================================================");
        System.out.print("Enter the weight of the package (in pounds): ");
        double weight = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter a description of the package: ");
        String description = scanner.nextLine();
        System.out.print("Enter the destination address: ");
        String destinationAddress = scanner.nextLine();
        System.out.print("Enter the mailed date (yyyy-MM-dd): ");
        Date mailedDate = parseDate(scanner.nextLine());
        System.out.print("Enter the expected arrival date (yyyy-MM-dd): ");
        Date expectedArrivalDate = parseDate(scanner.nextLine());

        int trackingId = mailingService.addPackage(weight, description, destinationAddress, mailedDate, expectedArrivalDate);
        System.out.printf("Package added successfully with Tracking ID: %d\n", trackingId);
    }

    private void displayPackages() {
        System.out.println("=====================================================");
        System.out.println("                  Display All Packages               ");
        System.out.println("=====================================================");
        System.out.println(mailingService.getPackagesDetails());
    }

    private void searchPackage() {
        System.out.println("=====================================================");
        System.out.println("                   Search for Package                ");
        System.out.println("=====================================================");
        System.out.print("Enter the Tracking ID of the package: ");
        int trackingId = scanner.nextInt();
        Package foundPackage = mailingService.searchPackage(trackingId);
        if (foundPackage != null) {
            System.out.println(foundPackage);
        } else {
            System.out.println("No package found with that Tracking ID.");
        }
    }

    private void viewRevisionHistory() {
        System.out.println("=====================================================");
        System.out.println("                   Revision History                  ");
        System.out.println("=====================================================");
        System.out.println(mailingService.getRevisionHistory());
    }

    private void editPackage() {
        System.out.print("Enter the Tracking ID of the package to edit: ");
        int trackingId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Package existingPackage = mailingService.searchPackage(trackingId);
        if (existingPackage != null) {
            System.out.println("Enter the new details for the package:");
            System.out.print("Enter the weight of the package (in pounds): ");
            double weight = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter a description of the package: ");
            String description = scanner.nextLine();
            System.out.print("Enter the destination address: ");
            String destinationAddress = scanner.nextLine();
            System.out.print("Enter the mailed date (yyyy-MM-dd): ");
            Date mailedDate = parseDate(scanner.nextLine());
            System.out.print("Enter the expected arrival date (yyyy-MM-dd): ");
            Date expectedArrivalDate = parseDate(scanner.nextLine());

            boolean success = mailingService.editPackage(trackingId, weight, description, destinationAddress, mailedDate, expectedArrivalDate);
            if (success) {
                System.out.println("Package updated successfully.");
            } else {
                System.out.println("Failed to update package.");
            }
        } else {
            System.out.println("No package found with that Tracking ID.");
        }
    }

    private Date parseDate(String dateStr) {
        try {
            return dateFormatter.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }
}
