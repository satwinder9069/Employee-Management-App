package com.employeedb.employeedatabase.ui.components.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.employeedb.employeedatabase.R

@Composable
fun InfoCardRow(
    attendancePercent: String,
    leavesTaken: String,
    performance: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        InfoCard(
            icon = painterResource(R.drawable.clock),
            iconColor = Color(0xFF66BB88),
            title = "Attendance",
            value = attendancePercent
        )

        InfoCard(
            icon = painterResource(R.drawable.calendar),
            iconColor = Color(0xFF1E5EFF),
            title = "Leaves Taken",
            value = leavesTaken
        )

        InfoCard(
            icon = painterResource(R.drawable.performance_badge),
            iconColor = Color(0xFFD9514F),
            title = "Performance",
            value = performance
        )
    }
}

@Composable
fun InfoCard(
    icon: Painter,
    iconColor: Color,
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.width(120.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier.height(16.dp))
            Text(value, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(title, fontSize = 12.sp, color = Color.Gray)
        }
    }
}
