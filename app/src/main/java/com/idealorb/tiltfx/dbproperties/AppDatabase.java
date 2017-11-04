package com.idealorb.tiltfx.dbproperties;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Ismail on 10/23/2017.
 */
@Database(entities = Currency.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static  AppDatabase INSTANCE;

    public abstract CurrencyDao currencyDao();

    public static  AppDatabase getDatabase(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "currencydb.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance()
    {
        INSTANCE = null;
    }
}
