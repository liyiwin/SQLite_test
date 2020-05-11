package com.example.sqlite

import android.database.sqlite.SQLiteDatabase
import android.text.BoringLayout
import android.widget.TextView



data class Model(

    var vocabulary :String,

    var meaning:String


)


data class edit_Model(

    var vocabulary :String,

    var meaning:String,

    var isedit:Boolean

)