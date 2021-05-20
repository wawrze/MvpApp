package com.wawra.mvpapp.domain.usecases

import com.nhaarman.mockitokotlin2.*
import com.wawra.mvpapp.BaseTests
import com.wawra.mvpapp.domain.models.Post
import com.wawra.mvpapp.domain.repositories.PostsLocalRepository
import com.wawra.mvpapp.domain.repositories.PostsNetworkRepository
import com.wawra.mvpapp.domain.usecases.base.RefreshAndGetPostsUseCase
import com.wawra.mvpapp.utils.executors.IOThread
import com.wawra.mvpapp.utils.executors.UIThread
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class RefreshAndGetPostsUseCaseTests : BaseTests() {

    private lateinit var postsNetworkRepository: PostsNetworkRepository
    private lateinit var postsLocalRepository: PostsLocalRepository
    private lateinit var refreshAndGetPostsUseCase: RefreshAndGetPostsUseCase

    @Before
    fun setup() {
        postsNetworkRepository = mock()
        postsLocalRepository = mock()
        refreshAndGetPostsUseCase = RefreshAndGetPostsUseCase(
            postsNetworkRepository,
            postsLocalRepository,
            IOThread(),
            UIThread()
        )
    }

    @Test
    fun `refresh posts success`() {
        // given
        val posts = listOf(
            Post("title 1", 1L, LocalDate.now(), "imageUrl 1", "description 1", "linkUrl 1"),
            Post("title 2", 2L, LocalDate.now(), "imageUrl 2", "description 2", "linkUrl 2")
        )
        whenever(postsNetworkRepository.getPosts()).thenReturn(Single.just(posts))
        whenever(postsLocalRepository.deletePosts()).thenReturn(Single.just(true))
        whenever(postsLocalRepository.savePosts(posts)).thenReturn(Single.just(true))
        whenever(postsLocalRepository.getPosts()).thenReturn(Single.just(posts))
        // when
        val result = refreshAndGetPostsUseCase.execute().blockingGet()
        // then
        verify(postsNetworkRepository).getPosts()
        verify(postsLocalRepository).deletePosts()
        verify(postsLocalRepository).savePosts(any())
        assertEquals(posts.size, result.size)
        assertEquals(posts[0].orderId, result[0].orderId)
        assertEquals(posts[1].orderId, result[1].orderId)
    }

    @Test
    fun `refresh posts success empty list`() {
        // given
        whenever(postsNetworkRepository.getPosts()).thenReturn(Single.just(listOf()))
        whenever(postsLocalRepository.deletePosts()).thenReturn(Single.just(true))
        whenever(postsLocalRepository.savePosts(listOf())).thenReturn(Single.just(true))
        whenever(postsLocalRepository.getPosts()).thenReturn(Single.just(listOf()))
        // when
        val result = refreshAndGetPostsUseCase.execute().blockingGet()
        // then
        verify(postsNetworkRepository).getPosts()
        verify(postsLocalRepository).deletePosts()
        verify(postsLocalRepository).savePosts(any())
        assertEquals(0, result.size)
    }

    @Test
    fun `refresh posts network error`() {
        // given
        whenever(postsNetworkRepository.getPosts()).thenReturn(Single.error(TestException()))
        var result: List<Post>? = null
        var error: Exception? = null
        // when
        try {
            result = refreshAndGetPostsUseCase.execute().blockingGet()
        } catch (e: Exception) {
            error = e
        }
        // then
        verify(postsNetworkRepository).getPosts()
        verify(postsLocalRepository, times(0)).deletePosts()
        verify(postsLocalRepository, times(0)).savePosts(any())
        assertNull(result)
        assert(error?.cause is TestException)
    }

    @Test
    fun `refresh posts delete from db error`() {
        // given
        val posts = listOf(
            Post("title 1", 1L, LocalDate.now(), "imageUrl 1", "description 1", "linkUrl 1"),
            Post("title 2", 2L, LocalDate.now(), "imageUrl 2", "description 2", "linkUrl 2")
        )
        whenever(postsNetworkRepository.getPosts()).thenReturn(Single.just(posts))
        whenever(postsLocalRepository.deletePosts()).thenReturn(Single.error(TestException()))
        var result: List<Post>? = null
        var error: Exception? = null
        // when
        try {
            result = refreshAndGetPostsUseCase.execute().blockingGet()
        } catch (e: Exception) {
            error = e
        }
        // then
        verify(postsNetworkRepository).getPosts()
        verify(postsLocalRepository).deletePosts()
        verify(postsLocalRepository, times(0)).savePosts(any())
        assertNull(result)
        assert(error?.cause is TestException)
    }


    @Test
    fun `refresh posts save to db error`() {
        // given
        val posts = listOf(
            Post("title 1", 1L, LocalDate.now(), "imageUrl 1", "description 1", "linkUrl 1"),
            Post("title 2", 2L, LocalDate.now(), "imageUrl 2", "description 2", "linkUrl 2")
        )
        whenever(postsNetworkRepository.getPosts()).thenReturn(Single.just(posts))
        whenever(postsLocalRepository.deletePosts()).thenReturn(Single.just(true))
        whenever(postsLocalRepository.savePosts(posts)).thenReturn(Single.error(TestException()))
        var result: List<Post>? = null
        var error: Exception? = null
        // when
        try {
            result = refreshAndGetPostsUseCase.execute().blockingGet()
        } catch (e: Exception) {
            error = e
        }
        // then
        verify(postsNetworkRepository).getPosts()
        verify(postsLocalRepository).deletePosts()
        verify(postsLocalRepository).savePosts(any())
        assertNull(result)
        assert(error?.cause is TestException)
    }

    @Test
    fun `refresh posts get from db error`() {
        // given
        val posts = listOf(
            Post("title 1", 1L, LocalDate.now(), "imageUrl 1", "description 1", "linkUrl 1"),
            Post("title 2", 2L, LocalDate.now(), "imageUrl 2", "description 2", "linkUrl 2")
        )
        whenever(postsNetworkRepository.getPosts()).thenReturn(Single.just(posts))
        whenever(postsLocalRepository.deletePosts()).thenReturn(Single.just(true))
        whenever(postsLocalRepository.savePosts(posts)).thenReturn(Single.just(true))
        whenever(postsLocalRepository.getPosts()).thenReturn(Single.error(TestException()))
        var result: List<Post>? = null
        var error: Exception? = null
        // when
        try {
            result = refreshAndGetPostsUseCase.execute().blockingGet()
        } catch (e: Exception) {
            error = e
        }
        // then
        verify(postsNetworkRepository).getPosts()
        verify(postsLocalRepository).deletePosts()
        verify(postsLocalRepository).savePosts(any())
        assertNull(result)
        assert(error?.cause is TestException)
    }

    internal class TestException : Exception()
}
