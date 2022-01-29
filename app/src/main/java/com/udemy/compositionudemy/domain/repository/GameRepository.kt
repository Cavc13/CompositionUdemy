package com.udemy.compositionudemy.domain.repository

import com.udemy.compositionudemy.domain.entity.GameSettings
import com.udemy.compositionudemy.domain.entity.Level
import com.udemy.compositionudemy.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}