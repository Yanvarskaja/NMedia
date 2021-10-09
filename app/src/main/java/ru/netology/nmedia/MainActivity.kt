package ru.netology.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.view

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            author = "Нетология. Университет интернет-профессий будущего"
        )
        with(binding) {
            avatar.setImageResource(R.drawable.ic_netology_48dp)
            content.text = post.content
            author.text = post.author
            published.text = post.published
            numberLikes.text = view(post.numberLikes)

            likes.setOnClickListener {
                post.likedByMe = !post.likedByMe
                likes.setImageResource(
                    if (post.likedByMe) {
                        R.drawable.ic_baseline_favorite_24
                    } else {
                        R.drawable.ic_baseline_favorite_border_24
                    }
                )
                if (post.likedByMe) {
                    post.numberLikes++
                    numberLikes.text = view(post.numberLikes)
                } else {
                    post.numberLikes--
                    numberLikes.text = view(post.numberLikes)
                }

            }

            share.setOnClickListener {
                post.share = !post.share
                R.drawable.ic_baseline_share_24
                if (post.share) {
                    post.numberShare++
                    numberShares.text = view(post.numberShare)


                }
            }
        }
    }
}






