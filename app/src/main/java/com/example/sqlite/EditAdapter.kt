package com.example.sqlite

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemview_add.view.*
import kotlinx.android.synthetic.main.itemview_edit.view.*

class EditAdapter (var outerlist :MutableList<edit_Model>,var ct : Context) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

     var innerlist = mutableListOf<edit_Model>()

     var addlist = mutableListOf<Int>(1)

    companion object{

        val basic_type:Int = 0
        val add_type:Int = 1

    }

    init {

        innerlist = outerlist


    }



    interface Delete{


        fun delete(vocabulary:String)


    }


    var deleteImp :Delete? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(viewType){

            basic_type  -> {

  //              Log.d("count","onCreateViewHolder")

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

     when(position){

          in innerlist.indices -> return basic_type

          else-> return add_type


     }


 }

    override fun getItemCount(): Int = innerlist.size+addlist.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {



    if(holder is ViewHodler_Basic){

        holder.setUI(position)

        holder.setAction(position)

    }

    if(holder is ViewHolder_Add){

        holder.setAction(position)


    }

    }

    inner class ViewHodler_Basic (view: View):RecyclerView.ViewHolder(view){

       val edit_btn = view.edit_btn

        val me_et = view.me_et

        val vo_et = view.vo_et

        val me_tx = view.me_tx

        val vo_tx = view.vo_tx

        val delete_btn = view.delete_btn

        val save_btn = view.save_btn

        val item_number = view.item_number

        val tx_Rl = view.tx_Rl

        val et_Rl = view.et_Rl


        fun setUI(position: Int){

            // 重綁 view

            item_number.setText("${position+1}")

            me_tx.setText(innerlist[position].meaning)

            vo_tx.setText(innerlist[position].vocabulary)

            me_et.setText(innerlist[position].meaning)

            vo_et.setText(innerlist[position].vocabulary)


            if(innerlist[position].isedit)  setEditMode()

            else setReadMode()


        }


        fun setAction(position: Int){

            edit_btn.setOnClickListener {

                setlist(innerlist[position],null,null,true)

                notifyDataSetChanged()

            }
            save_btn.setOnClickListener {

                val vocabulary =  vo_et.text.toString()

                val mean = me_et.text.toString()

                if( mean != "" && vocabulary!= ""){

                    setlist(innerlist[position],vocabulary,mean,false)

                    notifyDataSetChanged()

                    Toast("儲存成功",Toast.LENGTH_LONG)

                }

                else Toast("項目不可為空白",Toast.LENGTH_LONG)

            }

            delete_btn.setOnClickListener {

                if(innerlist[position].meaning!=null && innerlist[position].vocabulary!=null){

                    deleteImp?.delete(innerlist[position].vocabulary)

                    innerlist.removeAt(position)

                    notifyDataSetChanged()

                }

                else{

                    innerlist.removeAt(position)

                    notifyDataSetChanged()

                }

            }



        }

        fun setlist(list:edit_Model,vocabulary: String?,mean:String?,isedit:Boolean?){

            if (vocabulary!= null) list.vocabulary =vocabulary

            if(mean!= null) list.meaning = mean

            if (isedit!=null)  list.isedit = isedit

        }

        fun setReadMode(){


            et_Rl.visibility = View.GONE

            tx_Rl.visibility = View.VISIBLE

            save_btn.visibility  = View.GONE

            edit_btn.visibility = View.VISIBLE


        }

        fun setEditMode(){

            et_Rl.visibility = View.VISIBLE

            tx_Rl.visibility = View.GONE

            save_btn.visibility  = View.VISIBLE

            edit_btn.visibility = View.GONE


        }


    }


    inner class ViewHolder_Add(view: View):RecyclerView.ViewHolder(view){

        val add_btn = view.add_btn

        fun setAction(position: Int){

           add_btn.setOnClickListener {

                innerlist.add(edit_Model("","",false))

                notifyDataSetChanged()

            }

        }


    }



    fun update(list:MutableList<edit_Model>){

        innerlist = list

        notifyDataSetChanged()

    }

    fun Toast(message:String,length:Int){

       Toast.makeText(ct,message,length).show()

    }


}