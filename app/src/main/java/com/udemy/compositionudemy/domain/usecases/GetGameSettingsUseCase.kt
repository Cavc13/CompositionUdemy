package com.udemy.compositionudemy.domain.usecases

import com.udemy.compositionudemy.domain.entity.GameSettings
import com.udemy.compositionudemy.domain.entity.Level
import com.udemy.compositionudemy.domain.repository.GameRepository

class GetGameSettingsUseCase(
    val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}