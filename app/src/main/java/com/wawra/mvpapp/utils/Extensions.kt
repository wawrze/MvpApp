package com.wawra.mvpapp.utils

import android.app.Activity
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.wawra.mvpapp.R
import com.wawra.mvpapp.ui.MainActivity


fun Activity.changeFragment(fragment: Fragment) {
    (this as? MainActivity)?.apply {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_details_fragment, fragment, null)
            .commit()
        binding?.activityMainDetailsFragment?.show()
        val animateIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_animation)
        animateIn.fillAfter = true
        binding?.activityMainDetailsFragment?.startAnimation(animateIn)
    }
}

fun Activity.overrideOnBackPressed(override: (() -> Unit)?) {
    (this as? MainActivity)?.apply {
        backPressedOverride = override
    }
}

fun View.gone() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE
