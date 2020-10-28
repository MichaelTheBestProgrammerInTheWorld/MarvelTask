package com.michaelmagdy.marveltask.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.michaelmagdy.marveltask.model.Marvel;
import com.michaelmagdy.marveltask.model.MarvelRepository;

import java.util.List;

public class MarvelViewModel extends AndroidViewModel {

    private MarvelRepository repository;
    private LiveData<List<Marvel>> allMarvels;


    public MarvelViewModel(@NonNull Application application) {
        super(application);

        repository = new MarvelRepository(application);
        allMarvels = repository.getAllMarvels();
    }

    public void insert(Marvel marvel) {
        repository.insert(marvel);
    }

    public void delete(Marvel marvel) {
        repository.delete(marvel);
    }

    public LiveData<List<Marvel>> getAllMarvels() {
        return allMarvels;
    }
}
