package com.employeedb.employeedatabase.ui.components.attendance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.employeedb.employeedatabase.data.local.AttendanceWithEmployee
import com.employeedb.employeedatabase.model.Attendance
import com.employeedb.employeedatabase.model.AttendanceStatus
import com.employeedb.employeedatabase.ui.components.common.ConfirmDialogue

@Composable
fun AttendanceList(
    attendanceList: List<AttendanceWithEmployee>,
    onDelete: (AttendanceWithEmployee) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(attendanceList) {item ->
            AttendanceListItem(
                item = item,
                onDelete = { onDelete(item) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceListItem(
    item: AttendanceWithEmployee,
    onDelete: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    val attendance = item.attendance

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Status indicator
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        color = when (attendance.status) {
                            AttendanceStatus.PRESENT -> Color(0xFF4CAF50)
                            AttendanceStatus.LATE -> Color(0xFFFFA726)
                            AttendanceStatus.LEAVE -> Color(0xFF42A5F5)
                        },
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(12.dp))

            // employee info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.employeeName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if(attendance.inTime != null && attendance.outTime != null) {
                        Text(
                            text = "${attendance.inTime} - ${attendance.outTime}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    attendance.totalHours?.let {
                        Text(
                            text = "($it)",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Status Badge
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = when (attendance.status) {
                    AttendanceStatus.PRESENT -> Color(0xFF4CAF50).copy(alpha = 0.2f)
                    AttendanceStatus.LATE -> Color(0xFFFFA726).copy(alpha = 0.2f)
                    AttendanceStatus.LEAVE -> Color(0xFF42A5F5).copy(alpha = 0.2f)
                }
            ) {
                Text(
                    text = attendance.status.name,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Medium,
                    color = when (attendance.status) {
                        AttendanceStatus.PRESENT -> Color(0xFF2E7D32)
                        AttendanceStatus.LATE -> Color(0xFFF57C00)
                        AttendanceStatus.LEAVE -> Color(0xFF1565C0)
                    }
                )
            }

            // Delete button
            IconButton(onClick = { showDeleteDialog = true }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Attendance") },
            text = { Text("Delete attendance for ${item.employeeName}?") },
            confirmButton = {
                TextButton(onClick = {
                    onDelete()
                    showDeleteDialog = false
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}