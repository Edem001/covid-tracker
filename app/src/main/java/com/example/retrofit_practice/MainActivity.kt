package com.example.retrofit_practice

import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import androidx.fragment.app.commit
import com.example.retrofit_practice.di.AppComponent
import com.example.retrofit_practice.di.DaggerAppComponent
import com.example.retrofit_practice.fragments.CasesPerCountryFragment
import com.example.retrofit_practice.fragments.HistoryPerCountryFragment
import com.example.retrofit_practice.fragments.VaccinatedFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApplication).appComponent.inject(this)

        val onClick = View.OnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Select country")

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT
            input.width = ViewGroup.LayoutParams.MATCH_PARENT

            val paddingHorizontal = (20 * resources.displayMetrics.density + 0.5).toInt()
            val paddingVertical = (5 * resources.displayMetrics.density + 0.5).toInt()
            input.setPadding(paddingHorizontal)

            builder.setView(input)

            builder.setPositiveButton("Confirm", DialogInterface.OnClickListener { _, _ ->
                if (!input.text.toString().isEmpty()) {
                    val fragmentManager = supportFragmentManager

                    val bundle = Bundle().apply {
                        putString("country", input.text.toString())
                    }

                    when (it.id) {
                        R.id.relative_cases -> {
                            fragmentManager.commit {
                                replace(
                                    R.id.summary_fragment_container,
                                    CasesPerCountryFragment::class.java,
                                    bundle,
                                    "cases"
                                )
                                addToBackStack("main")
                            }

                        }
                        R.id.relative_history -> {
                            fragmentManager.commit {
                                replace(
                                    R.id.summary_fragment_container,
                                    HistoryPerCountryFragment::class.java,
                                    bundle,
                                    "history"
                                )
                                addToBackStack("main")
                            }
                        }
                        R.id.relative_vaccinated -> {
                            fragmentManager.commit {
                                replace(
                                    R.id.summary_fragment_container,
                                    VaccinatedFragment::class.java,
                                    bundle,
                                    "vaccinated"
                                )
                                addToBackStack("main")
                            }
                        }
                    }
                }
            })

            val dialog = builder.create()
            dialog.show()

        }

        val cases =
            findViewById<RelativeLayout>(R.id.relative_cases).also { it.setOnClickListener(onClick) }
        val history =
            findViewById<RelativeLayout>(R.id.relative_history).also { it.setOnClickListener(onClick) }
        val vaccinated = findViewById<RelativeLayout>(R.id.relative_vaccinated).also {
            it.setOnClickListener(onClick)
        }
    }

    fun hideNavigation(){
        findViewById<View>(R.id.bottom_sheet).apply {
            visibility = View.INVISIBLE
        }
    }

    fun displayNavigation(){
        findViewById<View>(R.id.bottom_sheet).apply {
            visibility = View.VISIBLE
        }
    }
}

class MyApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
