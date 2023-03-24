package com.example.unsplashapimvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.unsplashapimvvm.adapters.PhotosAdapter
import com.example.unsplashapimvvm.databinding.FragmentHomeBinding
import com.example.unsplashapimvvm.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var adapter: PhotosAdapter

    private val viewModel by viewModels<HomeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        viewModel.fetchPhotos(1)
        adapter = PhotosAdapter { photo, _ ->

            Log.i("TAG", "onCreateView: ${photo.toString()}")

        }

        binding.imagesRcv.adapter = adapter




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.photoList.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Error -> {
                    Log.i("TAG", "error : ${it.message} : ")

                }
                is DataState.Loading -> {
                    Log.i("TAG", "Data : : ${it.message} : ")
                }
                is DataState.Success -> {

                    Log.i("TAG", "data: ${it.data}")

                    adapter.submitList(it.data)


                }
            }


        }


    }


}