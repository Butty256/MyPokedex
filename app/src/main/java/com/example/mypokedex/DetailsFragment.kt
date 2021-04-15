package com.example.mypokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mypokedex.databinding.FragmentDetailsBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class DetailsFragment: Fragment() {

    val detailsViewModel = DetailsViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: FragmentDetailsBinding = DataBindingUtil.bind(view)?: throw RuntimeException("FragmentDetailsBinding not done.")
        binding.viewModel = detailsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val args: DetailsFragmentArgs by navArgs()
        detailsViewModel.setData(args.entry)

        val iconView: ImageView = view.findViewById(R.id.icon_detail_image)

        GlideApp.with(iconView)
            .load(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
                        args.entry.toString() + ".png"
            )
            .into(iconView)
    }
}
