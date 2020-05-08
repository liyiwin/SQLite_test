package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    val ct = this

    val list = mutableListOf<Model>()

    val ed_adapter = EditAdapter(list,ct)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        setuprecyclerview()

    }

    fun setuprecyclerview() {

        ed_recyclerview.layoutManager=LinearLayoutManager(ct)

        ed_recyclerview.adapter=ed_adapter


    }


}
