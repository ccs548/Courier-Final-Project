import java.text.SimpleDateFormat;
import java.util.Date;

public class TrackingRecord {
    private PackageStatus status;
    private Date updateDate;
    private Date expectedArrivalDate;

    public TrackingRecord(PackageStatus status, Date updateDate, Date expectedArrivalDate) {
        this.status = status;
        this.expectedArrivalDate = expectedArrivalDate;
        this.updateDate = updateDate;
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

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-12s", status));
        sb.append(String.format("%-20s", dateFormat.format(expectedArrivalDate)));
        sb.append(String.format("%-15s", dateFormat.format(updateDate)));

        return sb.toString();
    }
}
