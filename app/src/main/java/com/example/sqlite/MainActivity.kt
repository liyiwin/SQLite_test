package com.example.sqlite

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val ct = this

    val list = mutableListOf<Model>()

    val this_adapter = Adapter(list,ct)

    private lateinit var dbrw : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setAction()

       // db_insert()

        setUpList(list)

        setupRecyclerView()

        this_adapter.update(list)



      //  db_delete()

      //  db_update()




    }







    fun setUpList(list: MutableList<Model>){


        val c = dbrw.rawQuery(" SELECT * FROM myTable ",null)

        c.moveToFirst()


        for(i in 0 until c.count){

            list.add(Model("${c.getString(0)}","${c.getString(1)}"))

            c.moveToNext()

        }

        c.close()



    }




    fun setAction(){

        dbrw = MyDBHelper(this).writableDatabase


        imageView4.setOnClickListener {

           val intent = Intent(ct,EditActivity::class.java)

           startActivity(intent)

        }


        imageView3.setOnClickListener {

            val intent = Intent(ct,SearchActivity::class.java)

            startActivity(intent)


        }



    }


    fun  setupRecyclerView(){

        recyclerView.layoutManager =LinearLayoutManager(ct)

        recyclerView.adapter = this_adapter

    }



    fun db_update(){


       dbrw.execSQL("UPDATE myTable SET mean = '鳳梨' WHERE vocabulary = 'Apple'")


    }



    fun db_insert(mean:String){


        val  values =  ContentValues();
        values.put("vocabulary", "Apple");
        values.put("mean", "mean");
//        values.put("vocabulary", "PineApple");
//        values.put("mean", "鳳梨");

        dbrw.insert("myTable", null, values);

    }



}
