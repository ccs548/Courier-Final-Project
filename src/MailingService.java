import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MailingService {
    private List<Package> packageDatabase;
    private int count = 0;
    private final static int COST_PER_POUND = 5;

    public MailingService() {
        this.packageDatabase = new ArrayList<>();
        // Create package objects
        addPackage(5.0, "Books", "123 Maple St, Springfield", new Date(System.currentTimeMillis() - 86400000 * 5), new Date(System.currentTimeMillis() + 86400000 * 3));
        addPackage(2.3, "Clothing", "456 Elm St, Metropolis", new Date(System.currentTimeMillis() - 86400000 * 3), new Date(System.currentTimeMillis() - 86400000 * 1));
        addPackage( 10.0, "Electronics", "789 Oak St, Gotham", new Date(), new Date(System.currentTimeMillis() + 86400000 * 2));
        addPackage(1.5, "Toys", "321 Pine St, Star City", new Date(), new Date(System.currentTimeMillis() + 86400000 * 2));
        addPackage(7.8, "Furniture", "654 Cedar St, Central City", new Date(), new Date(System.currentTimeMillis() + 86400000 * 2));
    }

    public double estimateCost(double weight) {
        return weight * COST_PER_POUND;
    }

    public int addPackage(double weight, String description, String destinationAddress, Date mailedDate, Date expectedArrivalDate) {
        int trackingId = generateTrackingId();
        TrackingRecord trackingRecord = new TrackingRecord(PackageStatus.PACKED, mailedDate, expectedArrivalDate);
        Package newPackage = new Package(trackingId, weight, estimateCost(weight), description, destinationAddress, trackingRecord);
        packageDatabase.add(newPackage);
        return trackingId;
    }

    public String getPackagesDetails() {
        StringBuilder sb = new StringBuilder();
        for (Package p: packageDatabase) {
            sb.append(p +"\n");
        }
        return sb.toString();
    }

    public Package searchPackage(int trackingId) {
        for (Package p: packageDatabase) {
            if (p.getTrackingId() == trackingId) {
                return p;
            }
        }
        return null;
    }

    public void updatePackageStatus(int trackingId, PackageStatus status, Date updateDate, Date expectedArrivalDate) {
        Package p = searchPackage(trackingId);
        TrackingRecord newTrackingRecord = new TrackingRecord(status, updateDate, expectedArrivalDate);
        p.setLatestTrackingRecordT(newTrackingRecord);
    }

    public void removePackage(int trackingId) {
        for (int i = 0; i < packageDatabase.size(); i++) {
            if (packageDatabase.get(i).getTrackingId() == trackingId) {
                packageDatabase.remove(i);
                return;
            }
        }
    }

    private int generateTrackingId() {
        count++;
        return count;
    }
}
