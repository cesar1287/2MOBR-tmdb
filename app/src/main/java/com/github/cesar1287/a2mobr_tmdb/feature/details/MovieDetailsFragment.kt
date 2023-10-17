package com.github.cesar1287.a2mobr_tmdb.feature.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.cesar1287.a2mobr_tmdb.R
import com.github.cesar1287.a2mobr_tmdb.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private var binding: FragmentMovieDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}