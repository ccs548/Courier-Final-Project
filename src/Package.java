import java.util.*;

public class Package {
    private String trackingId;
    private double weight;
    private String description;
    private String destinationAddress;
    private TrackingRecord latestTrackingRecord;
    private List<TrackingRecord> trackingHistory;

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
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

    public void setTrackingRecord(TrackingRecord trackingRecord) {
        // when we add a tracking record, it will automatically be added to the trackingHistory
        this.latestTrackingRecord = trackingRecord;
        this.trackingHistory.add(trackingRecord);
    }

    public List<TrackingRecord> getTrackingHistory() {
        return trackingHistory;
    }
}
