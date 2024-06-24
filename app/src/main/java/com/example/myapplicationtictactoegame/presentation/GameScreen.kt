package com.example.myapplicationtictactoegame.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationtictactoegame.BordCellValue
import com.example.myapplicationtictactoegame.GameStates
import com.example.myapplicationtictactoegame.GameViewModel
import com.example.myapplicationtictactoegame.UserActions
import com.example.myapplicationtictactoegame.VictoryTypeValue
import com.example.myapplicationtictactoegame.ui.theme.BlueCustom
import com.example.myapplicationtictactoegame.ui.theme.GrayBackground

@Composable
fun GameScreen(
    viewModel: GameViewModel
) {

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayBackground)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Player 'O' : ${state.playerCircleCount}",
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

            Text(
                text = "Draw : ${state.drawCount}",
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

            Text(
                text = "Player 'X' : ${state.playerCrossCount}",
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

        }

        Text(
            text = "Tic Tac Toe",
            style = TextStyle(
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            ),
            color = BlueCustom
        )

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
                .background(color = GrayBackground),
            contentAlignment = Alignment.Center
        ) {
            BoardBase()

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f)

            ) {
                viewModel.boardItems.forEach{ (cellNo, boardCellValue) ->
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

                                if ( boardCellValue == BordCellValue.CIRCLE ){
                                    Circle()
                                } else if (boardCellValue == BordCellValue.CROSS){
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


        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = state.hintText,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic
                )
            )

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
                )
            ) {
                Text(
                    text = "Play Again",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            }
        }

    }
}

@Composable
fun DrawVictoryLine(
    states: GameStates
) {
    when(states.victoryType){
        VictoryTypeValue.NONE ->{}
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