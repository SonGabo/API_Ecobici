package xyz.gabrielrohez.apiecobici.data.Room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import xyz.gabrielrohez.apiecobici.utils.AppConstants;

@Entity(tableName = AppConstants.BIKE_AVAILABILITY)
public class AvailabilityBikesEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_available")
    private int id_available;
    @ColumnInfo(name = "bikes")
    private Integer bikes;
    @ColumnInfo(name = "slots")
    private Integer slots;
    @ColumnInfo(name = "status")
    private String status;

    public int getId_available() {
        return id_available;
    }

    public void setId_available(int id_available) {
        this.id_available = id_available;
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
        return "AvailabilityBikesEntity{" +
                "id_available=" + id_available +"\n"+
                ", bikes=" + bikes +"\n"+
                ", slots=" + slots +"\n"+
                ", status='" + status + '\'' +
                '}';
    }
}
