package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewModel.PostViewModel
import ru.netology.nmedia.dto.view


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                avatar.setImageResource(R.drawable.ic_netology_48dp)
                content.text = post.content
                author.text = post.author
                published.text = post.published
                numberLikes.text = view(post.numberLikes)
                numberShares.text = view(post.numberShare)

                likes.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.ic_baseline_favorite_24
                    } else {
                        R.drawable.ic_baseline_favorite_border_24
                    }
                )

                binding.likes.setOnClickListener {
                    viewModel.like()
                }


                binding.share.setOnClickListener {
                  numberShares.text = view(post.numberShare)
                   viewModel.share()
                }

            }
        }
    }
}



        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter (
            onLikeListener = {viewModel.likeById(it.id)},
            onShareListener= {viewModel.shareById(it.id)}
        )
        binding.posts.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.posts = posts
        }
    }
}









