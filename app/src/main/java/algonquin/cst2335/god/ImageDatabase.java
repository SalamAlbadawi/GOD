package algonquin.cst2335.god;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ImageEntity.class}, version = 1)
public abstract class ImageDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "image_database";

    private static ImageDatabase instance;

    public static ImageDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ImageDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract ImageDao imageDao();
}
