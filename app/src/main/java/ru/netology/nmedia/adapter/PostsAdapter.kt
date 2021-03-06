package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.activity.result.launch
import androidx.core.view.isGone
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.view

interface OnActionListener {
    fun onLikeClicked (post: Post) = Unit
    fun onShareClicked (post: Post) = Unit
    fun onRemoveClicked (post: Post) = Unit
    fun onEditClicked (post: Post) = Unit
    fun onShare (post: Post) = Unit
    fun onVideoClicked (post: Post) = Unit
    fun onContentClicked (post: Post) = Unit
}

class PostsAdapter(
    private val actionListener: OnActionListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(
            binding,
        actionListener
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        with(holder) {
            holder.bind(post)
        }
    }

}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val actionListener: OnActionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            avatar.setImageResource(R.drawable.ic_netology_48dp)
            author.text = post.author
            published.text = post.published
            content.text = post.content
           // numberLikes.text = view(post.numberLikes)
          //  numberShares.text = view(post.numberShare)
          //  numberViews.text = view(post.numberViews)
            likes.isChecked = post.likedByMe
            likes.text = "${view(post.numberLikes)}"
            share.text = "${view(post.numberShare)}"
            views.text = "${view(post.numberViews)}"
//            likes.setImageResource(
//                if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
//            )
            likes.setOnClickListener{
                actionListener.onLikeClicked(post)
            }
            share.setOnClickListener{
                actionListener.onShare(post)
            }
            video.isGone = post.video == null
            video.setOnClickListener {
                actionListener.onVideoClicked(post)
            }
            content.setOnClickListener {
                actionListener.onContentClicked(post)
            }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_menu)
                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.menu_remove -> {
                                actionListener.onRemoveClicked(post)
                                true
                            }
                            R.id.menu_edit -> {
                                actionListener.onEditClicked(post)
                                true
                            }
                            else -> false
                        }
                    }
                    show()
                }
            }
        }

    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}