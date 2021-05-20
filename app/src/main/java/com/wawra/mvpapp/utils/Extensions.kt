package com.wawra.mvpapp.utils

import android.app.Activity
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import com.wawra.mvpapp.R
import com.wawra.mvpapp.ui.MainActivity


fun Activity.changeFragment(fragment: Fragment) {
    (this as? MainActivity)?.apply {
        binding?.activityMainDetailsFragment?.show()
        val animate = TranslateAnimation(
            -(binding?.activityMainListFragment?.width?.toFloat() ?: 0f),
            0f,
            0f,
            0f
        )
        animate.duration = 500
        animate.fillAfter = true
        binding?.activityMainDetailsFragment?.startAnimation(animate)

        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_details_fragment, fragment, null)
            .commit()
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
