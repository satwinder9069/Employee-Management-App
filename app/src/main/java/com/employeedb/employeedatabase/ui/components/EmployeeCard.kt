package com.employeedb.employeedatabase.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.employeedb.employeedatabase.R
import com.employeedb.employeedatabase.model.Employee

@Composable
fun EmployeeCard(
    employee: Employee,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 56.dp, bottom = 48.dp)
            ) {
                Text(
                    text = employee.name,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = employee.department,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            Button(
                onClick = onEdit,
                modifier = Modifier.align(Alignment.TopEnd).size(35.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Unspecified
                )
            ) {
                Image(
                    painter = painterResource(R.drawable.edit),
                    contentDescription = "Edit",
                    modifier = Modifier.size(20.dp)
                )
            }

            Button(
                onClick = onDelete,
                modifier = Modifier.align(Alignment.BottomEnd),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                ),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Delete",
                    color = MaterialTheme.colorScheme.onError,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(6.dp))

                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "Delete",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}