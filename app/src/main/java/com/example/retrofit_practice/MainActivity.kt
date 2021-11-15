package com.example.retrofit_practice

import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.view.doOnDetach
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_practice.adapters.CountryDialogAdapter
import com.example.retrofit_practice.di.AppComponent
import com.example.retrofit_practice.di.DaggerAppComponent
import com.example.retrofit_practice.fragments.CasesPerCountryFragment
import com.example.retrofit_practice.fragments.HistoryPerCountryFragment
import com.example.retrofit_practice.fragments.VaccinatedFragment
import com.example.retrofit_practice.vm.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    private final val EDITTEXT_CHANGE_DELAY = 300L


    @Inject
    @Named("countryList")
    lateinit var countryList: ArrayList<Int>

    @Inject
    lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApplication).appComponent.inject(this)

        val onClick = View.OnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Select country")

            val dialogLayout = layoutInflater.inflate(R.layout.dialog_countries, null, false)
            val input = dialogLayout.findViewById<EditText>(R.id.country_et)
            builder.setView(dialogLayout)
            dialogLayout.doOnDetach { mainViewModel.SearchQuery.postValue("") }

            builder.setPositiveButton("Confirm", DialogInterface.OnClickListener { _, _ ->
                if (!input.text.toString().isEmpty()) {
                    val fragmentManager = supportFragmentManager

                    val bundle = Bundle().apply {
                        putString("country", input.text.toString().trim())
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

            val recyclerView = dialogLayout.findViewById<RecyclerView>(R.id.dialog_rv)
            val countryAdapter = CountryDialogAdapter(countryList, resources, applicationContext, object: CountryDialogAdapter.CountryClickListener{
                override fun onCountryClick(selectedCountry: String) {
                    input.text.clear()
                    input.text.insert(0, selectedCountry)
                    dialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick()
                }
            })

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = countryAdapter


            input.addTextChangedListener(afterTextChanged = {
                mainViewModel.SearchQuery.postValue(it.toString())
            })

            mainViewModel.SearchQuery.observe(this) {
                countryAdapter.filter(it)
            }

        }

        val cases =
            findViewById<RelativeLayout>(R.id.relative_cases).also { it.setOnClickListener(onClick) }
        val history =
            findViewById<RelativeLayout>(R.id.relative_history).also { it.setOnClickListener(onClick) }
        val vaccinated = findViewById<RelativeLayout>(R.id.relative_vaccinated).also {
            it.setOnClickListener(onClick)
        }
    }

    fun hideNavigation() {
        findViewById<View>(R.id.bottom_sheet).apply {
            visibility = View.INVISIBLE
        }
    }

    fun displayNavigation() {
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
