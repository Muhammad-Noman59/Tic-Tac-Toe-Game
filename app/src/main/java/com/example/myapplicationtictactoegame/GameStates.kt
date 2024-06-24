package com.example.myapplicationtictactoegame

data class GameStates (
    val playerCircleCount : Int = 0,
    val playerCrossCount : Int = 0,
    val drawCount : Int = 0,
    val hintText : String = "Player 'O' Turn",
    val currentTurn : BordCellValue = BordCellValue.CIRCLE,
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