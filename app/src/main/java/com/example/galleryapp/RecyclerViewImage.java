package com.example.galleryapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.galleryapp.databinding.ImageItemBinding;

import java.util.ArrayList;

public class RecyclerViewImage extends  RecyclerView.Adapter<RecyclerViewImage.Holder>{

    ImageItemBinding binding;

    private ArrayList <String>list;

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageItemBinding binding= ImageItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(list.get(position));
    }



    @Override
    public int getItemCount() {
        return list==null?0: list.size();
    }

    class Holder extends RecyclerView.ViewHolder
    {
        ImageItemBinding binding;
        public Holder(ImageItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void bind(String image){
            Glide.with(binding.getRoot().getContext()).load(image).into(binding.ivImage);
        }
    }
}
