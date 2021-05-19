package com.wawra.mvpapp.presentation.posts

import com.wawra.mvpapp.domain.usecases.base.UseCaseFactory
import com.wawra.mvpapp.presentation.base.BasePresenter
import javax.inject.Inject

typealias BaseMvpPresenter = BasePresenter<PostsPresentationModel, PostsView>

class PostsPresenter @Inject constructor(
    private val useCaseFactory: UseCaseFactory
) : BaseMvpPresenter() {

    override fun start(view: PostsView, presentationModel: PostsPresentationModel) {
        super.start(view, presentationModel)
        presentationModel.posts?.let { view.showPosts(it) } ?: getPosts()
    }

    fun refreshPosts() {
        useCaseFactory.provideRefreshAndGetPostsUseCase().execute()
            .subscribe(
                {
                    view?.showPosts(it)
                },
                {
                    view?.showError(it)
                }
            )
            .addToDisposables()
    }

    private fun getPosts() {
        useCaseFactory.provideGetPostsUseCase().execute()
            .subscribe(
                {
                    view?.showPosts(it)
                },
                {
                    view?.showError(it)
                }
            )
            .addToDisposables()
    }
}
