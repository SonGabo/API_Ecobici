package xyz.gabrielrohez.apiecobici.data.network.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class InfoStationResponse implements Serializable {
    private List<Stations> stations;

    public List<Stations> getStations() {
        return stations;
    }

    public void setStations(List<Stations> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "InfoStationResponse{" +
                "stations=" + stations +
                '}';
    }

    public static class Location implements Serializable {
        private double lat;
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

        @Override
        public String toString() {
            return "Location{" +
                    "lat=" + lat +
                    ", lon=" + lon +
                    '}';
        }
    }

    public static class Stations implements Serializable {
        private int id;
        private String name;
        private String address;
        private String addressNumber;
        private String zipCode;
        private String districtCode;
        private String districtName;
        private String altitude;
        @Expose
        private List<NearbyStations> nearbyStations;
        private Location location;
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

        @Override
        public String toString() {
            return "Stations{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", addressNumber='" + addressNumber + '\'' +
                    ", zipCode='" + zipCode + '\'' +
                    ", districtCode='" + districtCode + '\'' +
                    ", districtName='" + districtName + '\'' +
                    ", altitude='" + altitude + '\'' +
                    ", nearbyStations=" + nearbyStations +
                    ", location=" + location +
                    ", stationType='" + stationType + '\'' +"\n\n"+
                    '}';
        }
    }

    public static class NearbyStations {

    }
}
