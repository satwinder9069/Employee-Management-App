package com.employeedb.employeedatabase.ui.components.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
fun StatsSection(
    totalEmployees: Int,
    presentToday: Int,
    onLeave: Int,
    lateArrivals: Int,
    modifier: Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            StatCard(
                totalEmployees.toString(),
                "Total Employees",
                painterResource(R.drawable.group),
                Color(0xFF1E5E5F),
                modifier = Modifier.weight(1f)
            )
            StatCard(
                presentToday.toString(),
                "Present Today",
                painterResource(R.drawable.person_check),
                Color(0xFF2ECC71),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            StatCard(
                onLeave.toString(),
                "On Leave",
                painterResource(R.drawable.on_leave),
                Color(0xFFF39C12),
                modifier = Modifier.weight(1f)
            )
            StatCard(
                lateArrivals.toString(),
                "Late Arrivals",
                painterResource(R.drawable.clock),
                Color(0xFFE74C3C),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StatCard(
    value: String,
    label: String,
    icon: Painter,
    iconColor: Color,
    modifier: Modifier
) {
    Card(
        modifier = modifier.height(150.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(iconColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.background(iconColor)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    value, fontSize = 22.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    label, fontSize = 14.sp, color = Color.Gray
                )
            }
        }
    }
}