package com.wawra.mvpapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wawra.mvpapp.R
import com.wawra.mvpapp.ui.MainActivity

fun AppCompatActivity.changeFragment(fragment: Fragment) {
    (this as? MainActivity)?.apply {
        supportFragmentManager
            .beginTransaction()
            .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(R.id.activity_main_fragment, fragment, null)
            .addToBackStack(null)
            .commit()
    }
}