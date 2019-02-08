package xyz.gabrielrohez.apiecobici.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class InfoStationResponse implements Serializable {

    @SerializedName("stations")
    private List<Stations> stations;

    public List<Stations> getStations() {
        return stations;
    }

    public void setStations(List<Stations> stations) {
        this.stations = stations;
    }

    public static class Location {
        @SerializedName("lat")
        private double lat;
        @SerializedName("lon")
        private double lon;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }

    public static class Stations {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("address")
        private String address;
        @SerializedName("addressNumber")
        private String addressNumber;
        @SerializedName("zipCode")
        private String zipCode;
        @SerializedName("districtCode")
        private String districtCode;
        @SerializedName("districtName")
        private String districtName;
        @SerializedName("altitude")
        private String altitude;
        @SerializedName("nearbyStations")
        @Expose
        private List<NearbyStations> nearbyStations;
        @SerializedName("location")
        private Location location;
        @SerializedName("stationType")
        private String stationType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public String getAltitude() {
            return altitude;
        }

        public void setAltitude(String altitude) {
            this.altitude = altitude;
        }

        public List<NearbyStations> getNearbyStations() {
            return nearbyStations;
        }

        public void setNearbyStations(List<NearbyStations> nearbyStations) {
            this.nearbyStations = nearbyStations;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public String getStationType() {
            return stationType;
        }

        public void setStationType(String stationType) {
            this.stationType = stationType;
        }
    }

    public static class NearbyStations{

    }
}
