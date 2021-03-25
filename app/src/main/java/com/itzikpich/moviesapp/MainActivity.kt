package com.itzikpich.moviesapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.gson.Gson
import com.itzikpich.moviesapp.di.components.ActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var activityComponent: ActivityComponent

    companion object {
        val TAG = this::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//      inject Dagger in the activity's onCreate() method before calling super.onCreate() to avoid issues with fragment restoration.
//      (applicationContext as MyApplication).appComponent.inject(this) // not needed anymore,  going through ActivityComponent
        (applicationContext as MyApplication).appComponent.activityComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

//        Handler().postDelayed({
//            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_categoryFragment,
//                null, NavOptions.Builder().setLaunchSingleTop(true).build()
//                              )
//        }, 3000)
    }

    override fun onResume() {
        super.onResume()
        NavigationUI.setupActionBarWithNavController(this,
                Navigation.findNavController(this, R.id.nav_host_fragment)
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
    }

    fun initToolBar(title: String?) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.mipmap.ic_launcher_round)
    }

}
