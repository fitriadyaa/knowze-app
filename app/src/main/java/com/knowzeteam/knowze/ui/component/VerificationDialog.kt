package com.knowzeteam.knowze.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.theme.KnowzeTheme

@Composable
fun VerificationDialog(
    onButtonClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(342.dp, 401.dp)
            .fillMaxSize()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_check_dialog),
                contentDescription = "Icon Check",
                modifier = Modifier
                    .size(101.dp, 101.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.register_success),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.verification_dialog_message),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = onButtonClick,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.back_to_login),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationPreview() {
    KnowzeTheme {
        VerificationDialog(onButtonClick = {})
    }
}