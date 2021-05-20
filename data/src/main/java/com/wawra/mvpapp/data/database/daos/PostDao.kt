package com.wawra.mvpapp.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wawra.mvpapp.data.database.models.PostEntity
import io.reactivex.Single

@Dao
interface PostDao {

    @Query("DELETE FROM post")
    fun deleteAll(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostEntity>): Single<List<Long>>

    @Query("SELECT * FROM post")
    fun getPosts(): Single<List<PostEntity>>
}
