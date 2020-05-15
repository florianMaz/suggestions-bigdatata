package com.example.newsuperkeyboard.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.tutomobile.android.sqlite.DBHelper

class NGramsDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    private var bdd: SQLiteDatabase? = null

    private var maBaseSQLite: DBHelper? = null

    companion object{
        val DATABASE_NAME = "Database.db"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "'2GRAMS'"
        val TABLE_USER_NAME = "'USER2GRAMS'"
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
        //
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //
    }

    fun getTwoGramsCorpusSuggestion(previous: String): List<String> {
        var twoGrams  = mutableListOf<String>()
        val db = this.readableDatabase
        val selectQuery = "SELECT CURRENT FROM " + TABLE_NAME + " WHERE PREVIOUS  = '" + previous + "'" +
                " GROUP BY CURRENT  ORDER BY COUNT(*) DESC LIMIT 3"
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

    fun getThreeGramsCorpusSuggestion(previous1: String, previous2: String): List<String> {
        var threeGrams  = mutableListOf<String>()
        val db = this.readableDatabase
        val selectQuery = "SELECT CURRENT FROM '3GRAMS' WHERE PREVIOUS1  = '" + previous1 + "'" +
                " AND PREVIOUS2 = '"+previous2+"' GROUP BY CURRENT  ORDER BY COUNT(*) DESC LIMIT 3"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        threeGrams.add(cursor.getString(0))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return threeGrams
    }

    fun getUserTwoGramsCorpusSuggestion(previous: String): List<String> {
        var twoGrams  = mutableListOf<String>()
        val db = this.readableDatabase
        val selectQuery = "SELECT CURRENT FROM " + TABLE_USER_NAME + " WHERE PREVIOUS  = '" + previous + "'" +
                " GROUP BY CURRENT  ORDER BY COUNT(*) DESC LIMIT 3"
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

    fun getUserThreeGramsCorpusSuggestion(previous1: String, previous2: String): List<String> {
        var threeGrams  = mutableListOf<String>()
        val db = this.readableDatabase
        val selectQuery = "SELECT CURRENT FROM 'USER3GRAMS' WHERE PREVIOUS1  = '" + previous1 + "'" +
                " AND PREVIOUS2 = '"+previous2+"' GROUP BY CURRENT  ORDER BY COUNT(*) DESC LIMIT 3"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        threeGrams.add(cursor.getString(0))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return threeGrams
    }

    fun insertTwoGramsCorpusSuggestion(suggestion: String) {
        var listSugg: List<String> = suggestion.split(" ")
        val currentWord = listSugg.lastOrNull()
        val previousWord = listSugg.get(listSugg.lastIndex-1)
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("PREVIOUS", previousWord)
        values.put("CURRENT", currentWord)
        db.insert("'USER2GRAMS'", null, values)
        db.close()
    }

    fun insertThreeGramsCorpusSuggestion(suggestion: String) {
        var listSugg: List<String> = suggestion.split(" ")
        val currentWord = listSugg.lastOrNull()
        val previousWord1 = listSugg.get(listSugg.lastIndex-2)
        val previousWord2 = listSugg.get(listSugg.lastIndex-1)
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("PREVIOUS1", previousWord1)
        values.put("PREVIOUS2", previousWord2)
        values.put("CURRENT", currentWord)
        db.insert("'USER3GRAMS'", null, values)
        db.close()
    }

    override fun getReadableDatabase(): SQLiteDatabase {
        return super.getReadableDatabase()
    }

    override fun onOpen(db: SQLiteDatabase?) {
        Log.d("DB Opened", db.toString())
    }
}