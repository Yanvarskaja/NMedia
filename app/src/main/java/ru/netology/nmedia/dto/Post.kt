package ru.netology.nmedia.dto

data class Post (
    val id: Long = 0L,
    val content: String = "",
    val published: String = "",
    val author: String = "",
    var likedByMe: Boolean = false,
    var numberLikes: Long = 999999L,
    var numberShare: Long = 10L,
    var view:Boolean = false,
    var numberViews: Long = 1000

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