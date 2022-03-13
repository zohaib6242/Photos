package com.zohaib.photos.ui.main.view;

import static com.zohaib.photos.commons.Constants.GRID_COLUMN_COUNT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zohaib.photos.R;
import com.zohaib.photos.commons.PhotoApplication;
import com.zohaib.photos.data.model.Photo;
import com.zohaib.photos.databinding.ActivityMainBinding;
import com.zohaib.photos.ui.base.PhotoSelectListener;
import com.zohaib.photos.ui.main.adapter.PhotoAdapter;
import com.zohaib.photos.ui.main.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PhotoSelectListener {

    final String TAG = getClass().getSimpleName();
    ActivityMainBinding binding;
    PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initUI();

        if(PhotoApplication.getInstance().isNetworkAvailable()) {
            binding.progressBar.setVisibility(View.VISIBLE);

            MainActivityViewModel photoViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
            photoViewModel.getPhotos(false).observe(this, new Observer<List<Photo>>() {
                @Override
                public void onChanged(List<Photo> photoList) {
                    if (photoList != null && !photoList.isEmpty()) {
                        Log.e(TAG, "observe onChanged()=" + photoList.size());
                        binding.progressBar.setVisibility(View.GONE);
                        adapter.addPhotoList(photoList);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

        }else{
            Toast.makeText(this, "No Network Available", Toast.LENGTH_LONG).show();
        }
    }


    void initUI(){
        binding.rvPhotoList.setHasFixedSize(true);
        binding.rvPhotoList.setLayoutManager(new GridLayoutManager(this, GRID_COLUMN_COUNT));

        adapter = new PhotoAdapter();
        adapter.attachPhotoSelectListener(this);
        binding.rvPhotoList.setAdapter(adapter);
    }

    @Override
    public void onPhotoSelected(Photo photo) {
        new AlertDialog.Builder(this)
                .setTitle("Photo Detail")
                .setMessage("Are you sure you want to see detail?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent detailIntent = new Intent(MainActivity.this , DetailActivity.class);
                        detailIntent.putExtra("photo" , photo);
                        startActivity(detailIntent);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

}