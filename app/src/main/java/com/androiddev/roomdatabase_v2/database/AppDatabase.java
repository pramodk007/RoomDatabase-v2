package com.androiddev.roomdatabase_v2.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.androiddev.roomdatabase_v2.model.Person;

@Database(entities={Person.class},version = 1,exportSchema = false)
public  abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "person_database";

    //singleton for AppdataBase
    private static AppDatabase sInstance;
    //for interface PersonDao
    public abstract PersonDao personDao();

    public static AppDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG,"CREATING NEW DATABASE INSTANCE ");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME)
                            .build();
            }
        }
        Log.d(LOG_TAG,"GETTING THE DATABASE INSTANCE");
        return sInstance;
    }
}
