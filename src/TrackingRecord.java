import java.text.SimpleDateFormat;
import java.util.Date;

public class TrackingRecord {
    private PackageStatus status;
    private Date updateDate;
    private Date expectedArrivalDate;
    private String actionType;
    private String description;

    public TrackingRecord(PackageStatus status, Date updateDate, Date expectedArrivalDate, String actionType, String description) {
        this.status = status;
        this.expectedArrivalDate = expectedArrivalDate;
        this.updateDate = updateDate;
        this.actionType = actionType;
        this.description = description;
    }

    public PackageStatus getStatus() {
        return status;
    }

    public void setStatus(PackageStatus status) {
        this.status = status;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getExpectedArrivalDate() {
        return expectedArrivalDate;
    }

    public void setExpectedArrivalDate(Date expectedArrivalDate) {
        this.expectedArrivalDate = expectedArrivalDate;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-12s", actionType));                 // Action Type
        sb.append(String.format("%-12s", status));                     // Status
        sb.append(String.format("%-15s", (updateDate != null) ? dateFormat.format(updateDate) : "N/A"));  // Update Date
        sb.append(String.format("%-20s", (expectedArrivalDate != null) ? dateFormat.format(expectedArrivalDate) : "N/A"));  // Expected Arrival Date
        sb.append(String.format("%-30s", description));                // Description

        return sb.toString();
    }
}
