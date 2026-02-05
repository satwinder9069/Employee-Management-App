package com.employeedb.employeedatabase.ui.components.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
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
//            OutlinedIconButton(
//                onClick = { onSignOut() },
//                border = BorderStroke(1.dp,Color.White.copy(alpha = 0.5f)),
//                modifier = Modifier.size(40.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.Logout,
//                    contentDescription = "Sign Out",
//                    tint = Color.White,
//                    modifier = Modifier.size(22.dp)
//
//                )
//
//            }
        }

    }
}