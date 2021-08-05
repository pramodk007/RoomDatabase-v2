package com.androiddev.roomdatabase_v2.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.androiddev.roomdatabase_v2.model.Person;

import java.util.List;

@Dao
public interface PersonDao {

    @Query("select * from person_table")
    List<Person> loadAllPerson();

    @Insert
    void insertPerson( Person person);

    @Update
    void updatePerson( Person person);

    @Delete
    void deletePerson( Person person);

    @Query("select * from person_table where id = :id")
    Person loadPersonById( int id);

}
