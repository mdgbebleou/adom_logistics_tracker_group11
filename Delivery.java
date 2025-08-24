package model;

public class Delivery {
    private String packageId;
    private String origin;
    private String destination;
    private String vehicleRegNum;
    private String driverId;
    private String eta;
    private String status;

    public Delivery(String packageId, String origin, String destination, String vehicleRegNum, String driverId, String eta) {
        this.packageId = packageId;
        this.origin = origin;
        this.destination = destination;
        this.vehicleRegNum = vehicleRegNum;
        this.driverId = driverId;
        this.eta = eta;
        this.status = "Pending";
    }

    public String getPackageId() { return packageId; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getVehicleRegNum() { return vehicleRegNum; }
    public String getDriverId() { return driverId; }
    public String getEta() { return eta; }
    public String getStatus() { return status; }

    public void setDriverId(String driverId) { this.driverId = driverId; }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public void updateRoute(String newOrigin, String newDestination, String newEta) {
        this.origin = newOrigin;
        this.destination = newDestination;
        this.eta = newEta;
    }

    @Override
    public String toString() {
        return packageId + "," + origin + "," + destination + "," + vehicleRegNum + "," + driverId + "," + eta + "," + status + "\n";
    }
}