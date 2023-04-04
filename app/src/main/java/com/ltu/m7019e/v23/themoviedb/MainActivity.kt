package com.ltu.m7019e.v23.themoviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
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

            R.id.action_settings -> {
                val navController = Navigation.findNavController(this,R.id.nav_host_fragment)
                navController.navigate(R.id.SettingsFragment)
                true
            }

            R.id.action_genres -> {
                val navController = Navigation.findNavController(this,R.id.nav_host_fragment)
                navController.navigate(R.id.SecondFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}