package com.example.newsuperkeyboard

import android.os.Build
import android.os.Bundle
import android.provider.UserDictionary
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoTextView)

        var languages2: Array<String> = arrayOf("java", "jarvan", "joubert")

        // Create adapter and add in AutoCompleteTextView
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, languages2)
        autotextView.setAdapter(adapter)

        languages2 = arrayOf("sasassasa", "jarvan", "joubert")
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, languages2)
        autotextView.setAdapter(adapter)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
