package ru.netology.nmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import ru.netology.nmedia.NewPostFragment.Companion.contentArg

class AppActivity : AppCompatActivity(R.layout.activity_app) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            if (it.action != Intent.ACTION_SEND) {
                return@let
            }

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()) {
                findNavController(R.id.nav_main)
                    .navigate(R.id.action_feedFragment_to_newPostFragment, Bundle().apply {
                       contentArg = text
                    })
                return@let
            }

        }

        intent?.let {
            if (it.action != Intent.ACTION_SEND) {
                return@let
            }

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()) {
                findNavController(R.id.nav_main)
                    .navigate(R.id.action_feedFragment_to_actualPostFragment, Bundle().apply {
                        contentArg = text
                    })
                return@let
            }

        }

        intent?.let {
            if (it.action != Intent.ACTION_SEND) {
                return@let
            }

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text.isNullOrBlank()) {
                findNavController(R.id.nav_main)
                    .navigate(R.id.action_feedFragment_to_editPostFragment, Bundle().apply {
                        contentArg = text
                    })
                return@let
            }

        }
    }
}