package ru.netology.nmedia

import android.app.ProgressDialog.show
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.ActivityActualPostFragmentBinding
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.dto.view
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.utils.StringArg
import ru.netology.nmedia.viewModel.PostViewModel

class ActualPostFragment : Fragment(R.layout.activity_actual_post_fragment) {

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ActivityActualPostFragmentBinding.inflate(layoutInflater)
        val viewModel: PostViewModel by activityViewModels()
        val actualId: Long = arguments?.getLong("actaulId") ?: 0

        viewModel.chosenPost(actualId).observe(viewLifecycleOwner) { post ->
            post?: run {
                findNavController().navigateUp()
                return@observe
            }
            with(binding) {
                author.text = post.author
                content.text = post.content
                avatar
                published.text = post.published
                likes.text = view(post.numberLikes)
                share.text = view(post.numberShare)
                views.text = view(post.numberViews)
                likes.isChecked = post.likedByMe

                likes.setOnClickListener {
                    viewModel.likeById(post.id)
                }

                if (post.video == null) video.isGone
                video.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    val chooser = Intent.createChooser(intent, null)
                    startActivity(chooser)
                }

                share.setOnClickListener {
                    viewModel.shareById(post.id)
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }
                    val chooser = Intent.createChooser(intent, null)
                    startActivity(chooser)
                }

                menu.setOnClickListener {
                    PopupMenu(it.context, it).apply {
                        inflate(R.menu.post_menu)
                        setOnMenuItemClickListener { menuItem ->
                            when (menuItem.itemId) {
                                R.id.menu_remove -> {
                                    viewModel.removeById(post.id)
                                    true
                                }
                                R.id.menu_edit -> {
                                    viewModel.edit(post)
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
        return binding.root
    }
}