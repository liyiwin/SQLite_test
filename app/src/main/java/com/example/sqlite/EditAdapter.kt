package com.example.sqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EditAdapter (var outerlist :MutableList<Model>,var ct : Context) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

     var innerlist = mutableListOf<Model>()

    companion object{

        val basic_type:Int = 0
        val add_type:Int = 1

    }

    init {

        innerlist = outerlist

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(viewType){

            basic_type  -> {

                val view = LayoutInflater.from(ct).inflate(R.layout.itemview_edit,parent,false)

                return ViewHodler_Basic(view)
            }

            else  -> {

                val view = LayoutInflater.from(ct).inflate(R.layout.itemview_add,parent,false)

                return ViewHolder_Add(view)
            }


        }

    }

    override fun getItemViewType(position: Int): Int {


        return when(position){

            innerlist.size -> add_type

            else -> basic_type


        }


    }

    override fun getItemCount(): Int = innerlist.size+1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    inner class ViewHodler_Basic (view: View):RecyclerView.ViewHolder(view){


    }


    inner class ViewHolder_Add(view: View):RecyclerView.ViewHolder(view){


    }

    fun update(list:MutableList<Model>){

        innerlist = list

        notifyDataSetChanged()

    }
}