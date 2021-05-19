package com.wawra.mvpapp.ui.base

import com.wawra.mvpapp.ui.posts.PostsFragment
import javax.inject.Inject

class FragmentProvider @Inject constructor() {
    fun providePostsFragment() = PostsFragment()
}
