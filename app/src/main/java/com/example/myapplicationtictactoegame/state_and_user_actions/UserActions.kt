package com.example.myapplicationtictactoegame.state_and_user_actions

sealed class UserActions {

    object PlayAgainButtonClicked : UserActions()

    data class BoardTaped(val cellNo : Int) : UserActions()

}