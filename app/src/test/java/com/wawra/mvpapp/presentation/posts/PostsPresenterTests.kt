package com.wawra.mvpapp.presentation.posts

import android.provider.ContactsContract.CommonDataKinds.Email
import com.nhaarman.mockitokotlin2.*
import com.wawra.mvpapp.domain.FORMATTER
import com.wawra.mvpapp.domain.models.Post
import com.wawra.mvpapp.domain.usecases.base.GetPostsUseCase
import com.wawra.mvpapp.domain.usecases.base.RefreshAndGetPostsUseCase
import com.wawra.mvpapp.domain.usecases.base.UseCaseFactory
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
class PostsPresenterTests {

    @Captor
    lateinit var postDtoListCaptor: ArgumentCaptor<List<PostDto>>

    @Captor
    lateinit var stringCaptor: ArgumentCaptor<String>

    @Captor
    lateinit var intCaptor: ArgumentCaptor<Int>

    private lateinit var presenter: PostsPresenter
    private lateinit var useCaseFactory: UseCaseFactory
    private lateinit var view: PostsView
    private lateinit var presentationModel: PostsPresentationModel
    private lateinit var getPostsUseCase: GetPostsUseCase
    private lateinit var refreshAndGetPostsUseCase: RefreshAndGetPostsUseCase

    @Before
    fun setup() {
        view = mock()
        getPostsUseCase = mock()
        refreshAndGetPostsUseCase = mock()
        useCaseFactory = mock()
        whenever(useCaseFactory.provideGetPostsUseCase())
            .thenReturn(getPostsUseCase)
        whenever(useCaseFactory.provideRefreshAndGetPostsUseCase())
            .thenReturn(refreshAndGetPostsUseCase)
        presentationModel = PostsPresentationModel()
        presenter = PostsPresenter(useCaseFactory)
        presenter.start(view, presentationModel)
    }

    @Test
    fun `get posts from DB success`() {
        // given
        val posts = listOf(
            Post("title 1", 1L, LocalDate.now(), "imageUrl 1", "description 1", "linkUrl 1"),
            Post("title 2", 2L, LocalDate.now(), "imageUrl 2", "description 2", "linkUrl 2")
        )
        whenever(getPostsUseCase.execute()).thenReturn(Single.just(posts))
        // when
        presenter.resume()
        // then
        assert(presentationModel.state is PresenterState.Content)
        assertEquals(posts.size, (presentationModel.state as PresenterState.Content).posts.size)
        assertEquals(posts[0].orderId, (presentationModel.state as PresenterState.Content).posts[0].orderId)
        assertEquals(posts[1].orderId, (presentationModel.state as PresenterState.Content).posts[1].orderId)
        verify(view).showLoading()
        verify(view).hideErrorContent()
        verify(view).hideEmptyContent()
        verify(view).hideLoading()
        verify(view).showPosts(capture(postDtoListCaptor))
        assertEquals(posts.size, postDtoListCaptor.value.size)
        assertEquals(posts[0].orderId, postDtoListCaptor.value[0].id)
        assertEquals(posts[1].orderId, postDtoListCaptor.value[1].id)
        assertEquals(posts[1].title, postDtoListCaptor.value[1].title)
        assertEquals(posts[1].description, postDtoListCaptor.value[1].description)
        assertEquals(FORMATTER.format(posts[1].modificationDate), postDtoListCaptor.value[1].date)
        assertEquals(posts[1].imageUrl, postDtoListCaptor.value[1].imageUrl)
    }

    @Test
    fun `get posts from DB empty list`() {
        // given
        val posts = listOf<Post>()
        whenever(getPostsUseCase.execute()).thenReturn(Single.just(posts))
        // when
        presenter.resume()
        // then
        assert(presentationModel.state is PresenterState.Content)
        assertEquals(0, (presentationModel.state as PresenterState.Content).posts.size)
        verify(view).showLoading()
        verify(view).hideErrorContent()
        verify(view).hidePostList()
        verify(view).hideLoading()
        verify(view).showEmptyContent()
    }

    @Test
    fun `get posts from DB error`() {
        // given
        whenever(getPostsUseCase.execute()).thenReturn(Single.error(Exception()))
        // when
        presenter.resume()
        // then
        assert(presentationModel.state is PresenterState.ErrorContent)
        verify(view).showLoading()
        verify(view, times(2)).hideEmptyContent()
        verify(view, times(2)).hidePostList()
        verify(view, times(2)).hideLoading()
        verify(view, times(2)).showErrorContent()
        verify(view).showErrorMessage()
    }

    @Test
    fun `refresh posts success`() {
        // given
        val posts = listOf(
            Post("title 1", 1L, LocalDate.now(), "imageUrl 1", "description 1", "linkUrl 1"),
            Post("title 2", 2L, LocalDate.now(), "imageUrl 2", "description 2", "linkUrl 2")
        )
        whenever(refreshAndGetPostsUseCase.execute()).thenReturn(Single.just(posts))
        // when
        presenter.performAction(PostsViewInteraction.Refresh)
        // then
        assert(presentationModel.state is PresenterState.Content)
        assertEquals(posts.size, (presentationModel.state as PresenterState.Content).posts.size)
        assertEquals(posts[0].orderId, (presentationModel.state as PresenterState.Content).posts[0].orderId)
        assertEquals(posts[1].orderId, (presentationModel.state as PresenterState.Content).posts[1].orderId)
        verify(view).showLoading()
        verify(view).hideErrorContent()
        verify(view).hideEmptyContent()
        verify(view).hideLoading()
        verify(view).showPosts(capture(postDtoListCaptor))
        assertEquals(posts.size, postDtoListCaptor.value.size)
        assertEquals(posts[0].orderId, postDtoListCaptor.value[0].id)
        assertEquals(posts[1].orderId, postDtoListCaptor.value[1].id)
        assertEquals(posts[1].title, postDtoListCaptor.value[1].title)
        assertEquals(posts[1].description, postDtoListCaptor.value[1].description)
        assertEquals(FORMATTER.format(posts[1].modificationDate), postDtoListCaptor.value[1].date)
        assertEquals(posts[1].imageUrl, postDtoListCaptor.value[1].imageUrl)
    }

    @Test
    fun `refresh posts empty list`() {
        // given
        val posts = listOf<Post>()
        whenever(refreshAndGetPostsUseCase.execute()).thenReturn(Single.just(posts))
        // when
        presenter.performAction(PostsViewInteraction.Refresh)
        // then
        assert(presentationModel.state is PresenterState.Content)
        assertEquals(0, (presentationModel.state as PresenterState.Content).posts.size)
        verify(view).showLoading()
        verify(view).hideErrorContent()
        verify(view).hidePostList()
        verify(view).hideLoading()
        verify(view).showEmptyContent()
    }

    @Test
    fun `refresh posts error`() {
        // given
        whenever(refreshAndGetPostsUseCase.execute()).thenReturn(Single.error(Exception()))
        // when
        presenter.performAction(PostsViewInteraction.Refresh)
        // then
        assert(presentationModel.state is PresenterState.ErrorContent)
        verify(view).showLoading()
        verify(view, times(2)).hideEmptyContent()
        verify(view, times(2)).hidePostList()
        verify(view, times(2)).hideLoading()
        verify(view, times(2)).showErrorContent()
        verify(view).showErrorMessage()
    }

    @Test
    fun `show post details`() {
        // given
        presentationModel.state = PresenterState.Content(
            listOf(
                Post("title 1", 1L, LocalDate.now(), "imageUrl 1", "description 1", "linkUrl 1"),
                Post("title 2", 2L, LocalDate.now(), "imageUrl 2", "description 2", "linkUrl 2")
            )
        )
        // when
        presenter.performAction(PostsViewInteraction.ShowDetails(2L))
        // then
        verify(view).showDetails(capture(stringCaptor), capture(intCaptor))
        assertEquals("linkUrl 2", stringCaptor.value)
        assertEquals(1, intCaptor.value)
    }

    @Test
    fun `show post details wrong post id`() {
        // given
        presentationModel.state = PresenterState.Content(
            listOf(
                Post("title 1", 1L, LocalDate.now(), "imageUrl 1", "description 1", "linkUrl 1"),
                Post("title 2", 2L, LocalDate.now(), "imageUrl 2", "description 2", "linkUrl 2")
            )
        )
        // when
        presenter.performAction(PostsViewInteraction.ShowDetails(3L))
        // then
        verify(view, times(0)).showDetails(any(), any())
    }
}
