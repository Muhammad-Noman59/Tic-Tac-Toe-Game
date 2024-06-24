package com.example.myapplicationtictactoegame.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.myapplicationtictactoegame.ui.theme.BlueCustom
import com.example.myapplicationtictactoegame.ui.theme.GrayBackground

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameScreen() {

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
                text = "Player 'o' : 0",
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

            Text(
                text = "Draw : 0",
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

            Text(
                text = "Player 'x' : 0",
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
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(22.dp)
                )
                .clip(
                   shape =  RoundedCornerShape(22.dp)
                )
                .background(color = GrayBackground),
            contentAlignment = Alignment.Center
        ) {
            BoardBase()
        }


        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Player 'o' turn",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic
                )
            )

            Button(
                onClick = {

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