package xyz.gabrielrohez.apiecobici.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AvailabilityStationsResponse implements Serializable {

    @SerializedName("stationsStatus")
    @Expose
    private List<Stationsstatus> stationsStatus = null;

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

    public class Availability {

        @SerializedName("bikes")
        @Expose
        private Integer bikes;
        @SerializedName("slots")
        @Expose
        private Integer slots;

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

    public class Stationsstatus {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("availability")
        @Expose
        private Availability availability;

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
