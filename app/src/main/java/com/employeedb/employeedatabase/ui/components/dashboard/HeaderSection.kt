package com.employeedb.employeedatabase.ui.components.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HeaderSection(modifier: Modifier) {
    val formattedDate = remember {
        SimpleDateFormat(
            "EEEE, MMMM dd, yyyy",
            Locale.getDefault()
        ).format(Date())
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF1E5EFF))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = "Dashboard",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = formattedDate,
            color = Color.White.copy(alpha = 0.9f),
            fontSize = 14.sp
        )

    }
}