package com.github.cesar1287.a2mobr_tmdb.feature.home.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cesar1287.a2mobr_tmdb.adapter.HomeAdapter
import com.github.cesar1287.a2mobr_tmdb.databinding.FragmentHomeBinding
import com.google.firebase.inappmessaging.ktx.inAppMessaging
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val viewModel: HomeViewModel by viewModels()

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter { movie ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(movie)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.command = MutableLiveData()
        viewModel.getNowPlayingMovies()

        registerObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding?.rvNowPlayingMovies?.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = homeAdapter
        }
    }

    private fun registerObservers() {
        viewModel.nowPlayingMoviesList.observe(viewLifecycleOwner) {
            viewModel.saveMoviesIntoFirestore(it)
            viewModel.saveMoviesIntoRoom(it)

            it?.let {
                homeAdapter.submitList(it)
            }
        }

        Firebase.inAppMessaging.addClickListener { _, action ->
            CoroutineScope(Dispatchers.Main).launch {
                findNavController().navigate(Uri.parse(action.actionUrl))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}