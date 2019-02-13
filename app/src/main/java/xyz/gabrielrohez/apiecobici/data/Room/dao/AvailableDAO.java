package xyz.gabrielrohez.apiecobici.data.Room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.Room.entity.AvailabilityBikesEntity;
import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.StationsModel;

@Dao
public interface AvailableDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AvailabilityBikesEntity availabilityBikesEntity);

    @Query("SELECT * FROM availability_bike")
    List<AvailabilityBikesEntity> getAllAvailable();

    @Query("SELECT * FROM availability_bike WHERE id_available=:id")
    AvailabilityBikesEntity getStatusStation(int id);

    @Update
    void update(AvailabilityBikesEntity availabilityBikesEntity);

    @Query("SELECT status_bike.id as id, status_bike.name as name, status_bike.address as address, status_bike.addressNumber as addressNumber, status_bike.zipCode as zipCode, status_bike.districtCode as districtCode, status_bike.districtName as districtName," +
            "status_bike.lat as lat, status_bike.lon as lon, status_bike.stationType as stationType, availability_bike.bikes as bikes, availability_bike.slots as slots, availability_bike.status as status FROM availability_bike INNER JOIN status_bike on availability_bike.id_available = status_bike.available_id WHERE availability_bike.id_available=:id")
    StationsModel getStation(int id);
    /*
    @Query("SELECT professor.id as idProfessor, professor.name as nameProfessor, professor.email as emailProfessor, course.id as idCourse, course.name as nameCourse, course.duration as durationCourse FROM course INNER JOIN professor ON course.professorId=professor.id WHERE course.professorId=:id")
    List<ProfessorAndCourse> getCourse(int id);
     */
}
