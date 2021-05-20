package com.wawra.mvpapp.domain.repositories

import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.wawra.mvpapp.BaseTests
import com.wawra.mvpapp.TestException
import com.wawra.mvpapp.data.database.daos.PostDao
import com.wawra.mvpapp.data.database.models.PostEntity
import com.wawra.mvpapp.data.database.repositories.PostsLocalRepositoryImpl
import com.wawra.mvpapp.domain.FORMATTER
import com.wawra.mvpapp.domain.models.Post
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import java.time.LocalDate

class PostsLocalRepositoryTests : BaseTests() {

    @Captor
    lateinit var postsListCaptor: ArgumentCaptor<List<PostEntity>>

    private lateinit var postsDao: PostDao
    private lateinit var postsLocalRepository: PostsLocalRepositoryImpl

    @Before
    fun setup() {
        postsDao = mock()
        postsLocalRepository = PostsLocalRepositoryImpl(postsDao)
    }

    @Test
    fun `save posts success`() {
        // given
        val posts = listOf(
            Post("title 1", 1L, LocalDate.now(), "imageUrl 1", "description 1", "linkUrl 1"),
            Post("title 2", 2L, LocalDate.now(), "imageUrl 2", "description 2", "linkUrl 2")
        )
        whenever(postsDao.insertPosts(capture(postsListCaptor))).thenReturn(
            Single.just(
                listOf(
                    1L,
                    2L
                )
            )
        )
        // when
        val result = postsLocalRepository.savePosts(posts).blockingGet()
        // then
        assert(result)
        assertEquals(posts.size, postsListCaptor.value.size)
        assertEquals(posts[0].orderId, postsListCaptor.value[0].orderId)
        assertEquals(posts[1].orderId, postsListCaptor.value[1].orderId)
        assertEquals(posts[1].title, postsListCaptor.value[1].title)
        assertEquals(
            FORMATTER.format(posts[1].modificationDate),
            postsListCaptor.value[1].modificationDate
        )
        assertEquals(posts[1].imageUrl, postsListCaptor.value[1].imageUrl)
        assertEquals(posts[1].description, postsListCaptor.value[1].description)
        assertEquals(posts[1].linkUrl, postsListCaptor.value[1].linkUrl)
    }

    @Test
    fun `save posts empty list success`() {
        // given
        val posts = listOf<Post>()
        whenever(postsDao.insertPosts(capture(postsListCaptor))).thenReturn(Single.just(listOf()))
        // when
        val result = postsLocalRepository.savePosts(posts).blockingGet()
        // then
        assert(result)
        assertEquals(0, postsListCaptor.value.size)
    }

    @Test
    fun `save posts not all saved`() {
        // given
        val posts = listOf(
            Post("title 1", 1L, LocalDate.now(), "imageUrl 1", "description 1", "linkUrl 1"),
            Post("title 2", 2L, LocalDate.now(), "imageUrl 2", "description 2", "linkUrl 2")
        )
        whenever(postsDao.insertPosts(capture(postsListCaptor))).thenReturn(Single.just(listOf(1L)))
        // when
        val result = postsLocalRepository.savePosts(posts).blockingGet()
        // then
        assertFalse(result)
    }

    @Test
    fun `save posts error`() {
        // given
        val posts = listOf(
            Post("title 1", 1L, LocalDate.now(), "imageUrl 1", "description 1", "linkUrl 1"),
            Post("title 2", 2L, LocalDate.now(), "imageUrl 2", "description 2", "linkUrl 2")
        )
        whenever(postsDao.insertPosts(capture(postsListCaptor))).thenReturn(
            Single.error(
                TestException()
            )
        )
        var result: Boolean? = null
        var error: Exception? = null
        // when
        try {
            result = postsLocalRepository.savePosts(posts).blockingGet()
        } catch (e: Exception) {
            error = e
        }
        // then
        assertNull(result)
        assert(error?.cause is TestException)
    }
}
