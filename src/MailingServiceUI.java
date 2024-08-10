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
            System.out.println("5. Exit");
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
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private void estimateCost() {
        System.out.println("=====================================================");
        System.out.println("                     Estimate Cost                   ");
        System.out.println("=====================================================");
        System.out.print("Enter package weight: ");
        double weight = scanner.nextDouble();
        scanner.nextLine();
        double cost = mailingService.estimateCost(weight);
        System.out.println("Estimated Cost: $" + cost);
        System.out.println("Press enter to return to main page");
        scanner.nextLine();
    }

    private void addPackage() {
        System.out.println("=====================================================");
        System.out.println("                     Add Package                     ");
        System.out.println("=====================================================");
        System.out.print("Enter package weight: ");
        double weight = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter package description: ");
        String description = scanner.nextLine();
        System.out.print("Enter destination address: ");
        String destinationAddress = scanner.nextLine();
        Date updateDate = getDateInput("Enter the current date (yyyy-MM-dd):");
        Date expectedArrivalDate = getDateInput("Enter expected arrival date (yyyy-MM-dd):");
        int trackingId = mailingService.addPackage(weight, description, destinationAddress, updateDate, expectedArrivalDate);
        System.out.println("Package added with Tracking ID: " + trackingId);
        System.out.println(getHeader());
        System.out.println(mailingService.searchPackage(trackingId));
        System.out.println("Press enter to return to main page");
        scanner.nextLine();
    }

    private void displayPackages() {
        System.out.println("=====================================================");
        System.out.println("                  Display Packages                   ");
        System.out.println("=====================================================");
        System.out.println(getHeader());
        System.out.println(mailingService.getPackagesDetails());
        System.out.println("Press enter to return to main page");
        scanner.nextLine();
    }

    private void searchPackage() {
        System.out.println("=====================================================");
        System.out.println("                    Search Package                   ");
        System.out.println("=====================================================");
        System.out.print("Please enter the tracking ID (must be an integer): ");
        int trackingId = scanner.nextInt();
        Package p = mailingService.searchPackage(trackingId);
        if (p == null) {
            System.out.println("The package with tracking ID " + trackingId + " is not found in our mailing service system.");
            return;
        }
        System.out.println("------------------------------------------------------");
        System.out.println(getHeader());
        System.out.println(p);
        System.out.println("------------------Tracking History---------------------");
        System.out.println(getTrackingRecordHeader());
        for (TrackingRecord trackingRecord: p.getTrackingHistory()) {
            System.out.println(trackingRecord);
        }
        System.out.println("Do you want to update or delete this package?");
        System.out.println("1. Update Package Status");
        System.out.println("2. Delete Package");
        System.out.println("3. Go Back to Main Menu");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                updatePackageRecord(trackingId);
                break;
            case 2:
                deletePackage(trackingId);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
        }
    }

    private void updatePackageRecord(int trackingId) {
        System.out.println("=====================================================");
        System.out.println("                    Update Package                   ");
        System.out.println("=====================================================");
        Package p = mailingService.searchPackage(trackingId);
        PackageStatus status = p.getTrackingRecord().getStatus();
        if (status == PackageStatus.DELIVERED) {
            System.out.println("This package is already delivered. Cannot update it anymore");
            return;
        }
        Date updateDate = getDateInput("Enter the current date (yyyy-MM-dd):");
        System.out.println("Select new status: ");
        System.out.println("1. Packed");
        System.out.println("2. In Transit");
        System.out.println("3. Delivered");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                status = PackageStatus.PACKED;
                break;
            case 2:
                status = PackageStatus.IN_TRANSIT;
                break;
            case 3:
                status = PackageStatus.DELIVERED;
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
        }
        if (status != PackageStatus.DELIVERED) {
            Date expectedArrivalDate = getDateInput("Enter the new expected arrival Date: ");
            mailingService.updatePackageStatus(trackingId, status, updateDate, expectedArrivalDate);
        } else {
            mailingService.updatePackageStatus(trackingId, status, updateDate, updateDate); // today is the delivery date
        }
        System.out.println("Successfully update package details. Press enter to return to main page");
        scanner.nextLine();
    }

    private void deletePackage(int trackingId) {
        System.out.println("=====================================================");
        System.out.println("                    Delete Package                   ");
        System.out.println("=====================================================");
        mailingService.removePackage(trackingId);
        System.out.println("Successfully deleted package " + trackingId + ". Returning to main menu.");
    }

    private Date getDateInput(String prompt) {
        Date date = null;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            String dateInput = scanner.nextLine();
            try {
                date = dateFormatter.parse(dateInput);
                valid = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }
        return date;
    }

    private static String getHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-12s", "Tracking ID"));
        sb.append(String.format("%-8s", "Weight"));
        sb.append(String.format("%-10s", "Cost"));
        sb.append(String.format("%-15s", "Description"));
        sb.append(String.format("%-30s", "Destination Address"));
        sb.append(String.format("%-12s", "Status"));
        sb.append(String.format("%-20s", "Expected Date"));
        sb.append(String.format("%-15s", "Update Date"));
        return sb.toString();
    }

    private static String getTrackingRecordHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-12s", "Status"));
        sb.append(String.format("%-20s", "Expected Arrival Date"));
        sb.append(String.format("%-15s", "UpdateDate"));
        return sb.toString();
    }

}
