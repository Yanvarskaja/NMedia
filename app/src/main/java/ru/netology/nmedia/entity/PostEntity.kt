package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
class PostEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val content: String = "",
    val published: String = "",
    val author: String = "",
    val likedByMe: Boolean = false,
    val sharedByMe: Boolean = false,
    val numberLikes: Long = 99L,
    val numberShare: Long = 10L,
    val numberViews: Long = 1000,
    val video: String? = null
        ){

    companion object {
        fun fromDto(dto : Post) : PostEntity = with(dto) {
            PostEntity(
                id = id,
                content = content,
                published = published,
                author = author,
                likedByMe = likedByMe,
                sharedByMe = sharedByMe,
                numberLikes = numberLikes,
                numberShare = numberShare,
                numberViews = numberViews,
                video = video
            )
        }
    }


    fun toDto(): Post = with(this) {
        Post(
            id = id,
            content = content,
            published = published,
            author = author,
            likedByMe = likedByMe,
            sharedByMe = sharedByMe,
            numberLikes = numberLikes,
            numberShare = numberShare,
            numberViews = numberViews,
            video = video
        )
    }
}