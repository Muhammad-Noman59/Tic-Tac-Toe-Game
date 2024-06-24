package com.example.myapplicationtictactoegame.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplicationtictactoegame.state_and_user_actions.BordCellValue
import com.example.myapplicationtictactoegame.state_and_user_actions.GameStates
import com.example.myapplicationtictactoegame.state_and_user_actions.UserActions
import com.example.myapplicationtictactoegame.state_and_user_actions.VictoryTypeValue

class GameViewModel : ViewModel() {

    var state by mutableStateOf(GameStates())

    val boardItems: MutableMap<Int, BordCellValue> = mutableMapOf(

        1 to BordCellValue.NONE,
        2 to BordCellValue.NONE,
        3 to BordCellValue.NONE,
        4 to BordCellValue.NONE,
        5 to BordCellValue.NONE,
        6 to BordCellValue.NONE,
        7 to BordCellValue.NONE,
        8 to BordCellValue.NONE,
        9 to BordCellValue.NONE
    )


    fun onAction(actions: UserActions) {

        when (actions) {
            is UserActions.BoardTaped -> {

                addValuesToBoard(actions.cellNo)
            }

            UserActions.PlayAgainButtonClicked -> {
                gameReset()
            }
        }
    }

    private fun gameReset() {
        boardItems.forEach { (i, boardCellValue) ->

            boardItems[i] = BordCellValue.NONE
        }

        state = state.copy(
            currentPlayer = true,
            currentTurn = BordCellValue.CIRCLE,
            hasWon = false,
            gameDraw = false,
            victoryType = VictoryTypeValue.NONE
        )
    }

    private fun addValuesToBoard(cellNo: Int) {

        if (boardItems[cellNo] != BordCellValue.NONE) {
            return
        }

        if (state.currentTurn == BordCellValue.CIRCLE) {
            boardItems[cellNo] = BordCellValue.CIRCLE

            if (checkForVictory(BordCellValue.CIRCLE)){

                state = state.copy(

                    winner = "Player 'O' is Winner 😍",
                    playerCircleCount = state.playerCircleCount +1,
                    currentTurn = BordCellValue.NONE,
                    hasWon = true
                )
            }else if (hesBoardFull()) {

                    state = state.copy(
                        gameDraw = true,
                        drawCount = state.drawCount + 1
                    )
                } else {
                    state = state.copy(
                        currentPlayer = false,
                        currentTurn = BordCellValue.CROSS
                    )
                }

        } else if (state.currentTurn == BordCellValue.CROSS) {

            boardItems[cellNo] = BordCellValue.CROSS

            if (checkForVictory(BordCellValue.CROSS)){

                state = state.copy(

                    winner = "Player 'X' is Winner 😍",
                    playerCrossCount = state.playerCrossCount +1,
                    currentTurn = BordCellValue.NONE,
                    hasWon = true
                )
            }else if (hesBoardFull()) {

                state = state.copy(
                    gameDraw = true,
                    drawCount = state.drawCount + 1
                )
            } else {

                state = state.copy(
                    currentPlayer = true,
                    currentTurn = BordCellValue.CIRCLE
                )
            }

        }
    }

    private fun checkForVictory(boardValue: BordCellValue): Boolean {

        when {
            boardItems[1] == boardValue && boardItems[2] == boardValue && boardItems[3] == boardValue -> {

                state = state.copy(
                    victoryType = VictoryTypeValue.HORIZONTAL1
                )
                return true
            }

            boardItems[4] == boardValue && boardItems[5] == boardValue && boardItems[6] == boardValue -> {

                state = state.copy(
                    victoryType = VictoryTypeValue.HORIZONTAL2
                )
                return true
            }


            boardItems[7] == boardValue && boardItems[8] == boardValue && boardItems[9] == boardValue -> {

                state = state.copy(
                    victoryType = VictoryTypeValue.HORIZONTAL3
                )
                return true
            }


            boardItems[1] == boardValue && boardItems[4] == boardValue && boardItems[7] == boardValue -> {

                state = state.copy(
                    victoryType = VictoryTypeValue.VERTICAL1
                )
                return true
            }


            boardItems[2] == boardValue && boardItems[5] == boardValue && boardItems[8] == boardValue -> {

                state = state.copy(
                    victoryType = VictoryTypeValue.VERTICAL2
                )
                return true
            }


            boardItems[3] == boardValue && boardItems[6] == boardValue && boardItems[9] == boardValue -> {

                state = state.copy(
                    victoryType = VictoryTypeValue.VERTICAL3
                )
                return true
            }


            boardItems[1] == boardValue && boardItems[5] == boardValue && boardItems[9] == boardValue -> {

                state = state.copy(
                    victoryType = VictoryTypeValue.DIAGONAL1
                )
                return true
            }


            boardItems[3] == boardValue && boardItems[5] == boardValue && boardItems[7] == boardValue -> {

                state = state.copy(
                    victoryType = VictoryTypeValue.DIAGONAL2
                )
                return true
            }

            else -> return false
        }

    }

    private fun hesBoardFull(): Boolean {

        if (boardItems.containsValue(BordCellValue.NONE)) return false
        return true
    }
}