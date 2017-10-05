package com.machimbira.currency.features

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.machimbira.currency.R
import com.machimbira.currency.configuration.CurrencyApplication
import com.machimbira.currency.features.convertCurrencyScreen.ConvertCurrencyFragment
import com.machimbira.currency.features.startUpScreen.StartupFragment
import com.machimbira.currency.persistence.preferences.PreferencesManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(R.id.screen_container, StartupFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_convert -> {
                replaceFragment(R.id.screen_container, ConvertCurrencyFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                replaceFragment(R.id.screen_container, SettingsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_home)

        CurrencyApplication.setLanguage("en")
        if(PreferencesManager(this).getLanguagePreference().equals(resources.getString(R.string.shona))){
            CurrencyApplication.setLanguage("es")
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.screen_container, StartupFragment.newInstance())
        transaction.commit()
    }

    private fun replaceFragment(view: Int, newFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.addToBackStack("backstack")
        transaction.replace(view, newFragment)
        transaction.commit()
    }
}
