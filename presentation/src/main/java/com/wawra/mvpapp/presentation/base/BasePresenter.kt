package com.wawra.mvpapp.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface Presenter<in M : Any, in V> {
    fun start(view: V, presentationModel: M)
    fun stop()
    fun resume()
    fun pause()
    fun destroy()
}

abstract class BasePresenter<M : Any, V> : Presenter<M, V> {

    protected var view: V? = null
    protected lateinit var presentationModel: M
    private val disposables = CompositeDisposable()

    override fun start(view: V, presentationModel: M) {
        this.view = view
        this.presentationModel = presentationModel
    }

    override fun stop() {
        this.view = null
    }

    override fun resume() {
    }

    override fun pause() {
    }

    override fun destroy() {
        disposables.clear()
    }

    protected fun Disposable.addToDisposables() {
        disposables.add(this)
    }
}
