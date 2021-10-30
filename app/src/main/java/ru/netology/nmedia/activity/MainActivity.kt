package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import ru.netology.nmedia.EditPostActivityContract
import ru.netology.nmedia.NewPostActivityContract
import ru.netology.nmedia.adapter.OnActionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewModel.PostViewModel
import ru.netology.nmedia.dto.Post

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

                override fun onShare(post: Post) {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                    val chooser = Intent.createChooser(intent, null)
                    startActivity(chooser)
                }

                override fun onVideoClicked(post: Post) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    val chooser = Intent.createChooser(intent, null)
                    startActivity(chooser)
                }
            }
        )
        binding.posts.adapter = adapter
        viewModel.data.observe(this, adapter::submitList)

        val launcherNewPost = registerForActivityResult(NewPostActivityContract()) { text ->
            text ?: return@registerForActivityResult
            viewModel.changeContent(text.toString())
            viewModel.save()
        }
        binding.newPost.setOnClickListener {
            launcherNewPost.launch()
        }

        val launcherEditPost = registerForActivityResult(EditPostActivityContract()) { text ->
            text ?: return@registerForActivityResult
            viewModel.changeContent(text.toString())
            viewModel.save()
        }
        viewModel.edited.observe(this) {
            if (it.id == 0L) {
             return@observe
            }
            launcherEditPost.launch(it.content)
        }


//            binding.cancelEditing.visibility = View.VISIBLE //отобразить
//            binding.content.setText(it.content)
//            binding.content.requestFocus()
//        }
//
//        binding.save.setOnClickListener {
//
//            with(binding.content) {
//
//                if (text.isNullOrBlank()) {
//                    Toast.makeText(context, "Content must not be empty", Toast.LENGTH_SHORT).show()
//                    return@setOnClickListener
//
//                }
//                viewModel.changeContent(text.toString())
//                viewModel.save()
//
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(this)
//         //       binding.cancelEditing.visibility = View.GONE //перестает занимать место на экране
//            }
//        }
//        binding.cancelEditing.visibility = View.GONE //перестает занимать место на экране
//        binding.cancelEditing.setOnClickListener {
//            with(binding.content) {
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(this)
//
//             binding.cancelEditing.visibility = View.GONE //перестает занимать место на экране
//            }
        //      }

    }
}





