package xyz.gabrielrohez.apiecobici.data.Room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.Room.entity.StatusBikesEntity;
import xyz.gabrielrohez.apiecobici.data.network.model.StationsModel;

@Dao
public interface StatusDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(StatusBikesEntity statusBikesEntity);

    @Query("SELECT * FROM status_bike")
    List<StatusBikesEntity> getAllBikes();

    @Update
    void update(StatusBikesEntity statusBikesEntity);

}
