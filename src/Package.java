import java.util.*;

public class Package {
    private int trackingId;
    private double weight;
    private double cost;
    private String description;
    private String destinationAddress;
    private TrackingRecord latestTrackingRecord;
    private List<TrackingRecord> trackingHistory;

    public Package(int trackingId, double weight, double cost, String description, String destinationAddress, TrackingRecord latestTrackingRecord) {
        this.trackingId = trackingId;
        this.weight = weight;
        this.cost = cost;
        this.description = description;
        this.destinationAddress = destinationAddress;
        this.latestTrackingRecord = latestTrackingRecord;
        this.trackingHistory = new ArrayList<>();
        this.trackingHistory.add(latestTrackingRecord);
    }

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }


    public TrackingRecord getTrackingRecord() {
        return latestTrackingRecord;
    }

    public void setLatestTrackingRecordT(TrackingRecord trackingRecord) {
        this.latestTrackingRecord = trackingRecord;
        this.trackingHistory.add(trackingRecord);
    }

    public List<TrackingRecord> getTrackingHistory() {
        return trackingHistory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-12d", trackingId));
        sb.append(String.format("%-8.2f", weight));
        sb.append(String.format("%-10.2f", cost));

        if (description.length() > 12) {
            sb.append(String.format("%-15.12s... ", description));
        } else {
            sb.append(String.format("%-15s", description));
        }

        sb.append(String.format("%-30s", destinationAddress));
        sb.append(String.format("%-40s", latestTrackingRecord));

        return sb.toString();
    }
}
