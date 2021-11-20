package ru.netology.nmedia.viewModel

import android.app.Application
import android.content.Context
import androidx.activity.result.launch
import androidx.lifecycle.*
import ru.netology.nmedia.db.AppDb
//import ru.netology.nmedia.EditPostActivityContract
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
//import ru.netology.nmedia.repository.PostRepositoryFileImpl
import ru.netology.nmedia.repository.PostRepositorySQLiteImpl
import ru.netology.nmedia.repository.PostRepositorySharedPrefsImpl

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    numberLikes= 0,
    published = ""
)

class PostViewModel(application: Application): AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositorySQLiteImpl(
        AppDb.getInstance(application).postDao
    )

    val edited = MutableLiveData(empty)
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)

    fun changeContent(content: String) {
        edited.value = edited.value?.copy(content = content)
    }

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
            edited.value = empty
    }

    fun edit (post: Post) {
        edited.value = post

    }

        fun chosenPost(id: Long): LiveData<Post?> = data.map { posts ->
            posts.find {it.id == id}
        }

    }

