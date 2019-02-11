package xyz.gabrielrohez.apiecobici.data.Room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import xyz.gabrielrohez.apiecobici.utils.AppConstants;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = AppConstants.BIKE_STATUS,
        foreignKeys = @ForeignKey(entity = AvailabilityBikesEntity.class,
        parentColumns = "id_available",
        childColumns = "available_id",
        onDelete = CASCADE))
public class StatusBikesEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "addressNumber")
    private String addressNumber;
    @ColumnInfo(name = "zipCode")
    private String zipCode;
    @ColumnInfo(name = "districtCode")
    private String districtCode;
    @ColumnInfo(name = "districtName")
    private String districtName;
    @ColumnInfo(name = "lat")
    private Double lat;
    @ColumnInfo(name = "lon")
    private Double lon;
    @ColumnInfo(name = "stationType")
    private String stationType;
    @ColumnInfo(name = "available_id")
    private int available_id;

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

    public int getAvailable_id() {
        return available_id;
    }

    public void setAvailable_id(int available_id) {
        this.available_id = available_id;
    }

    @Override
    public String toString() {
        return "StatusBikesEntity{" +
                "id=" + id +"\n"+
                ", name='" + name + '\'' +"\n"+
                ", address='" + address + '\'' +"\n"+
                ", addressNumber='" + addressNumber + '\'' +"\n"+
                ", zipCode='" + zipCode + '\'' +"\n"+
                ", districtCode='" + districtCode + '\'' +"\n"+
                ", districtName='" + districtName + '\'' +"\n"+
                ", lat=" + lat +"\n"+
                ", lon=" + lon +"\n"+
                ", stationType='" + stationType + '\'' +"\n"+
                ", available_id=" + available_id +
                '}';
    }
}
