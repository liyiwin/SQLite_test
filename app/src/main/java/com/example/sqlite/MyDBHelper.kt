package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context, name:String = database, factory:
SQLiteDatabase.CursorFactory?=null, version : Int = v ) :
    SQLiteOpenHelper(context,name, factory, version){

    companion object{
        private const val database ="mdatabase.db"

        private const val v = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE myTable(vocabulary text PRIMARY KEY NOT NULL, mean text NOT NULL)" )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS myTable")

        onCreate(db)

    }


}