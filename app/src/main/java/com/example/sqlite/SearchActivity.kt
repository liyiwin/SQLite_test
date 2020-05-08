package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    val ct = this

    val list = mutableListOf<Model>()

    val this_adapter = SearchAdapter(list,ct)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setupRecyclerView()
    }

    fun  setupRecyclerView(){

        search_recyclerview.layoutManager = LinearLayoutManager(ct)

        search_recyclerview.adapter = this_adapter

    }
}
