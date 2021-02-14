package com.hafizhamza.roamdatabase.Room;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private Context ctx;
    private static DatabaseClient mInstance;
    private MyDatabase myDatabase;
    public DatabaseClient(Context ctx) {
        this.ctx = ctx;
        myDatabase= Room.databaseBuilder(ctx,MyDatabase.class,"StudentDb").build();
    }
    public static synchronized DatabaseClient getInstance(Context context)
    {
        if (mInstance==null)
            mInstance=new DatabaseClient(context);
        return mInstance;
    }
    public MyDatabase getMyDatabase()
    {
        return myDatabase;
    }
}
