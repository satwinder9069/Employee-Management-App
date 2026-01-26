package com.employeedb.employeedatabase.ui.components.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.employeedb.employeedatabase.data.local.AttendanceWithEmployee
import com.employeedb.employeedatabase.model.Attendance
import com.employeedb.employeedatabase.model.AttendanceStatus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RecentActivities(
    activities: List<AttendanceWithEmployee>
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Recent Activities",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        if(activities.isEmpty()) {
            Text(
                text = "No recent activities",
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else {
            activities.forEach { activity->
                ActivityItem(attendance = activity)
            }
        }
    }
}

@Composable
fun ActivityItem(
    attendance: AttendanceWithEmployee
) {
    val initials = attendance.employeeName
        .split(" ")
        .mapNotNull { it.firstOrNull()?.uppercase() }
        .take(2)
        .joinToString("")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF1E5EFF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(attendance.employeeName, fontWeight = FontWeight.Bold)
            Text(
                when (attendance.attendance.status) {
                    AttendanceStatus.PRESENT -> "Checked in"
                    AttendanceStatus.LATE -> "Late arrival"
                    AttendanceStatus.LEAVE -> "On leave"
                },
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Text(
            attendance.attendance.inTime ?: formatTime(attendance.attendance.date),
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}
private fun formatTime(dateInMillis: Long): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(Date(dateInMillis))
}