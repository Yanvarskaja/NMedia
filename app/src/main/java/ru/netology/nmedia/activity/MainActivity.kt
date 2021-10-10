package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.view
import ru.netology.nmedia.viewModel.PostViewModel

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

                if (post.likedByMe) {
                    post.numberLikes++

                } else {
                    post.numberLikes--
                }
                numberLikes.text = view(post.numberLikes)

                binding.likes.setOnClickListener {
                    viewModel.like()
                }


                binding.share.setOnClickListener {
                    post.numberShare++
                    numberShares.text = view(post.numberShare)
          //          viewModel.share()
                }

            }
        }
    }
}









