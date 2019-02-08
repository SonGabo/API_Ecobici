package xyz.gabrielrohez.apiecobici.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class InfoStationResponse implements Serializable {

    @SerializedName("stations")
    @Expose
    private List<Station> stations = null;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "InfoStationResponse{" +
                "stations=" + stations +
                '}';
    }

    public class Location {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lon")
        @Expose
        private Double lon;

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

        @Override
        public String toString() {
            return "Location{" +
                    "lat=" + lat +
                    ", lon=" + lon +
                    '}';
        }
    }

    public class Station {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("addressNumber")
        @Expose
        private String addressNumber;
        @SerializedName("zipCode")
        @Expose
        private String zipCode;
        @SerializedName("districtCode")
        @Expose
        private String districtCode;
        @SerializedName("districtName")
        @Expose
        private String districtName;
        @SerializedName("altitude")
        @Expose
        private Object altitude;
        @SerializedName("nearbyStations")
        @Expose
        private List<Integer> nearbyStations = null;
        @SerializedName("location")
        @Expose
        private Location location;
        @SerializedName("stationType")
        @Expose
        private String stationType;

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

        public Object getAltitude() {
            return altitude;
        }

        public void setAltitude(Object altitude) {
            this.altitude = altitude;
        }

        public List<Integer> getNearbyStations() {
            return nearbyStations;
        }

        public void setNearbyStations(List<Integer> nearbyStations) {
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

        @Override
        public String toString() {
            return "Station{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", addressNumber='" + addressNumber + '\'' +
                    ", zipCode='" + zipCode + '\'' +
                    ", districtCode='" + districtCode + '\'' +
                    ", districtName='" + districtName + '\'' +
                    ", altitude=" + altitude +
                    ", nearbyStations=" + nearbyStations +
                    ", location=" + location +
                    ", stationType='" + stationType + '\'' +
                    '}';
        }
    }
}
