package com.example.retrofit_practice

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import com.example.retrofit_practice.di.AppComponent
import com.example.retrofit_practice.di.DaggerAppComponent
import com.example.retrofit_practice.util.Status
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApplication).appComponent.inject(this)

        val onClick = View.OnClickListener {

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Select country")

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT
            dialog.setView(input)

            dialog.setPositiveButton("Confirm", DialogInterface.OnClickListener { _, _ ->
                when(it.id){
                    R.id.relative_cases -> {

                    }
                    R.id.relative_history -> {}
                    R.id.relative_vaccinated -> {}
                }
            })


        }

        val cases = findViewById<RelativeLayout>(R.id.relative_cases)
        val history = findViewById<RelativeLayout>(R.id.relative_history)
        val vaccinated = findViewById<RelativeLayout>(R.id.relative_vaccinated)
    }
}

class MyApplication : Application(){
    val appComponent:AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}