package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepositiry {
    val data: LiveData<Post>
    fun like()
  //  fun share()
}