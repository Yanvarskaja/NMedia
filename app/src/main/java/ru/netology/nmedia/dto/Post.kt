package ru.netology.nmedia.dto

import android.widget.VideoView

data class Post (
    val id: Long = 0L,
    val content: String = "",
    val published: String = "",
    val author: String = "",
    val likedByMe: Boolean = false,
    val numberLikes: Long = 99L,
    val numberShare: Long = 10L,
    val numberViews: Long = 1000,
    val video: String? = null


)

fun view (count : Long) : String {
    when {
        count in 1000..9999 -> return (count/1000).toString() + "." + (count % 1000 / 100).toString() +"K"
        count in 10_000..999_999 -> return (count/1000).toString() + "K"
        count in 1_000_000..999_999_999 -> return (count/1_000_000).toString() + "M"
        else -> return count.toString()
    }
    return count.toString()
}