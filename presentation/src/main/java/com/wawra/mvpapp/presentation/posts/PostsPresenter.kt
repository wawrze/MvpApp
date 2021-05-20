package com.wawra.mvpapp.presentation.posts

import com.wawra.mvpapp.domain.FORMATTER
import com.wawra.mvpapp.domain.usecases.base.UseCaseFactory
import com.wawra.mvpapp.presentation.base.BasePresenter
import javax.inject.Inject

typealias BaseMvpPresenter = BasePresenter<PostsPresentationModel, PostsView>

class PostsPresenter @Inject constructor(
    private val useCaseFactory: UseCaseFactory
) : BaseMvpPresenter() {

    override fun start(view: PostsView, presentationModel: PostsPresentationModel) {
        super.start(view, presentationModel)
        presentationModel.posts ?: getPosts()
    }

    override fun resume() {
        presentationModel.posts?.let { showPosts() }
    }

    fun performAction(interaction: PostsViewInteraction) {
        when (interaction) {
            is PostsViewInteraction.Refresh -> refreshPosts()
            is PostsViewInteraction.ShowDetails -> showPostDetails(interaction.id)
        }
    }

    private fun showPostDetails(postId: Long) {
        presentationModel.posts?.firstOrNull { it.orderId == postId }?.let {
            view?.showDetails(it.linkUrl, presentationModel.posts?.indexOf(it))
        }
    }

    private fun refreshPosts() {
        view?.showLoading()
        useCaseFactory.provideRefreshAndGetPostsUseCase().execute()
            .subscribe(
                {
                    presentationModel.posts = it
                    showPosts()
                },
                {
                    view?.hideLoading()
                    view?.showError(it)
                }
            )
            .addToDisposables()
    }

    private fun getPosts() {
        view?.showLoading()
        useCaseFactory.provideGetPostsUseCase().execute()
            .subscribe(
                {
                    presentationModel.posts = it
                    showPosts()
                },
                {
                    view?.hideLoading()
                    view?.showError(it)
                }
            )
            .addToDisposables()
    }

    private fun showPosts() {
        presentationModel.posts?.map {
            PostDto(
                it.orderId,
                it.title,
                it.description,
                FORMATTER.format(it.modificationDate),
                it.imageUrl
            )
        }?.let { view?.showPosts(it) }
        view?.hideLoading()
    }
}
