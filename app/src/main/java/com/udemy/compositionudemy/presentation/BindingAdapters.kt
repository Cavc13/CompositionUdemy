package com.udemy.compositionudemy.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.udemy.compositionudemy.R
import com.udemy.compositionudemy.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = "Необходимое количество правильных ответов: $count"
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, score: Int) {
    textView.text = "Ваш счет правильных ответов: $score"
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percent: Int) {
    textView.text = "Необходимый процент правильных ответов: $percent"
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    var percent = 0
    with(gameResult) {
        percent = if (countOfQuestion == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestion.toDouble()) * 100).toInt()
        }
    }

    textView.text = "Необходимое количество правильных ответов: $percent"
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, win: Boolean) {
    val resId = if (win) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
    imageView.setImageResource(resId)
}