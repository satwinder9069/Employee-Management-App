package com.employeedb.employeedatabase.ui.components.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmploymentDetails(
    employeeId: String,
    joinDate: String,
    department: String,
    manager: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFAFA)
        ),
        elevation = CardDefaults.cardElevation(4.dp)

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                "Employment Details", fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                DetailColumn(
                    label = "Employee ID",
                    value = employeeId,
                    modifier = Modifier.weight(1f)
                )
                DetailColumn(
                    label = "Join Date",
                    value = joinDate,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                DetailColumn(
                    label = "Department",
                    value = department,
                    modifier = Modifier.weight(1f)
                )
                DetailColumn(
                    label = "Manager",
                    value = manager,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun DetailColumn(label: String, value: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = FontWeight.Medium, color = Color.LightGray)
    }
}