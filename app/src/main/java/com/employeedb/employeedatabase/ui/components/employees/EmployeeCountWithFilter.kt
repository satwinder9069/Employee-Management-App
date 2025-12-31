package com.employeedb.employeedatabase.ui.components.employees

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.employeedb.employeedatabase.R

@Composable
fun EmployeeCountWithFilter(
    count: Int,
    onFilterCheck: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$count employees",
            color = Color.Gray
        )
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(enabled = onFilterCheck != null) {
                onFilterCheck?.invoke()
            }
        ) {   Icon(
                painter = painterResource(R.drawable.filter_list),
                contentDescription = "Filter",
                tint = Color(0xFF1E5EFF)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Filter",
                color = Color(0xFF1E5EFF),
                fontSize = 14.sp
            )
        }
    }

}