package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.R
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.dto.Post
import java.io.File


class PostRepositoryFileImpl(
    private val context: Context
  //  private val dao: PostDao
) : PostRepository {

    private val gson = Gson()
    private val prefs = context.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
     private val key = "posts"
    private val filename = "posts.json"



    private var posts = emptyList<Post>()
//
    private val data = MutableLiveData(posts)

    init {
        val file: File = context.filesDir.resolve(filename)

        if (file.exists()) {
            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, type)
                data.value = posts
            }
        }
//        posts = dao.getAll()
//        data.value = posts
    }

    override fun getAll(): LiveData<List<Post>> = data

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(post.copy(id = posts.firstOrNull()?.id?.inc()?: + 1)) + posts.orEmpty()
            data.value = posts
            sync()
            return
        }
        data.value = data.value?.map {
            if (it.id == post.id) {
                it.copy( content = post.content)
            } else {
                it
            }
        }
        sync()
//        val id = post.id
//        val saved = dao.save(post)
//        posts = if (id == 0L) {
//            listOf(saved) + posts
//        } else {
//            posts.map {
//                if (it.id != id) it else saved
//            }
//        }
//        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
        sync()
    }

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                numberLikes = if (it.likedByMe) it.numberLikes-1 else it.numberLikes+1)
        }
        data.value = posts
        sync()
    }


    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(numberShare = it.numberShare+1)
        }
        data.value = posts
        sync()
    }


    private fun sync() {
        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(data.value))
        }
    }
}
