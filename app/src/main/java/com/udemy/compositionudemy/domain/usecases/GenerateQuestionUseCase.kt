package com.udemy.compositionudemy.domain.usecases

import com.udemy.compositionudemy.domain.entity.Question
import com.udemy.compositionudemy.domain.repository.GameRepository

class GenerateQuestionUseCase(
    val repository: GameRepository
) {
    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}