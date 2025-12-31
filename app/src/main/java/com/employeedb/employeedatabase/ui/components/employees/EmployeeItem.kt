package com.employeedb.employeedatabase.ui.components.employees

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.employeedb.employeedatabase.R
import com.employeedb.employeedatabase.model.Employee
import com.employeedb.employeedatabase.navigation.Screen

@Composable
fun EmployeeItem(employee: Employee, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFEFFFF)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(Color(0xFFE3ECFF), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = employee.initials,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E5EFF)
                )

            }

            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = employee.name,
                        fontWeight = FontWeight.Bold
                    )
                    if (employee.onLeave) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "On Leave",
                            color = Color(0xFFEF5350),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                    }
                }

                Text(
                    text = employee.department, fontSize = 13.sp, color = Color.Gray
                )
                Text(
                    text = employee.role, fontSize = 12.sp, color = Color.LightGray
                )
            }
            IconButton(
                onClick = {
                    navController.navigate(
                        Screen.DetailScreen.createRoute(employee.id)
                    )
                },
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_forward),
                    contentDescription = null,
                )
            }
        }
    }
}