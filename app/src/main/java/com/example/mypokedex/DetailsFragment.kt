package com.example.mypokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class DetailsFragment: Fragment() {
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
        val info: PokeInfo = getData(args.entry)

        val idView: TextView = view.findViewById(R.id.id_detail_text)
        val nameView: TextView = view.findViewById(R.id.name_detail_text)
        val flavorView: TextView = view.findViewById(R.id.flavor_detail_text)
        val iconView: ImageView = view.findViewById(R.id.icon_detail_image)
        idView.text = info.id.toString()
        nameView.text = info.name.capitalize()

        // どうにかしたい
        val flavorSize: Int = info.flavor_text_entries.size
        for (i in 0..flavorSize) {
            if (info.flavor_text_entries[i].language == Language("en") &&
                info.flavor_text_entries[i].version == Version("red")) {
                flavorView.text = info.flavor_text_entries[i].flavor_text
                break
            }
        }

        GlideApp.with(iconView)
            .load(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +
                        info.id.toString() + ".png"
            )
            .into(iconView)
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