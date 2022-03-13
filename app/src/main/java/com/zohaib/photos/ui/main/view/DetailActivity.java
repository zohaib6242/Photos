package com.zohaib.photos.ui.main.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.zohaib.photos.R;
import com.zohaib.photos.data.model.Photo;
import com.zohaib.photos.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        Photo photo = (Photo) getIntent().getSerializableExtra("photo");
        Glide.with(this)
                .load(photo.largeImageURL)
                .centerCrop()
                .placeholder(R.drawable.loader)
                .into(binding.image);

    }
}