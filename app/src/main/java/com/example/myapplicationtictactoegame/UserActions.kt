package com.example.myapplicationtictactoegame

sealed class UserActions {

    object PlayAgainButtonClicked : UserActions()

    data class BoardTaped(val cellNo : Int) : UserActions()

}