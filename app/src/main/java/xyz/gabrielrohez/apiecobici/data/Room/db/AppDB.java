package xyz.gabrielrohez.apiecobici.data.Room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import xyz.gabrielrohez.apiecobici.data.Room.dao.AvailableDAO;
import xyz.gabrielrohez.apiecobici.data.Room.dao.StatusDAO;
import xyz.gabrielrohez.apiecobici.data.Room.entity.AvailabilityBikesEntity;
import xyz.gabrielrohez.apiecobici.data.Room.entity.StatusBikesEntity;
import xyz.gabrielrohez.apiecobici.utils.AppConstants;

@Database(entities = {StatusBikesEntity.class, AvailabilityBikesEntity.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    public static AppDB INSTANCE;
    public abstract StatusDAO statusDAO();
    public abstract AvailableDAO availableDAO();

    public static AppDB getAppDB(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDB.class, AppConstants.NAME_DATABASE)
                    .allowMainThreadQueries() // Allows queries in the main thread.
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
