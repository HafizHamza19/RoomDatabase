package com.hafizhamza.roamdatabase.Room;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class},version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
public abstract DAO dao();
}
