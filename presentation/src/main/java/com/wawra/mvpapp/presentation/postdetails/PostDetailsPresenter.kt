package com.wawra.mvpapp.presentation.postdetails

import com.wawra.mvpapp.presentation.base.BasePresenter
import javax.inject.Inject

typealias BaseMvpPresenter = BasePresenter<PostDetailsPresentationModel, PostDetailsView>

class PostDetailsPresenter @Inject constructor() : BaseMvpPresenter() {

    override fun start(view: PostDetailsView, presentationModel: PostDetailsPresentationModel) {
        super.start(view, presentationModel)
        if (!presentationModel.linkLoaded) {
            view.showLoading()
            presentationModel.activeUrl?.let { view.loadUrl(it) }
        }
    }

    fun performAction(interaction: PostDetailsViewInteraction) {
        when (interaction) {
            is PostDetailsViewInteraction.BackPressed -> backPressed()
            is PostDetailsViewInteraction.ChangeActiveUrl -> setActiveUrl(interaction.url)
            is PostDetailsViewInteraction.LinkLoading -> setLinkLoading(interaction.isLoaded)
        }
    }

    private fun setLinkLoading(isLoaded: Boolean) {
        presentationModel.linkLoaded = isLoaded
        if (isLoaded) view?.hideLoading() else view?.showLoading()
    }

    private fun setActiveUrl(url: String?) {
        when {
            presentationModel.startUrl == null -> presentationModel.startUrl = url
            url == presentationModel.startUrl -> presentationModel.previousUrl = null
            else -> presentationModel.previousUrl = presentationModel.activeUrl
        }
        presentationModel.activeUrl = url
    }

    private fun backPressed() {
        presentationModel.previousUrl?.let {
            view?.showLoading()
            view?.loadUrl(it)
        } ?: view?.close()
    }
}
