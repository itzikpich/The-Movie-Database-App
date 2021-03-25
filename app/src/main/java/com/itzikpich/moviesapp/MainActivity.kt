package com.itzikpich.moviesapp

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.Behavior.DragCallback
import com.google.gson.Gson
import com.itzikpich.moviesapp.di.components.ActivityComponent
import com.itzikpich.moviesapp.utilities.loadFromUrlToGlide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
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

        (appbar.layoutParams as CoordinatorLayout.LayoutParams).behavior = object : AppBarLayout.Behavior() {}

//        collapsing_toolbar_layout.setCollapsedTitleTextAppearance(R.style.coll_toolbar_title)
//        collapsing_toolbar_layout.setExpandedTitleTextAppearance()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

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

    fun expandToolbar(expand: Boolean, recyclerView: ViewGroup) {
        appbar.setExpanded(expand, false)
        if (expand) unLockAppBar(recyclerView)
        else lockAppBar(recyclerView)
    }

    fun setToolbarTitle(title: String) {
        collapsing_toolbar_layout.title = title
    }

    fun setToolbarColor(color: Int) {
        toolbar.setBackgroundResource(color)
    }

    fun setToolbarImage(src: String) {
        collapsing_toolbar_layout.expandedImage.loadFromUrlToGlide(src)
    }

    private fun unLockAppBar(recyclerView: ViewGroup) {
        ViewCompat.setNestedScrollingEnabled(recyclerView, true)
        val params = appbar.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as AppBarLayout.Behavior?
        behavior?.setDragCallback(object : DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return true
            }
        })
    }

    private fun lockAppBar(recyclerView: ViewGroup) {
        /* Disable the nestedScrolling to disable expanding the
     appBar with dragging the nestedScrollView below it */
        ViewCompat.setNestedScrollingEnabled(recyclerView, false)

        /* But still appBar is expandable with dragging the appBar itself
    and below code disables that too
     */
        val params = appbar.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as AppBarLayout.Behavior?
        behavior?.setDragCallback(object : DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return false
            }
        })
    }

}
