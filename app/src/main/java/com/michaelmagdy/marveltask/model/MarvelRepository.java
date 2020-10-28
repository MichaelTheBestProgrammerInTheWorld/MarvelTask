package com.michaelmagdy.marveltask.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MarvelRepository {

    private MarvelDao marvelDao;
    private LiveData<List<Marvel>> allMarvels;

    public MarvelRepository(Application application) {

        MarvelDatabase database = MarvelDatabase.getInstance(application);
        marvelDao = database.marvelDao();
        allMarvels = marvelDao.getAllMarvels();
    }

    public void insert(final Marvel marvel) {

        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                marvelDao.insert(marvel);
            }
        });
    }

    public void delete(final Marvel marvel) {
        
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                marvelDao.delete(marvel);
            }
        });
    }

    public LiveData<List<Marvel>> getAllMarvels() {
        return allMarvels;
    }
}
