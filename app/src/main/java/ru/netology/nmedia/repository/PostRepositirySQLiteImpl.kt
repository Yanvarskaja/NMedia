package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.R
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.entity.PostEntity
import java.io.File


class PostRepositorySQLiteImpl(
   private val dao: PostDao
) : PostRepository {

    override fun getAll(): LiveData<List<Post>> = dao.getAll()
        .map { it.map(PostEntity::toDto) }

    override fun save(post: Post) {
       dao.insert(PostEntity.fromDto(post))
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
    }

    override fun likeById(id: Long) {
        dao.likeById(id)

    }


    override fun shareById(id: Long) {
        dao.shareById(id)
    }
}
