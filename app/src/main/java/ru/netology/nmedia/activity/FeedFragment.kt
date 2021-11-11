package ru.netology.nmedia.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
//import ru.netology.nmedia.EditPostActivityContract
//import ru.netology.nmedia.NewPostActivityContract
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnActionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.viewModel.PostViewModel
import ru.netology.nmedia.dto.Post

class FeedFragment : Fragment() {
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)


        //   val viewModel: PostViewModel by activityViewModels()
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

                override fun onContentClicked(post: Post) {
                    findNavController().navigate(R.id.action_feedFragment_to_actualPostFragment, bundleOf("id" to post.id))

                }
            })
        binding.posts.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner, adapter::submitList)


//        val launcherNewPost = registerForActivityResult(NewPostActivityContract()) { text ->
//            text ?: return@registerForActivityResult
//
//        }
        binding.newPost.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
        }



//        val launcherEditPost = registerForActivityResult(EditPostActivityContract()) { text ->
//            text ?: return@registerForActivityResult
//            viewModel.changeContent(text.toString())
//            viewModel.save()
//        }
//        viewModel.edited.observe(viewLifecycleOwner) {
//            if (it.id == 0L) {
//             return@observe
//            }
//            launcherEditPost.launch(it.content)
//        }
//        return binding.root
//    }
        return binding.root
    }

}






