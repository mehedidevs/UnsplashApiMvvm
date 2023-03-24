package com.example.unsplashapimvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.unsplashapimvvm.R
import com.example.unsplashapimvvm.databinding.PhotoItemLayoutBinding
import com.example.unsplashapimvvm.model.PhotoModel


class PhotosAdapter(val onPhotoSelected: (photo: PhotoModel, position: Int) -> Unit) :
    ListAdapter<PhotoModel, PhotosAdapter.PhotoViewHolder>(comparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = PhotoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        getItem(position).let {
            holder.bind(it, position)
        }


    }


    inner class PhotoViewHolder(private val itemBinding: PhotoItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(photoModel: PhotoModel, position: Int) {
            itemBinding.apply {
                imgPhoto.load(photoModel.urls.thumb) {
                    placeholder(R.color.purple_200)
                    crossfade(true)
                }

                cardPhoto.setOnClickListener {
                    onPhotoSelected(photoModel, position)
                }
            }
        }
    }


    companion object {

        val comparator = object : DiffUtil.ItemCallback<PhotoModel>() {
            override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {

                return oldItem == newItem
            }

        }


    }

}
