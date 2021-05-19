package com.wawra.mvpapp.presentation.postdetails

import com.wawra.mvpapp.presentation.base.BasePresenter
import javax.inject.Inject

typealias BaseMvpPresenter = BasePresenter<PostDetailsPresentationModel, PostDetailsView>

class PostDetailsPresenter @Inject constructor() : BaseMvpPresenter() {

    override fun start(view: PostDetailsView, presentationModel: PostDetailsPresentationModel) {
        super.start(view, presentationModel)
        if (!presentationModel.linkLoaded) view.loadUrl(presentationModel.linkUrl)
    }

    fun setLinkLoaded() {
        presentationModel.linkLoaded = true
        view?.hideLoading()
    }
}
