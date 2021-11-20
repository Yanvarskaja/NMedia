package ru.netology.nmedia.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.netology.nmedia.entity.PostEntity
import java.io.Closeable

@Dao
interface PostDao {
    @Query("SELECT * FROM PostEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<PostEntity>>

    @Insert
    fun insert(post: PostEntity)

    @Query("UPDATE PostEntity SET content = :content WHERE id = :postId")
    fun updateContentById(postId: Long, content: String)

    fun save(post: PostEntity) {
        if (post.id == 0L) insert(post) else updateContentById(postId = post.id, content = post.content)
    }

   // fun save(post: Post): Post

    @Query(
        """
           UPDATE PostEntity SET
               numberLikes = numberLikes + CASE WHEN likedByMe THEN -1 ELSE 1 END,
               likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
           WHERE id = :id;
        """
    )
    fun likeById(id: Long)

    @Query(
        """
           UPDATE PostEntity SET
              numberShare = numberShare + CASE WHEN sharedByMe THEN -1 ELSE 1 END,
               likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
           WHERE id = :id;
        """
    )
    fun shareById(id: Long)

    @Query("DELETE FROM PostEntity WHERE id = :id")
    fun removeById(id: Long)
}
