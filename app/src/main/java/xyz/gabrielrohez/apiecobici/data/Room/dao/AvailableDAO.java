package xyz.gabrielrohez.apiecobici.data.Room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.Room.entity.AvailabilityBikesEntity;

@Dao
public interface AvailableDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AvailabilityBikesEntity availabilityBikesEntity);

    @Query("SELECT * FROM availability_bike")
    List<AvailabilityBikesEntity> getAllAvailable();

    @Update
    void update(AvailabilityBikesEntity availabilityBikesEntity);

}
