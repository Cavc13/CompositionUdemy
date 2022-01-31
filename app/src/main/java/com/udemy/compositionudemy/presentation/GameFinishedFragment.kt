package com.udemy.compositionudemy.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.udemy.compositionudemy.R
import com.udemy.compositionudemy.databinding.FragmentGameFinishedBinding
import com.udemy.compositionudemy.domain.entity.GameResult
import java.lang.RuntimeException

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("binding from FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupClickListener() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBindViews()
        setupClickListener()
    }

    private fun onBindViews(){
        with(binding) {
            emojiResult.setImageResource(getSmileResId())
            tvRequiredAnswers.text = "Необходимое количество правильных ответов: " +
                    "${gameResult.gameSettings.minCountOfRightAnswers}"
            tvScoreAnswers.text = "Ваш счет правильных ответов: ${gameResult.countOfRightAnswers}"
            tvRequiredPercentage.text = "Необходимый процент правильных ответов: " +
                    "${gameResult.gameSettings.minPercentOfRightAnswers}"
            tvScorePercentage.text = "Процент правильных ответов: " +
             "${getPercentOfRightAnswers()}"
        }
    }

    private fun getPercentOfRightAnswers() = with(gameResult) {
        if (countOfQuestion == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestion.toDouble()) * 100).toInt()
        }
    }

    private fun getSmileResId(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager
            .popBackStack(GameFragment.NAME, POP_BACK_STACK_INCLUSIVE)
    }

    companion object {

        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}