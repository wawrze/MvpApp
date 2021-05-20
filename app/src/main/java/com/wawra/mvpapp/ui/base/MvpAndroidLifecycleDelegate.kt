package com.wawra.mvpapp.ui.base

import android.os.Bundle
import com.wawra.mvpapp.presentation.base.Presenter

abstract class MvpAndroidLifecycleDelegate<M : Any, V, out P : Presenter<M, V>>(
    private val modelSerializer: PresentationModelSerializer<M> = PresentationModelSerializer()
) {

    private lateinit var presenter: P
    private lateinit var presentationModel: M
    private val modelKey: String
        get() = (presenter.javaClass.canonicalName ?: "") + "\$PresentationModel"

    fun createElements(savedInstanceState: Bundle?) {
        this.presenter = createPresenter()
        this.presentationModel = restorePresentationModel(savedInstanceState)
            ?: createPresentationModel()
    }

    private fun restorePresentationModel(savedInstanceState: Bundle?) =
        modelSerializer.restorePresentationModel(savedInstanceState, modelKey)

    fun start(view: V) {
        presenter.start(view, presentationModel)
    }

    fun stop() {
        presenter.stop()
    }

    fun resume() {
        presenter.resume()
    }

    fun pause() {
        presenter.pause()
    }

    fun destroy() {
        presenter.destroy()
    }

    fun saveState(outState: Bundle) {
        modelSerializer.savePresentationModel(outState, modelKey, presentationModel)
    }

    protected abstract fun createPresenter(): P
    protected abstract fun createPresentationModel(): M
}
