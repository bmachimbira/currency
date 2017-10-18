package com.machimbira.currency.features


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.machimbira.currency.R
import com.machimbira.currency.configuration.CurrencyApplication
import com.machimbira.currency.persistence.preferences.PreferencesManager
import kotlinx.android.synthetic.main.fragment_settings.view.*
import za.co.cporm.model.CPOrm.getApplicationContext
import javax.inject.Inject


class SettingsFragment : Fragment() {

    @Inject
    private lateinit var preferences: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CurrencyApplication.iocContainer.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_settings, container, false)

        view.select_english.setOnClickListener {
            preferences.setLanguagePreferenceToEnglish()
            val languageToLoad = "en"
            CurrencyApplication.setLanguage(languageToLoad)
            Toast.makeText(getApplicationContext(), "Language set to English", Toast.LENGTH_SHORT).show()
        }

        view.select_shona.setOnClickListener {
            preferences.setLanguagePreferenceToShona()
            val languageToLoad = "es"
            CurrencyApplication.setLanguage(languageToLoad)
            Toast.makeText(getApplicationContext(), "Language set to Shona", Toast.LENGTH_SHORT).show()

        }
        return view
    }


    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }

}
