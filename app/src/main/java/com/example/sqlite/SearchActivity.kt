package com.example.sqlite

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    val ct = this

    val list = mutableListOf<Model>()

    val this_adapter = SearchAdapter(list,ct)

    private lateinit var dbrw : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setAction()

        setupRecyclerView()
    }

    fun  setupRecyclerView(){

        search_recyclerview.layoutManager = LinearLayoutManager(ct)

        search_recyclerview.adapter = this_adapter

    }


    fun setAction(){

        dbrw = MyDBHelper(this).writableDatabase

        search_btn.setOnClickListener {

            val result = search_et.text.toString()

            search(result)

        }



    }




    fun search(result:String){

        val c = dbrw.rawQuery(" SELECT * FROM myTable WHERE vocabulary LIKE '$result'",null)

         c.moveToFirst()

          list.clear()


        for(i in 0 until c.count){

            list.add(Model("${c.getString(0)}","${c.getString(1)}"))

            c.moveToNext()

        }

        this_adapter.update(list)

        c.close()


    }
}
