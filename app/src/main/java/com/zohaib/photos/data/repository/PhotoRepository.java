package com.zohaib.photos.data.repository;

import static com.zohaib.photos.commons.Constants.API_KEY;
import static com.zohaib.photos.commons.Constants.ITEM_LOAD_LIMIT;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.zohaib.photos.commons.PhotoApplication;
import com.zohaib.photos.data.api.ApiInterface;
import com.zohaib.photos.data.model.Photo;
import com.zohaib.photos.data.model.PixabayResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepository {

    private final String TAG = getClass().getSimpleName();

    public MutableLiveData<List<Photo>> requestPhotos(boolean isLocal) {
        if (isLocal){
            return requestLocalPhotos();
        }
        else {
            return requestRemotePhotos();
        }
    }

    private MutableLiveData<List<Photo>> requestRemotePhotos() {
        final MutableLiveData<List<Photo>> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiService =
                PhotoApplication.getRetrofitClient().create(ApiInterface.class);

        apiService.getPhotos(ITEM_LOAD_LIMIT , API_KEY).enqueue(new Callback<PixabayResponse>() {
            @Override
            public void onResponse(Call<PixabayResponse> call, Response<PixabayResponse> response) {
                Log.e(TAG, "getPhotos response="+response );

                if (response.isSuccessful() && response.body()!=null && response.body().items != null && response.body().items.size() > 0) {
                    Log.e(TAG, "getPhotos response.size="+response.body().items.size() );
                    mutableLiveData.setValue(response.body().items);
                }
            }

            @Override
            public void onFailure(Call<PixabayResponse> call, Throwable t) {
                Log.e(TAG, "getPhotos onFailure" + call.toString());
            }
        });

        return mutableLiveData;
    }

    private MutableLiveData<List<Photo>> requestLocalPhotos() {
        final MutableLiveData<List<Photo>> mutableLiveData = new MutableLiveData<>();
        //local data implementation
        return mutableLiveData;
    }

}
