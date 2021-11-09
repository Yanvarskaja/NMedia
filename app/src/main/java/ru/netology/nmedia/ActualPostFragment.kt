package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = ActivityActualPostFragmentBinding.inflate(layoutInflater)
        val viewModel: PostViewModel by activityViewModels()
        val actualId: Long = arguments?.getLong("actaulId") ?: 1

        viewModel.data.observe(viewLifecycleOwner) {

        }
        return binding.root
    }
}