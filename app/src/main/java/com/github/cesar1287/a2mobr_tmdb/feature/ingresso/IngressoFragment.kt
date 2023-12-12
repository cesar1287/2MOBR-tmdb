package com.github.cesar1287.a2mobr_tmdb.feature.ingresso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.cesar1287.a2mobr_tmdb.databinding.FragmentIngressoBinding

class IngressoFragment : Fragment() {

    private val args: IngressoFragmentArgs by navArgs()
    private val movieId: String by lazy {
        args.movieId
    }

    private val binding: FragmentIngressoBinding by lazy {
        FragmentIngressoBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.apply {
            settings.javaScriptEnabled = true
            loadUrl("https://www.ingresso.com/filme/$movieId")
        }
    }
}