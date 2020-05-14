package com.example.newsuperkeyboard.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.tutomobile.android.sqlite.DBHelper

class TwoGramsDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    private var bdd: SQLiteDatabase? = null

    private var maBaseSQLite: DBHelper? = null

    companion object{
        val DATABASE_NAME = "Database.db"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "2GRAMS"
        val PREVIOUS = "PREVIOUS"
        val CURRENT = "CURRENT"
    }

    init {
        //On crée la BDD et sa table
        maBaseSQLite = DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
    }

    fun open() {
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite!!.writableDatabase
    }

    override fun close() {
        //on ferme l'accès à la BDD
        bdd!!.close()
    }

    fun getBDD(): SQLiteDatabase? {
        return bdd
    }

    fun insertData(previous: String, current: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(PREVIOUS, previous)
        contentValues.put(CURRENT, current)
        db.insert(TABLE_NAME, null, contentValues)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun getTwoGramsCorpusSuggestion(previous: String): List<String> {
        var twoGrams  = mutableListOf<String>()
        val db = this.readableDatabase
        val selectQuery = "SELECT CURRENT FROM " + TABLE_NAME + " WHERE PREVIOUS  = '" + previous + "'" +
                " GROUP BY CURRENT  ORDER BY COUNT(*) DESC LIMIT 2"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                       twoGrams.add(cursor.getString(0))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return twoGrams
    }
}