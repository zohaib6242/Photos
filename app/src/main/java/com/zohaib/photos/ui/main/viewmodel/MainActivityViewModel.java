package com.zohaib.photos.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zohaib.photos.data.model.Photo;
import com.zohaib.photos.data.repository.PhotoRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private PhotoRepository photoRepository;
    private MutableLiveData<List<Photo>> photos;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        photoRepository = new PhotoRepository();
    }

    public LiveData<List<Photo>> getPhotos(boolean isLocalSource) {
       return photoRepository.requestPhotos(isLocalSource);
    }
}
