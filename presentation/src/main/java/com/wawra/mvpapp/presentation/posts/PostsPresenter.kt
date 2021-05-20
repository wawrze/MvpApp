package com.wawra.mvpapp.presentation.posts

import com.wawra.mvpapp.domain.FORMATTER
import com.wawra.mvpapp.domain.models.Post
import com.wawra.mvpapp.domain.usecases.base.UseCaseFactory
import com.wawra.mvpapp.presentation.base.BasePresenter
import javax.inject.Inject

typealias BaseMvpPresenter = BasePresenter<PostsPresentationModel, PostsView>

class PostsPresenter @Inject constructor(
    private val useCaseFactory: UseCaseFactory
) : BaseMvpPresenter() {

    override fun resume() {
        setState()
    }

    fun performAction(interaction: PostsViewInteraction) {
        when (interaction) {
            is PostsViewInteraction.Refresh -> setState(PresenterState.Loading(true))
            is PostsViewInteraction.ShowDetails -> showPostDetails(interaction.id)
        }
    }

    private fun setState(state: PresenterState = presentationModel.state) {
        presentationModel.state = state
        when (state) {
            is PresenterState.Content -> showPosts(state.posts)
            is PresenterState.Error -> handleError()
            is PresenterState.ErrorContent -> showErrorContent()
            is PresenterState.Initial -> setState(PresenterState.Loading(false))
            is PresenterState.Loading -> loadPosts(state.withRefresh)
        }
    }

    private fun handleError() {
        view?.apply {
            hidePostList()
            hideEmptyContent()
            showErrorContent()
            hideLoading()
            showErrorMessage()
            setState(PresenterState.ErrorContent)
        }
    }

    private fun showErrorContent() {
        view?.hidePostList()
        view?.hideEmptyContent()
        view?.showErrorContent()
        view?.hideLoading()
    }

    private fun loadPosts(withRefresh: Boolean) {
        view?.showLoading()
        if (withRefresh) refreshPosts() else getPosts()
    }

    private fun showPostDetails(postId: Long) {
        (presentationModel.state as? PresenterState.Content)?.posts?.let { posts ->
            posts.firstOrNull { it.orderId == postId }?.let {
                view?.showDetails(it.linkUrl, posts.indexOf(it))
            }
        }
    }

    private fun refreshPosts() {
        useCaseFactory.provideRefreshAndGetPostsUseCase().execute()
            .subscribe(
                { setState(PresenterState.Content(it)) },
                { setState(PresenterState.Error) }
            )
            .addToDisposables()
    }

    private fun getPosts() {
        useCaseFactory.provideGetPostsUseCase().execute()
            .subscribe(
                { setState(PresenterState.Content(it)) },
                { setState(PresenterState.Error) }
            )
            .addToDisposables()
    }

    private fun showPosts(posts: List<Post>) {
        if (posts.isEmpty()) {
            view?.hidePostList()
            view?.hideErrorContent()
            view?.showEmptyContent()
        } else {
            view?.hideErrorContent()
            view?.hideEmptyContent()
            view?.showPosts(
                posts.map {
                    PostDto(
                        it.orderId,
                        it.title,
                        it.description,
                        FORMATTER.format(it.modificationDate),
                        it.imageUrl
                    )
                }
            )
        }
        view?.hideLoading()
    }
}
