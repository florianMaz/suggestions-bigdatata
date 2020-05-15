package com.example.newsuperkeyboard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsuperkeyboard.adapter.RestaurantAdapter
import com.example.newsuperkeyboard.db.NGramsDB
import com.example.newsuperkeyboard.util.convertDpToPx
import com.example.newsuperkeyboard.util.getIdFromString
import com.google.android.gms.location.LocationServices
import com.tutomobile.android.sqlite.DBHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: RestaurantAdapter

    private lateinit var tts: TextToSpeech

    lateinit var editText: EditText
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var sendBtn: Button

    lateinit var nGramsDB: NGramsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DBHelper(baseContext, "Database.db", null, 1)
        dbHelper.createDatabase()
        nGramsDB = NGramsDB(baseContext)

        nGramsDB.open()


        initViews()
        initListeners()
        initRecyclerView()

        tts = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.SUCCESS) {
                Log.d("COUCOU", "TTS init failed")
            } else {
                tts.language = Locale.FRENCH
            }
        })

        viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        viewModel.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        viewModel.restaurantsLiveData.observe(this, Observer {
            adapter.restaurants = it
        })

        checkForPermissions()

        val activityRootView = findViewById<View>(R.id.cl_root)
        activityRootView.viewTreeObserver.addOnGlobalLayoutListener {
            val heightDiff = activityRootView.rootView.height - activityRootView.height
            val isOpen = heightDiff > convertDpToPx(this@MainActivity.applicationContext, 200f)
            if (isOpen) {
                ll_predictions.visibility = View.VISIBLE
            } else {
                ll_predictions.visibility = View.GONE
            }
        }



    }

    private fun initViews() {
        editText = findViewById(R.id.search_edt)
        btn1 = findViewById(R.id.sugg1_btn)
        btn2 = findViewById(R.id.sugg2_btn)
        btn3 = findViewById(R.id.sugg3_btn)
        sendBtn = findViewById(R.id.send_btn)

        btn1.setText("Je")
        btn2.setText("Tu")
        btn3.setText("Salut")
    }

    private fun initListeners() {

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                // "le " => le
                var textClean = editText.text.trim()
                var lTextEntered = textClean.split(" ")

                var resultCorpus = listOf<String>()
                var resultUser= listOf<String>()
                //Log.d("lTextEntered", lTextEntered.toString())
                if(lTextEntered.size == 1) {

                    //Log.d("previousWord", lTextEntered.last())
                    resultCorpus =  nGramsDB.getTwoGramsCorpusSuggestion(lTextEntered.last())
                    resultUser =  nGramsDB.getUserTwoGramsCorpusSuggestion(lTextEntered.last())
                } else if(lTextEntered.size >= 2) {
                    val previousWord1 = lTextEntered.get(lTextEntered.lastIndex-1)
                    val previousWord2 = lTextEntered.last()
                    //Log.d("previousWord1", previousWord1)
                    //Log.d("previousWord2", previousWord2)
                    resultCorpus = nGramsDB.getThreeGramsCorpusSuggestion(previousWord1, previousWord2)
                    resultUser = nGramsDB.getUserThreeGramsCorpusSuggestion(previousWord1, previousWord2)
                }
                //Log.d("result corpus", resultCorpus.toString())
                //Log.d("result user", resultUser.toString())

                if (resultUser.size >= 2 && resultCorpus.size >= 1) {
                    btn1.setText(resultCorpus[0])
                    btn2.setText(resultUser[0])
                    btn3.setText(resultUser[1])
                } else if(resultUser.size == 1 && resultCorpus.size >= 2) {
                    btn1.setText(resultCorpus[0])
                    btn2.setText(resultUser[0])
                    btn3.setText(resultCorpus[1])
                } else if(resultCorpus.size == 3) {
                    btn1.setText(resultCorpus[2])
                    btn2.setText(resultCorpus[0])
                    btn3.setText(resultCorpus[1])
                } else if(resultUser.size == 1 && resultCorpus.isEmpty()) {
                    btn1.setText("")
                    btn2.setText(resultUser[0])
                    btn3.setText("")
                } else if(resultUser.isEmpty() && resultCorpus.isEmpty()) {
                    btn1.setText("")
                    btn2.setText("")
                    btn3.setText("")
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.toString()?.let { str ->
                    getIdFromString(str)?.let {
                        viewModel.requestRestaurant(it)
                        playFoodQuote()
                    } ?: run {
                        adapter.restaurants = listOf()
                    }
                }
            }
        })

        btn1.setOnClickListener {
            addTextInEditText(btn1.text)
        }
        btn2.setOnClickListener {
            addTextInEditText(btn2.text)
        }
        btn3.setOnClickListener {
            addTextInEditText(btn3.text)
        }

        sendBtn.setOnClickListener {
            val lTextEntered = editText.text.split(" ")
            if (lTextEntered.size == 2) {
                nGramsDB.insertTwoGramsCorpusSuggestion(editText.text.toString())

            } else if (lTextEntered.size >= 3) {
                nGramsDB.insertThreeGramsCorpusSuggestion(editText.text.toString())
            }
        }
    }

    private fun initRecyclerView() {
        adapter = RestaurantAdapter(this)
        rcv_restaurants.adapter = adapter
        rcv_restaurants.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun checkForPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }

    private fun addTextInEditText(btnText: CharSequence) {
        val currentText = editText.text
        editText.setText("$currentText $btnText")
        editText.setSelection(editText.text.length)
    }

    private fun playFoodQuote() {
        if (!toggle.isChecked) return
        if (tts.isSpeaking) return
        val array = resources.getStringArray(R.array.food)
        val str = array[Random().nextInt(array.size)]
        tts.speak(str, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "Noice", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
