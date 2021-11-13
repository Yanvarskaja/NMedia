package ru.netology.nmedia

import android.app.Activity
import android.content.Intent
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
import ru.netology.nmedia.databinding.FragmentEditPostBinding
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.utils.StringArg
import ru.netology.nmedia.viewModel.PostViewModel


class EditPostFragment : Fragment() {
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditPostBinding.inflate(layoutInflater)
   //     val viewModel: PostViewModel by activityViewModels()
            binding.content.setText(viewModel.edited.value?.content)
            binding.content.requestFocus()

            binding.save.setOnClickListener {
                val content = binding.content.text
                if (!content.isNullOrBlank()) {

                    viewModel.changeContent(content.toString())
                    viewModel.save()
                    AndroidUtils.hideKeyboard(binding.root)
                }
                findNavController().navigateUp()
            }
            return binding.root

    }
}

 //   override fun onCreate(savedInstanceState: Bundle?) {
 //       super.onCreate(savedInstanceState)
//        val binding = ActivityEditPostBinding.inflate(layoutInflater)
//      //  setContentView(binding.root)
//
//        binding.content.setText(intent.getStringExtra(Intent.EXTRA_TEXT))
//        binding.content.requestFocus()
//
//        binding.save.setOnClickListener {
//            val content =  binding.content.text?.toString()
//
//            if (content.isNullOrBlank()) {
//                setResult((Activity.RESULT_CANCELED))
//            } else {
//                val intent = Intent()
//                intent.putExtra(Intent.EXTRA_TEXT, content)
//                setResult(Activity.RESULT_OK, intent)
//            }
//            finish()
  //      }

   // }
//}