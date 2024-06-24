package com.example.myapplicationtictactoegame.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationtictactoegame.components.Circle
import com.example.myapplicationtictactoegame.components.Cross
import com.example.myapplicationtictactoegame.components.WinDiagonalLine1
import com.example.myapplicationtictactoegame.components.WinDiagonalLine2
import com.example.myapplicationtictactoegame.components.WinHorizontalLine1
import com.example.myapplicationtictactoegame.components.WinHorizontalLine2
import com.example.myapplicationtictactoegame.components.WinHorizontalLine3
import com.example.myapplicationtictactoegame.components.WinVerticalLine1
import com.example.myapplicationtictactoegame.components.WinVerticalLine2
import com.example.myapplicationtictactoegame.components.WinVerticalLine3
import com.example.myapplicationtictactoegame.state_and_user_actions.BordCellValue
import com.example.myapplicationtictactoegame.state_and_user_actions.GameStates
import com.example.myapplicationtictactoegame.presentation.view_model.GameViewModel
import com.example.myapplicationtictactoegame.state_and_user_actions.UserActions
import com.example.myapplicationtictactoegame.state_and_user_actions.VictoryTypeValue
import com.example.myapplicationtictactoegame.ui.theme.BlueCustom
import com.example.myapplicationtictactoegame.ui.theme.GrayBackground

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameScreen(
    viewModel: GameViewModel = GameViewModel()
) {

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 32.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Tic Tac Toe",
            style = TextStyle(
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            ),
            color = BlueCustom
        )

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TopStatesBox(
                heading = "Player : O",
                count = state.playerCircleCount.toString()
            )

            TopStatesBox(
                heading = "Draw",
                count = state.drawCount.toString()
            )

            TopStatesBox(
                heading = "Player : X",
                count = state.playerCrossCount.toString()
            )


        }

        Spacer(Modifier.height(24.dp))

        if (state.hasWon || state.gameDraw){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(5.dp)
                    ),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = if (state.gameDraw) "Game Draw 😅" else state.winner.toString(),
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            }

            Spacer(Modifier.height(24.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(22.dp)
                )
                .clip(
                    shape = RoundedCornerShape(22.dp)
                )
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {
            BoardBase()

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f)

            ) {
                viewModel.boardItems.forEach { (cellNo, boardCellValue) ->
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {
                                    viewModel.onAction(
                                        UserActions.BoardTaped(
                                            cellNo = cellNo
                                        )
                                    )
                                },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AnimatedVisibility(
                                visible = viewModel.boardItems[cellNo] != BordCellValue.NONE,
                                enter = scaleIn(tween(1000))
                            ) {

                                if (boardCellValue == BordCellValue.CIRCLE) {
                                    Circle()
                                } else if (boardCellValue == BordCellValue.CROSS) {
                                    Cross()
                                }
                            }

                        }
                    }

                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = state.hasWon,
                    enter = fadeIn(tween(2000))
                ) {
                    DrawVictoryLine(states = state)
                }
            }


        }

        Spacer(Modifier.height(38.dp))

        if (state.hasWon || state.gameDraw) {

            Button(
                onClick = {

                    viewModel.onAction(
                        UserActions.PlayAgainButtonClicked
                    )
                },
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.buttonElevation(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueCustom,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Play Again",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            }

        } else {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(48.dp)
                        .background(
                            color = if (state.currentPlayer) BlueCustom else GrayBackground,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "O",
                        style = TextStyle(
                            fontSize = 46.sp
                        ),
                        color = if (state.currentPlayer) Color.White else Color.Black
                    )
                }

                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(48.dp)
                        .background(
                            color = if (state.currentPlayer == false) BlueCustom else GrayBackground,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "X",
                        style = TextStyle(
                            fontSize = 46.sp
                        ),
                        color = if (state.currentPlayer == false) Color.White else Color.Black
                    )
                }
            }
        }


    }
}


@Composable
fun TopStatesBox(

    heading: String,
    count: String,
) {
    Column(
        modifier = Modifier
            .width(90.dp)
            .background(
                color = BlueCustom,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
            .clip(
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = heading,
            style = TextStyle(
                fontSize = 14.sp
            ),
            color = Color.White
        )
        Text(
            text = count,
            style = TextStyle(
                fontSize = 22.sp
            ),
            color = Color.White,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun DrawVictoryLine(
    states: GameStates
) {
    when (states.victoryType) {
        VictoryTypeValue.NONE -> {}
        VictoryTypeValue.HORIZONTAL1 -> WinHorizontalLine1()
        VictoryTypeValue.HORIZONTAL2 -> WinHorizontalLine2()
        VictoryTypeValue.HORIZONTAL3 -> WinHorizontalLine3()
        VictoryTypeValue.VERTICAL1 -> WinVerticalLine1()
        VictoryTypeValue.VERTICAL2 -> WinVerticalLine2()
        VictoryTypeValue.VERTICAL3 -> WinVerticalLine3()
        VictoryTypeValue.DIAGONAL1 -> WinDiagonalLine1()
        VictoryTypeValue.DIAGONAL2 -> WinDiagonalLine2()
    }
}