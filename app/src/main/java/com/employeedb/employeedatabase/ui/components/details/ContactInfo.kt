package com.employeedb.employeedatabase.ui.components.details

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
fun ContactInfo(
    email: String,
    phone: String? = null,
    location: String? = null,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFAFA)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = "Contact Information",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            ContactItem(
                icon = painterResource(R.drawable.mail),
                iconBackground = Color(0xFFCAD6FF),
                iconTint = Color(0xFF1E5EFF),
                label = "Email",
                value = email
            )
            ContactItem(
                icon = painterResource(R.drawable.call),
                iconBackground = Color(0xFFDFFFE3),
                iconTint = Color(0xFF0BAF2F),
                label = "Phone",
                value = phone ?: "Not Added"
            )
            ContactItem(
                icon = painterResource(R.drawable.location),
                iconBackground = Color(0xFFFFE6D6),
                iconTint = Color(0xFFFF7300),
                label = "Location",
                value = location ?: "Not added"
            )


        }

    }
}

@Composable
fun ContactItem(
    icon: Painter,
    iconBackground: Color,
    iconTint: Color,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(iconBackground, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = label,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(label, color = Color.DarkGray, fontSize = 12.sp)
            Text(value, fontWeight = FontWeight.Medium, color = Color.Gray)
        }
    }
}
