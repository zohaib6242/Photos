package com.zohaib.photos.commons;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoApplication extends Application {

    final String TAG = getClass().getSimpleName();
    private static PhotoApplication mInstance;
    private static Retrofit retrofit = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized PhotoApplication getInstance() {
        return mInstance;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Retrofit getRetrofitClient() {

        if (retrofit == null) {
            okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder().build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build();
        }
        return retrofit;
    }

}
