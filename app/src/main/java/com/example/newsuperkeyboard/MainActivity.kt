package com.example.newsuperkeyboard

import android.os.Build
import android.os.Bundle
import android.provider.UserDictionary
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.button.MaterialButton

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.search_edt)
        btn1 = findViewById(R.id.sugg1_btn)
        btn2 = findViewById(R.id.sugg2_btn)
        btn3 = findViewById(R.id.sugg3_btn)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                btn1.setText("ddcdc")
                btn2.setText("azerty")
                btn3.setText("cvnh,")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //
            }
        })

        btn1.setOnClickListener(View.OnClickListener {
            addTextInEditText(btn1.text)
        })
        btn2.setOnClickListener(View.OnClickListener {
            addTextInEditText(btn2.text)
        })
        btn3.setOnClickListener(View.OnClickListener {
            addTextInEditText(btn3.text)
        })
    }

    private fun addTextInEditText(btnText: CharSequence){
        val currentText = editText.text
        editText.setText( "$currentText $btnText")
        editText.setSelection(editText.text.length)
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
