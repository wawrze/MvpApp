package com.wawra.mvpapp.ui.base

import com.wawra.mvpapp.ui.sample.SampleFragment
import javax.inject.Inject

class FragmentProvider @Inject constructor() {
    fun provideSampleFragment() = SampleFragment()
}
