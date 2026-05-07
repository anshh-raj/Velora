package com.example.velora.velora.presentation.components.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import com.example.velora.ui.theme.BodyNormal
import com.example.velora.ui.theme.MajorColors
import com.example.velora.velora.presentation.utils.HSpacer
import kotlinx.coroutines.delay

object AppSnackbarHost{
    val snackbarHostState = SnackbarHostState()
}

@Composable
fun CustomSnackbarHost(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = AppSnackbarHost.snackbarHostState
){
    var showAnimation by remember { mutableStateOf(false) }
    var currentMessage by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(true) }
    var animationDuration by remember { mutableLongStateOf(800L) }

    LaunchedEffect(snackbarHostState.currentSnackbarData) {
        snackbarHostState.currentSnackbarData?.let { data->
            val actionLabel = data.visuals.actionLabel

            animationDuration = when(data.visuals.duration){
                SnackbarDuration.Short -> 800L
                SnackbarDuration.Long -> 2000L
                SnackbarDuration.Indefinite -> 4000L
            }

            when(actionLabel){
                "POSITIVE" -> {
                    isSuccess = true
                    currentMessage = data.visuals.message
                    showAnimation = true
                }

                "NEGATIVE" -> {
                    isSuccess = false
                    currentMessage = data.visuals.message
                    showAnimation = true
                }
            }
        }
    }

    CoronosSnackBar(
        message = currentMessage,
        isSuccess = isSuccess,
        isVisible = showAnimation,
        onAnimationComplete = {
            showAnimation = false
            snackbarHostState.currentSnackbarData?.dismiss()
        },
        onDismiss = {
            showAnimation = false
            snackbarHostState.currentSnackbarData?.dismiss()
        },
        modifier = Modifier,
        displayDurationMillis = animationDuration
    )
}

@Composable
fun CoronosSnackBar(
    message: String,
    isSuccess: Boolean,
    isVisible: Boolean,
    onAnimationComplete: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    displayDurationMillis: Long = 2000L
) {
    val transition = updateTransition(targetState = isVisible, label = "SnackBarVisibility")

    val offsetY by transition.animateDp(
        label = "OffsetY",
        transitionSpec = {
            tween(durationMillis = 400, easing = FastOutSlowInEasing)
        }
    ) { visible ->
        if (visible) 0.dp else (-100).dp
    }

    val alpha by transition.animateFloat(
        label = "Alpha",
        transitionSpec = {
            tween(durationMillis = 300)
        }
    ) { visible ->
        if (visible) 1f else 0f
    }

    if(isVisible || alpha > 0f){
        LaunchedEffect(Unit) {
            delay(displayDurationMillis)
            onAnimationComplete()
        }

        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = offsetY)
                .alpha(alpha),
            contentAlignment = Alignment.TopCenter
        ){
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = if (isSuccess) Color(0xFF4CAF50) else MaterialTheme.colorScheme.errorContainer,
                tonalElevation = 6.dp,
                shadowElevation = 8.dp,
                modifier = Modifier.wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Row (
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = if (isSuccess) Icons.Default.CheckCircle else Icons.Default.Error,
                        contentDescription = null,
                        tint = Color.White,
                    )

                    HSpacer(12.dp)

                    Text(
                        text = message,
                        style = BodyNormal().copy(color = MajorColors.White.color)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Dismiss",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}