package xyz.gabrielrohez.apiecobici.data.network.model;

public class StationsModel {

    private Integer id;
    private String name;
    private String address;
    private String addressNumber;
    private String zipCode;
    private String districtCode;
    private String districtName;
    private Double lat;
    private Double lon;
    private String stationType;
    private Integer bikes;
    private Integer slots;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public Integer getBikes() {
        return bikes;
    }

    public void setBikes(Integer bikes) {
        this.bikes = bikes;
    }

    public Integer getSlots() {
        return slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StationsModel{" +
                "id=" + id + "\n"+
                ", name='" + name + '\'' +"\n"+
                ", address='" + address + '\'' +"\n"+
                ", addressNumber='" + addressNumber + '\'' +"\n"+
                ", zipCode='" + zipCode + '\'' +"\n"+
                ", districtCode='" + districtCode + '\'' +"\n"+
                ", districtName='" + districtName + '\'' +"\n"+
                ", lat=" + lat +"\n"+
                ", lon=" + lon +"\n"+
                ", stationType='" + stationType + '\'' +"\n"+
                ", bikes=" + bikes +"\n"+
                ", slots=" + slots +"\n"+
                ", status='" + status + '\'' +"\n"+
                '}';
    }
}
