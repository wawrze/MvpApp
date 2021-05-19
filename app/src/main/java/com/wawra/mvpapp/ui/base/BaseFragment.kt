package com.wawra.mvpapp.ui.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wawra.mvpapp.presentation.base.Presenter
import dagger.android.support.AndroidSupportInjection
import java.io.Serializable
import javax.inject.Inject

abstract class BaseFragment<M : Serializable, V, P : Presenter<M, V>> : Fragment() {

    @Inject
    protected lateinit var presenter: P

    private val mvpDelegate: MvpAndroidLifecycleDelegate<M, V, P> = object :
        MvpAndroidLifecycleDelegate<M, V, P>() {
        override fun createPresenter(): P = presenter

        override fun createPresentationModel() = this@BaseFragment.createPresentationModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpDelegate.createElements(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        @Suppress("UNCHECKED_CAST")
        mvpDelegate.start(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mvpDelegate.destroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvpDelegate.saveState(outState)
    }

    override fun onStop() {
        super.onStop()
        mvpDelegate.stop()
    }

    abstract fun createPresentationModel(): M
}
