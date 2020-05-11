package com.example.sqlite

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    val ct = this

    val list = mutableListOf<edit_Model>()

    val ed_adapter = EditAdapter(list,ct)

    private lateinit var dbrw : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        setAction()

        setUpList(list)

        setuprecyclerview()

        ed_adapter.update(list)

    }




    fun setuprecyclerview() {

        ed_recyclerview.layoutManager=LinearLayoutManager(ct)

        ed_recyclerview.adapter=ed_adapter


    }

    fun setAction(){


        dbrw = MyDBHelper(this).writableDatabase


        back_btn.setOnClickListener {


            val mBuilder = AlertDialog.Builder(this)
                .setTitle("資料尚未儲存")
                .setMessage("確定要離開了嗎？")
                .setNeutralButton("取消"){dialog, which -> /*要執行的程式*/ }
                .setNegativeButton("拒絕"){dialog, which -> /*要執行的程式*/}
                .setPositiveButton("確定") {dialog, which -> intent(MainActivity()) }
                .show()

        }


        finished_btn.setOnClickListener {


          if (!filterdataempty(ed_adapter.innerlist))
            {

                dbrw.delete("myTable", null, null)

              //  for (i in ed_adapter.innerlist.indices){ Log.d("aaaa",i.toString()+"\n"+"count"+ed_adapter.innerlist[i].vocabulary)}

                db_insert(ed_adapter.innerlist)



           Toast("修改成功",Toast.LENGTH_LONG)

                intent(MainActivity())

            }

            else Toast("尚未輸入完成",Toast.LENGTH_LONG)

        }

        ed_adapter.deleteImp = object :EditAdapter.Delete{
            override fun delete(vocabulary: String) {

                Log.d("aaaa",vocabulary)

              //  dbrw.delete("myTable","vocabulary like ?", arrayOf(vocabulary))

                dbrw.execSQL("delete from myTable where vocabulary = '$vocabulary'");




            }


        }

    }

    fun filterdataempty(datalist:MutableList<edit_Model>):Boolean{

        val filterdata  = datalist.filter { it.meaning == "" ||  it.vocabulary == "" }

        if (filterdata.size != 0) return true

        else return false

    }

    fun db_insert(datalist:MutableList<edit_Model>){

        for( i in datalist.indices){

            val  values =  ContentValues()

            val vocabulary =  datalist[i].vocabulary

            Log.d("aaaa", vocabulary)

            val mean = datalist[i].meaning

            Log.d("aaaa",mean)


            values.put("vocabulary", vocabulary)
            values.put("mean", mean)

            dbrw.insert("myTable", null, values)

        }

    }



    fun setUpList(list: MutableList<edit_Model>){


        val c = dbrw.rawQuery(" SELECT * FROM myTable ",null)

        c.moveToFirst()


        for(i in 0 until c.count){

            list.add(edit_Model("${c.getString(0)}","${c.getString(1)}",false))

            c.moveToNext()

        }

        c.close()


    }

    fun intent(acticity:Activity){

        val intent = Intent(ct,acticity::class.java)

         startActivity(intent)


    }

    fun Toast(message:String,length:Int){

        Toast.makeText(ct,message,length).show()

    }





}
