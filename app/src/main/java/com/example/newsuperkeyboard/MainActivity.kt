package com.example.newsuperkeyboard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.newsuperkeyboard.util.convertDpToPx
import com.example.newsuperkeyboard.util.getIdFromString
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: RestaurantAdapter

    lateinit var editText: EditText
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initListeners()
        initRecyclerView()

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
    }

    private fun initListeners() {

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                btn1.setText("ddcdc")
                btn2.setText("azerty")
                btn3.setText("cvnh,")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.toString()?.let { str ->
                    getIdFromString(str)?.let {
                        viewModel.requestRestaurant(it)
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
