package com.zohaib.photos.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zohaib.photos.R;
import com.zohaib.photos.data.model.Photo;
import com.zohaib.photos.databinding.ItemPhotoBinding;
import com.zohaib.photos.ui.base.PhotoSelectListener;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    private List<Photo> photoList;
    private PhotoSelectListener photoSelectListener;

    public PhotoAdapter() {
        photoList = new ArrayList<>();
    }

    public void attachPhotoSelectListener(PhotoSelectListener photoSelectListener){
        this.photoSelectListener = photoSelectListener;
    }

    public void addPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @Override
    public int getItemCount() {
        return photoList != null ? photoList.size() : 0;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         ItemPhotoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_photo, parent, false);

        return new MyViewHolder(binding);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemPhotoBinding binding;

        MyViewHolder(ItemPhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext())
                .load(photoList.get(holder.getAdapterPosition()).previewURL)
                .centerCrop()
                .placeholder(R.drawable.loader)
                .into(holder.binding.photo);
        holder.binding.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (photoSelectListener != null){
                    photoSelectListener.onPhotoSelected(photoList.get(holder.getAdapterPosition()));
                }
            }
        });
    }
}
