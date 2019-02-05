package xyz.gabrielrohez.apiecobici;

import java.util.List;

public class AvailabilityStationsResponse {
    private List<StationsStatus> stationsStatus;

    public List<StationsStatus> getStationsStatus() {
        return stationsStatus;
    }

    public void setStationsStatus(List<StationsStatus> stationsStatus) {
        this.stationsStatus = stationsStatus;
    }

    public static class Availability {
        private int bikes;
        private int slots;

        public int getBikes() {
            return bikes;
        }

        public void setBikes(int bikes) {
            this.bikes = bikes;
        }

        public int getSlots() {
            return slots;
        }

        public void setSlots(int slots) {
            this.slots = slots;
        }
    }

    public static class StationsStatus {
        private int id;
        private String status;
        private Availability availability;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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
    }
}
