import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MailingService {
    private List<Package> packageDatabase;
    private List<TrackingRecord> revisionHistory; // NEW FIELD to store revision history
    private int count = 0;
    private final static int COST_PER_POUND = 5;

    public MailingService() {
        this.packageDatabase = new ArrayList<>();
        this.revisionHistory = new ArrayList<>(); // Initialize revision history
        // Create package objects
        addPackage(5.0, "Books", "123 Maple St, Springfield", new Date(System.currentTimeMillis() - 86400000 * 5), new Date(System.currentTimeMillis() + 86400000 * 3));
        addPackage(2.3, "Clothing", "456 Elm St, Metropolis", new Date(System.currentTimeMillis() - 86400000 * 3), new Date(System.currentTimeMillis() - 86400000 * 1));
        addPackage(10.0, "Electronics", "789 Oak St, Gotham", new Date(), new Date(System.currentTimeMillis() + 86400000 * 2));
        addPackage(1.5, "Toys", "321 Pine St, Star City", new Date(), new Date(System.currentTimeMillis() + 86400000 * 2));
        addPackage(7.8, "Furniture", "654 Cedar St, Central City", new Date(), new Date(System.currentTimeMillis() + 86400000 * 2));
    }

    public double estimateCost(double weight) {
        return weight * COST_PER_POUND;
    }

    public int addPackage(double weight, String description, String destinationAddress, Date mailedDate, Date expectedArrivalDate) {
        int trackingId = generateTrackingId();
        TrackingRecord trackingRecord = new TrackingRecord(PackageStatus.PACKED, mailedDate, expectedArrivalDate, "ADD", description);
        Package newPackage = new Package(trackingId, weight, estimateCost(weight), description, destinationAddress, trackingRecord);
        packageDatabase.add(newPackage);
        revisionHistory.add(trackingRecord); // Log the action
        return trackingId;
    }

    public boolean deletePackage(int trackingId) {
        Package foundPackage = searchPackage(trackingId);
        if (foundPackage != null) {
            packageDatabase.remove(foundPackage);
            TrackingRecord trackingRecord = new TrackingRecord(PackageStatus.REMOVED, new Date(), null, "DELETE", "Package removed with ID: " + trackingId);
            revisionHistory.add(trackingRecord); // Log the action
            return true;
        }
        return false;
    }

    public boolean modifyPackage(int trackingId, String newDescription, double newWeight) {
        Package foundPackage = searchPackage(trackingId);
        if (foundPackage != null) {
            foundPackage.setDescription(newDescription);
            foundPackage.setWeight(newWeight);
            foundPackage.setCost(estimateCost(newWeight));
            TrackingRecord trackingRecord = new TrackingRecord(PackageStatus.MODIFIED, new Date(), foundPackage.getTrackingRecord().getExpectedArrivalDate(), "MODIFY", "Package modified with ID: " + trackingId);
            revisionHistory.add(trackingRecord); // Log the action
            return true;
        }
        return false;
    }

    public boolean editPackage(int trackingId, double weight, String description, String destinationAddress, Date mailedDate, Date expectedArrivalDate) {
        Package foundPackage = searchPackage(trackingId);
        if (foundPackage != null) {
            foundPackage.setWeight(weight);
            foundPackage.setDescription(description);
            foundPackage.setDestinationAddress(destinationAddress);
            foundPackage.getTrackingRecord().setUpdateDate(mailedDate);
            foundPackage.getTrackingRecord().setExpectedArrivalDate(expectedArrivalDate);

            TrackingRecord trackingRecord = new TrackingRecord(PackageStatus.MODIFIED, new Date(), expectedArrivalDate, "EDIT", "Package edited with ID: " + trackingId);
            revisionHistory.add(trackingRecord); // Log the edit action
            return true;
        }
        return false;
    }

    public String getPackagesDetails() {
        StringBuilder sb = new StringBuilder();
        for (Package p : packageDatabase) {
            sb.append(p.toString()).append("\n");  // Properly appending a newline after each package's details
        }
        return sb.toString();
    }

    public String getRevisionHistory() {
        StringBuilder sb = new StringBuilder();
        for (TrackingRecord record : revisionHistory) {
            sb.append(record.toString()).append("\n");  // Properly appending a newline after each tracking record's details
        }
        return sb.toString();
    }

    public Package searchPackage(int trackingId) {
        for (Package p : packageDatabase) {
            if (p.getTrackingId() == trackingId) {
                return p;
            }
        }
        return null;
    }

    private int generateTrackingId() {
        return ++count;
    }
}
