package com.example.a4kfullwalpapers

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blur_layout.startBlur()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_popular, R.id.nav_random, R.id.nav_favorite, R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        home_btn.setOnClickListener {
            navController.popBackStack()
            invisibleCircle()
            circle_1.visibility = View.VISIBLE
            navController.navigate(R.id.nav_home)
        }
        popular_btn.setOnClickListener {
            navController.popBackStack()
            invisibleCircle()
            circle_2.visibility = View.VISIBLE
            navController.navigate(R.id.nav_popular)
        }
        random_btn.setOnClickListener {
            navController.popBackStack()
            invisibleCircle()
            circle_3.visibility = View.VISIBLE
            navController.navigate(R.id.nav_random)
        }
        favorite_btn.setOnClickListener {
            navController.popBackStack()
            invisibleCircle()
            circle_4.visibility = View.VISIBLE
            navController.navigate(R.id.nav_favorite)
        }
    }

    fun invisibleCircle() {
        circle_1.visibility = View.INVISIBLE
        circle_2.visibility = View.INVISIBLE
        circle_3.visibility = View.INVISIBLE
        circle_4.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (itemId) {
            R.id.search -> {
                navController.navigate(R.id.searchFragment)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onStop() {
        super.onStop()
        blur_layout.pauseBlur()
    }

    fun hideBottomMenu() {
        blur_layout.visibility = View.INVISIBLE
    }

    fun showBottomMenu() {
        blur_layout.visibility = View.VISIBLE
    }

    fun showCircle(position: Int) {
        circle_1.visibility = View.INVISIBLE
        circle_2.visibility = View.INVISIBLE
        circle_3.visibility = View.INVISIBLE
        circle_4.visibility = View.INVISIBLE
        if (position == 1) {
            circle_1.visibility = View.VISIBLE
        } else if (position == 2) {
            circle_2.visibility = View.VISIBLE
        } else if (position == 3) {
            circle_3.visibility = View.VISIBLE
        } else if (position == 4) {
            circle_4.visibility = View.VISIBLE
        }

    }
}