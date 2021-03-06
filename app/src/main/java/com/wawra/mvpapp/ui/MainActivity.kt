package com.wawra.mvpapp.ui

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.wawra.mvpapp.R
import com.wawra.mvpapp.databinding.ActivityMainBinding
import com.wawra.mvpapp.ui.base.FragmentProvider
import com.wawra.mvpapp.utils.gone
import com.wawra.mvpapp.utils.isVisible
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentProvider: FragmentProvider

    internal var binding: ActivityMainBinding? = null
    internal var backPressedOverride: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setView()
    }

    private fun setView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onBackPressed() {
        when {
            backPressedOverride != null -> backPressedOverride?.invoke()
            binding?.activityMainDetailsFragment?.isVisible() == true -> hideDetailsFragment()
            else -> super.onBackPressed()
        }
    }

    private fun hideDetailsFragment() {
        binding?.activityMainDetailsFragment?.gone()
        val animate = AnimationUtils.loadAnimation(this, R.anim.slide_out_animation)
        animate.fillAfter = false
        binding?.activityMainDetailsFragment?.startAnimation(animate)
    }
}
