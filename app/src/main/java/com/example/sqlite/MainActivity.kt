package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val ct = this

    val list = mutableListOf<Model>()

    val this_adapter = Adapter(list,ct)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setuplist(list)

        setupRecyclerView()

        this_adapter.update(list)


    }

    fun setuplist(list:MutableList<Model>){



    }

    fun  setupRecyclerView(){

        recyclerView.layoutManager =LinearLayoutManager(ct)

        recyclerView.adapter = this_adapter

    }



}
