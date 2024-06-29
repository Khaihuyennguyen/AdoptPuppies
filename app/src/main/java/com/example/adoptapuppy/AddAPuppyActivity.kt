package com.example.adoptapuppy

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class AddAPuppyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_a_puppy)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name: EditText = findViewById(R.id.name)
        val age: EditText = findViewById(R.id.age)
        val breed: Spinner = findViewById(R.id.spinner)
        val availableFrom: EditText = findViewById(R.id.available_from)
        val trained: CheckBox = findViewById(R.id.trained)
        val calmness: Slider = findViewById(R.id.slider)
        val camera: Button = findViewById(R.id.camera_button)
        val add: Button = findViewById(R.id.add_button)

        add.setOnClickListener {
            val nameText = name.text.toString()
            val breedText = breed.selectedItem.toString()
            val isTrained = trained.isChecked
            Toast.makeText(this, "name: $nameText, breed: $breedText, is trained: $isTrained", Toast.LENGTH_SHORT).show()
        }

        breed.adapter = ArrayAdapter<String>(
            this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            arrayOf("Hello", "World")
        )

        calmness.setLabelFormatter { value: Float ->
            val format = NumberFormat.getPercentInstance()
            format.maximumFractionDigits = 1
            format.minimumFractionDigits = 1
            " ${format.format(value)} playful "
        }

        availableFrom.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()
            datePicker.show(supportFragmentManager, TAG)

            datePicker.addOnPositiveButtonClickListener {
                val millis = datePicker.selection
                if (millis != null) {
                    availableFrom.setText(dateToString(millisToDate(millis)))
                }
            }
        }
    }

    fun millisToDate(millis: Long): Date {
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone("UTC-7")
        calendar.timeInMillis = millis
        return calendar.time
    }

    fun dateToString(date: Date): String {
        val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("UTC-7")
        return sdf.format(date)
    }
}