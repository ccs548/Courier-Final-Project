import java.util.Date;

public class TrackingRecord {
    private PackageStatus status;
    private Date updateDate;
    private Date expectedArrivalDate;

    public TrackingRecord(PackageStatus status, Date updateDate, Date expectedArrivalDate) {
        this.status = status;
        this.updateDate = updateDate;
        this.expectedArrivalDate = expectedArrivalDate;
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
}
