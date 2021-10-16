package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.view

typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostsAdapter(private val onLikeListener: OnLikeListener, val onShareListener: OnShareListener) : RecyclerView.Adapter<PostViewHolder>() {
    var posts = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        with(holder) {
            bind(post)
        }
    }

    override fun getItemCount(): Int = posts.size
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            avatar.setImageResource(R.drawable.ic_netology_48dp)
            author.text = post.author
            published.text = post.published
            content.text = post.content
            numberLikes.text = view(post.numberLikes)
            numberShares.text = view(post.numberShare)
            numberViews.text = view(post.numberViews)
            likes.setImageResource(
                if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            )
            if (post.likedByMe) {
                likes.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            likes.setOnClickListener{
                onLikeListener(post)
            }
            share.setOnClickListener{
                onShareListener(post)
            }


        }
    }
}
