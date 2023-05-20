package com.example.mynavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mynavigation.R
import com.example.mynavigation.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        val view = binding.root
        setContentView(view)
        // back Button
        val navHostFragment = supportFragmentManager.
        findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)


        binding.bottomNavigation.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.menu_home -> replaceFragment(Fragment1())
                R.id.menu_profile -> replaceFragment(ProfilePage())
                R.id.menu_card -> replaceFragment(Mycard())
                R.id.menu_restos -> replaceFragment(Fragment1())
                R.id.menu_notif -> replaceFragment(Notifications())
            }
            true
        }

    }





    private fun replaceFragment(fragment : Fragment){
          if(fragment != null){
              val transaction = supportFragmentManager.beginTransaction()
              transaction.replace(R.id.fragmentContainerView, fragment)
              transaction.addToBackStack(null)
              transaction.commit()
          }
    }

}