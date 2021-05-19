package com.wawra.mvpapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wawra.mvpapp.R
import com.wawra.mvpapp.ui.base.FragmentProvider
import com.wawra.mvpapp.utils.changeFragment
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentProvider: FragmentProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(fragmentProvider.providePostsFragment())
    }
}
