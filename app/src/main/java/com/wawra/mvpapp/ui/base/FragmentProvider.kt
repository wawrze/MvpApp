package com.wawra.mvpapp.ui.base

import android.os.Bundle
import com.wawra.mvpapp.ui.postdetails.PostDetailsFragment
import com.wawra.mvpapp.ui.postdetails.PostDetailsFragment.Companion.LINK_URL_KEY
import com.wawra.mvpapp.ui.posts.PostsFragment
import javax.inject.Inject

class FragmentProvider @Inject constructor() {
    fun providePostsFragment() = PostsFragment()
    fun providePostDetailsFragment(linkUrl: String) = PostDetailsFragment().apply {
        arguments = Bundle().apply { putString(LINK_URL_KEY, linkUrl) }
    }
}
