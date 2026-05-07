package com.example.velora.velora.presentation.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun HSpacer(width: Dp = MaterialTheme.spacing.s4) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun VSpacer(height: Dp = MaterialTheme.spacing.s4){
    Spacer(modifier = Modifier.height(height))
}

data class Spacing(
    val default: Dp = 0.dp,
    val sHalf: Dp = 2.dp,
    val s1: Dp = 4.dp,
    val s2: Dp = 8.dp,
    val s3: Dp = 12.dp,
    val s4: Dp = 16.dp,
    val s5: Dp = 20.dp,
    val s6: Dp = 24.dp,
    val s8: Dp = 32.dp,
    val s10: Dp = 40.dp,
    val s16: Dp = 64.dp,
)

val LocalSpacingProvider = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacingProvider.current