package xyz.gabrielrohez.apiecobici.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class InfoStationResponse implements Parcelable {

    @SerializedName("stations")
    @Expose
    private List<Station> stations = null;

    protected InfoStationResponse(Parcel in) {
        stations = in.createTypedArrayList(Station.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(stations);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InfoStationResponse> CREATOR = new Creator<InfoStationResponse>() {
        @Override
        public InfoStationResponse createFromParcel(Parcel in) {
            return new InfoStationResponse(in);
        }

        @Override
        public InfoStationResponse[] newArray(int size) {
            return new InfoStationResponse[size];
        }
    };

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

    public class Location implements Parcelable {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lon")
        @Expose
        private Double lon;

        protected Location(Parcel in) {
            if (in.readByte() == 0) {
                lat = null;
            } else {
                lat = in.readDouble();
            }
            if (in.readByte() == 0) {
                lon = null;
            } else {
                lon = in.readDouble();
            }
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (lat == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeDouble(lat);
            }
            if (lon == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeDouble(lon);
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public final Creator<Location> CREATOR = new Creator<Location>() {
            @Override
            public Location createFromParcel(Parcel in) {
                return new Location(in);
            }

            @Override
            public Location[] newArray(int size) {
                return new Location[size];
            }
        };

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

    public static class Station implements Parcelable {

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

        protected Station(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            name = in.readString();
            address = in.readString();
            addressNumber = in.readString();
            zipCode = in.readString();
            districtCode = in.readString();
            districtName = in.readString();
            stationType = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(name);
            dest.writeString(address);
            dest.writeString(addressNumber);
            dest.writeString(zipCode);
            dest.writeString(districtCode);
            dest.writeString(districtName);
            dest.writeString(stationType);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Station> CREATOR = new Creator<Station>() {
            @Override
            public Station createFromParcel(Parcel in) {
                return new Station(in);
            }

            @Override
            public Station[] newArray(int size) {
                return new Station[size];
            }
        };

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
