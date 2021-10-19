package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.OnActionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewModel.PostViewModel
import ru.netology.nmedia.dto.view
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            object : OnActionListener {
                override fun onEditClicked(post: Post) {
                    viewModel.edit(post)
                }

                override fun onRemoveClicked(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onLikeClicked(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onShareClicked(post: Post) {
                    viewModel.shareById(post.id)
                }

                override fun onCancelEditingClicked(post: Post) {
                    viewModel.cancelEditing(post)
                }
            }
        )
        binding.posts.adapter = adapter
        viewModel.data.observe(this, adapter::submitList)

    binding.group.visibility = View.GONE //перестает занимать место на экране
        viewModel.edited.observe(this) {
            if (it.id == 0L) {
                return@observe
            }
            binding.content.setText(it.content)
            binding.content.requestFocus()
            binding.group.visibility = View.VISIBLE //отобразить
        }

        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(context, "Content must not be empty", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.group.visibility = View.GONE //перестает занимать место на экране
            }
        }

        binding.cancelEditing.setOnClickListener {
            with(binding.content) {
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.group.visibility = View.GONE //перестает занимать место на экране
            }
        }

    }
}




