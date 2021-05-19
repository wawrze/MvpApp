package com.wawra.mvpapp.utils

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.wawra.mvpapp.R
import com.wawra.mvpapp.ui.MainActivity

fun Activity.changeFragment(fragment: Fragment) {
    (this as? MainActivity)?.apply {
        supportFragmentManager
            .beginTransaction()
            .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(R.id.activity_main_fragment, fragment, null)
            .addToBackStack(null)
            .commit()
    }
}

fun View.gone() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}