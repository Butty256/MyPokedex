package com.example.mypokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PokeAdapter(createDataList())

        pokeRecyclerView.setHasFixedSize(true)
        pokeRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        pokeRecyclerView.adapter = adapter
    }

    private fun createDataList(): List<PokeImage> {
        val dataList = mutableListOf<PokeImage>()
        for (i in 0..49) {
            val data: PokeImage = PokeImage().also {
                it.name = "タイトル"
                it.id = i
                it.url = "aaaaa"
            }
            dataList.add(data)
        }
        return dataList
    }
}