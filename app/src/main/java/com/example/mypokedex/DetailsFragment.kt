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

        val args: DetailsFragmentArgs by navArgs()

        val binding: FragmentDetailsBinding = DataBindingUtil.bind(view)?: throw RuntimeException("FragmentDetailsBinding not done.")
        binding.viewModel = detailsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val iconView: ImageView = view.findViewById(R.id.icon_detail_image)

        GlideApp.with(iconView)
            .load(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
                        args.entry.toString() + ".png"
            )
            .into(iconView)
    }

    override fun onStart() {
        super.onStart()

        //val data: PokeInfo = getData(args.entry)
        //println(data.name)
        //detailsViewModel.setData2(data)
        detailsViewModel.setData(5)
    }

    private fun getData(id: Int): PokeInfo {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var data: PokeInfo = PokeInfo()
        thread {
            try {
                val service: PokeInfoService = retrofit.create(PokeInfoService::class.java)
                data = service.pokeInfo(id).execute().body() ?: throw IllegalStateException("NULL")
            } catch (e: Exception) {
                Log.d("api", "debug $e")
            }
        }
        // 一時的に止まる
        // 本当はスレッドが終わるまで待ちたい
        Thread.sleep(1000)
        return data
    }

}
