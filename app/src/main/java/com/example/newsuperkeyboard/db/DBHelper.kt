package com.tutomobile.android.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import com.example.newsuperkeyboard.BuildConfig
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class DBHelper(
    context: Context,
    name: String,
    factory: CursorFactory?,
    version: Int
) :
    SQLiteOpenHelper(context, name, factory, version) {


    private val DATABASE_NAME = "Database.db"
    private val DATABASE_PATH = "/data/data/" + BuildConfig.APPLICATION_ID.toString() + "/databases/"
    private var mContext: Context

    init {
        mContext = context
    }

    override fun onCreate(db: SQLiteDatabase) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_TWOGRAMS + ";")
        onCreate(db)
    }

    companion object {
        private const val TABLE_TWOGRAMS = "'2GRAMS'"
        private const val COL_PREVIOUS = "PREVIOUS"
        private const val COL_CURRENT = "CURRENT"
        private const val CREATE_BDD =
            ("CREATE TABLE IF NOT EXISTS " + TABLE_TWOGRAMS + " ("
                    + COL_PREVIOUS + " TEXT NOT NULL, " + COL_CURRENT + " TEXT NOT NULL);")
    }

    @Throws(IOException::class)
    fun createDatabase() {
        this.readableDatabase
        close()
        copyDataBase()
    }

    @Throws(IOException::class)
    private fun copyDataBase() {
        val mInput: InputStream = mContext.getAssets().open(DATABASE_NAME)
        val outFileName: String = DATABASE_PATH + DATABASE_NAME
        val mOutput: OutputStream = FileOutputStream(outFileName)
        val mBuffer = ByteArray(2024)
        var mLength: Int
        while (mInput.read(mBuffer).also { mLength = it } > 0) {
            mOutput.write(mBuffer, 0, mLength)
        }
        mOutput.flush()
        mOutput.close()
        mInput.close()
    }

}