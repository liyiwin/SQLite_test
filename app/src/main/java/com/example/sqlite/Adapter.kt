package com.example.sqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemview_one.view.*

class Adapter(outerlist:MutableList<Model>,var ct:Context):RecyclerView.Adapter<Adapter.ViewHolder>(){

    var innerlist = mutableListOf<Model>()


    init{

        innerlist = outerlist

    }

    inner class ViewHolder(v: View):RecyclerView.ViewHolder(v){

      val vo_txt = v.vo_txt

      val me_txt = v.me_txt
        

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =LayoutInflater.from(ct).inflate(R.layout.itemview_one,parent,false)

        return ViewHolder(v)

    }

    override fun getItemCount(): Int = innerlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.me_txt.text = innerlist[position].meaning

        holder.vo_txt.text = innerlist[position].vocabulary


    }

    fun update(list:MutableList<Model>){

        innerlist =list

        notifyDataSetChanged()

    }
}