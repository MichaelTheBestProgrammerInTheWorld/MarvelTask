package com.michaelmagdy.marveltask.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.michaelmagdy.marveltask.R;

import java.io.ByteArrayOutputStream;

@Database(entities = Marvel.class, version = 1)
public abstract class MarvelDatabase extends RoomDatabase {

    private static MarvelDatabase instance;

    public abstract MarvelDao marvelDao();

    private static Context mContext;

    public static synchronized MarvelDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MarvelDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        mContext = context;

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //new PopulateDbAsyncTask(instance).execute();
        }
    };

        private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private MarvelDao marvelDao;

        public PopulateDbAsyncTask (MarvelDatabase db) {
            marvelDao = db.marvelDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            marvelDao.insert(new Marvel("Hulk", "this is Hulk,one of the marvels superHero",
                    convertDrawableToByteArray(R.drawable.hulk)));
            marvelDao.insert(new Marvel("Iron Man", "this is Iron Man,one of the marvels superHero",
                    convertDrawableToByteArray(R.drawable.iron_man)));
            marvelDao.insert(new Marvel("Spider Man", "this is Spider Man,one of the marvels superHero",
                    convertDrawableToByteArray(R.drawable.spider_man)));
            return null;
        }
    }

    private static byte[] convertDrawableToByteArray(int resId){

        Drawable d = mContext.getResources().getDrawable(resId); // the drawable (Captain Obvious, to the rescue!!!)
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();
        return bitmapdata;
    }
}
