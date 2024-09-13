package com.yaman.belediyehizmet.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Header(text:String) {
    androidx.compose.material.Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        color = Color.Black,
        modifier = Modifier.fillMaxWidth().padding(start = 45.dp),
        textAlign = TextAlign.Start
    )
}