package ru.netology.nmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import ru.netology.nmedia.NewPostFragment.Companion.contentArg
import ru.netology.nmedia.viewModel.PostViewModel

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

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            println(it)
        }
    }
//    private fun checkGoogleApiAvailability() {
//        with(GoogleApiAvailability.getInstance()) {
//            val code = isGooglePlayServicesAvailable(this@AppActivity)
//            if (code == ConnectionResult.SUCCESS) {
//                return@with
//            }
//            if (isUserResolvableError(code)) {
//                getErrorDialog(this@AppActivity, code, 9000).show()
//                return
//            }
//            Toast.makeText(this@AppActivity, R.string.google_play_unavailable, Toast.LENGTH_LONG)
//                .show()
//        }
//
//        FirebaseMessaging.getInstance().token.addOnSuccessListener {
//            println(it)
//        }
 //   }


}
