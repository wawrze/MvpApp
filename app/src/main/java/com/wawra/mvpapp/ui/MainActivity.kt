package com.wawra.mvpapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            binding?.activityMainDetailsFragment?.isVisible() == true ->
                binding?.activityMainDetailsFragment?.gone()
            else -> super.onBackPressed()
        }
    }
}
