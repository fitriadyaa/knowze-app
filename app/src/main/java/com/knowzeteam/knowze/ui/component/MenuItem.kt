package com.knowzeteam.knowze.ui.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knowzeteam.knowze.R
import com.knowzeteam.knowze.ui.theme.KnowzeTheme
import kotlinx.coroutines.delay

@Composable
fun MenuItem(
    text: String,
    subText: String? = null,
    imageResId: Int,
    boxColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var clicked by remember { mutableStateOf(false) }
    val scale: Float by animateFloatAsState(
        targetValue = if (clicked) 0.95f else 1f, // Shrink and then return to original size
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    LaunchedEffect(clicked) {
        if (clicked) {
            delay(350)  // Duration of the bounce effect
            clicked = false
        }
    }

    Box(
        modifier = modifier
            .height(110.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                clicked = true
                onClick()
            }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(RoundedCornerShape(20.dp))
            .background(color = boxColor)

    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = modifier.weight(1f)) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                subText?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    )
                }
            }
            Spacer(modifier = modifier.width(16.dp))
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "image description",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview(){
    KnowzeTheme {
        MenuItem(
            text = "Cari Rekomendasi\nKursusmu",
            imageResId = R.drawable.ic_cari_rekomendasi,
            boxColor = Color(0xFF43936C),
            onClick = {}
        )
    }
}