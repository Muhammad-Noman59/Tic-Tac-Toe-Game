package com.example.myapplicationtictactoegame.state_and_user_actions

data class GameStates (
    val playerCircleCount : Int = 0,
    val playerCrossCount : Int = 0,
    val drawCount : Int = 0,
    val currentPlayer : Boolean = true,
    val gameDraw : Boolean = false,
    val currentTurn : BordCellValue = BordCellValue.CIRCLE,
    val winner : String? = null,
    val hasWon : Boolean = false,
    val victoryType : VictoryTypeValue = VictoryTypeValue.NONE
)

enum class BordCellValue{
    CIRCLE,
    CROSS,
    NONE
}

enum class VictoryTypeValue{

    NONE,
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    DIAGONAL1,
    DIAGONAL2
}