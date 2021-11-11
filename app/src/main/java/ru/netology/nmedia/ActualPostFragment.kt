package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.ActivityActualPostFragmentBinding
import ru.netology.nmedia.databinding.FragmentNewPostBinding
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
 //       val viewModel: PostViewModel by activityViewModels()
//        val actualId: Long = arguments?.getLong("actaulId") ?: 1
//
//       fun updateToChosen() {
//           viewModel.chosenPost(actualId)
//       }
//        updateToChosen()

        viewModel.data.observe(viewLifecycleOwner) {
           with (binding) {
              author.text = "2"
               content.text = "3"


           }
        }
        return binding.root
    }
}