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
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.utils.StringArg
import ru.netology.nmedia.viewModel.PostViewModel


class NewPostFragment : Fragment() {

    companion object {
        var Bundle.contentArg by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentNewPostBinding.inflate(layoutInflater)

  //      setContentView(binding.root)
        binding.content.requestFocus()
        arguments?.contentArg?.also {
            binding.content.setText(it)
        }
        binding.save.setOnClickListener {
       //     val content =  binding.content.text?.toString()
            val  result = binding.content.text
            if (!result.isNullOrBlank()) {
            //    setResult((Activity.RESULT_CANCELED))
           // } else {
                viewModel.changeContent(result.toString())
                viewModel.save()
                AndroidUtils.hideKeyboard(binding.root)
//                val intent = Intent()
//                intent.putExtra(Intent.EXTRA_TEXT, content)
//                setResult(Activity.RESULT_OK, intent)
            }
        //    finish()
            findNavController().navigateUp()
                        }
        return binding.root
    }



}