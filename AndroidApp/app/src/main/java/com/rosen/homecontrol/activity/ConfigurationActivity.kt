package com.rosen.homecontrol.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.rosen.homecontrol.R
import com.rosen.homecontrol.fragment.ConfigurationFragment

class ConfigurationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cofiguration)
        //setSupportActionBar(findViewById(R.id.toolbar_configuration))

        if (supportActionBar != null) {
            with(supportActionBar!!) {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }

        supportFragmentManager
                .beginTransaction()
                .replace(
                        R.id.fragment_container,
                        ConfigurationFragment()
                )
                .commit()
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            finish()
//        }
//        return true
//    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.toolbar_main_menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean =
//            when (item.itemId) {
//                android.R.id.home -> {
//                    finish()
//                    true
//                }
//                else -> super.onOptionsItemSelected(item)
//            }

}