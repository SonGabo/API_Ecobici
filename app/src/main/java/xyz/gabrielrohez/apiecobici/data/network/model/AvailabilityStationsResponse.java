package xyz.gabrielrohez.apiecobici.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AvailabilityStationsResponse implements Parcelable {

    @SerializedName("stationsStatus")
    @Expose
    private List<Stationsstatus> stationsStatus = null;

    protected AvailabilityStationsResponse(Parcel in) {
        stationsStatus = in.createTypedArrayList(Stationsstatus.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(stationsStatus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AvailabilityStationsResponse> CREATOR = new Creator<AvailabilityStationsResponse>() {
        @Override
        public AvailabilityStationsResponse createFromParcel(Parcel in) {
            return new AvailabilityStationsResponse(in);
        }

        @Override
        public AvailabilityStationsResponse[] newArray(int size) {
            return new AvailabilityStationsResponse[size];
        }
    };

    public List<Stationsstatus> getStationsStatus() {
        return stationsStatus;
    }

    public void setStationsStatus(List<Stationsstatus> stationsStatus) {
        this.stationsStatus = stationsStatus;
    }

    @Override
    public String toString() {
        return "AvailabilityStationsResponse{" +
                "stationsStatus=" + stationsStatus +
                '}';
    }

    public static class Availability implements Parcelable{

        @SerializedName("bikes")
        @Expose
        private Integer bikes;
        @SerializedName("slots")
        @Expose
        private Integer slots;

        protected Availability(Parcel in) {
            if (in.readByte() == 0) {
                bikes = null;
            } else {
                bikes = in.readInt();
            }
            if (in.readByte() == 0) {
                slots = null;
            } else {
                slots = in.readInt();
            }
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (bikes == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(bikes);
            }
            if (slots == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(slots);
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Availability> CREATOR = new Creator<Availability>() {
            @Override
            public Availability createFromParcel(Parcel in) {
                return new Availability(in);
            }

            @Override
            public Availability[] newArray(int size) {
                return new Availability[size];
            }
        };

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

        @Override
        public String toString() {
            return "Availability{" +
                    "bikes=" + bikes +
                    ", slots=" + slots +
                    '}';
        }
    }

    public static class Stationsstatus implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("availability")
        @Expose
        private Availability availability;

        protected Stationsstatus(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            status = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(status);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Stationsstatus> CREATOR = new Creator<Stationsstatus>() {
            @Override
            public Stationsstatus createFromParcel(Parcel in) {
                return new Stationsstatus(in);
            }

            @Override
            public Stationsstatus[] newArray(int size) {
                return new Stationsstatus[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Availability getAvailability() {
            return availability;
        }

        public void setAvailability(Availability availability) {
            this.availability = availability;
        }

        @Override
        public String toString() {
            return "Stationsstatus{" +
                    "id=" + id +
                    ", status='" + status + '\'' +
                    ", availability=" + availability +
                    '}';
        }
    }
}
