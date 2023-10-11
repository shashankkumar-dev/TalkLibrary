package com.xynos.talk.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun TimeText(sentTime: Long) {
    Text(
        modifier = Modifier.padding(vertical = 3.dp),
        text = formatTimeToDisplay(sentTime),
        style = TextStyle(fontSize = 9.sp, color = Color.Gray)
    )
}

fun formatTimeToDisplay(millis: Long): String {
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR)

    calendar.timeInMillis = millis
    val messageYear = calendar.get(Calendar.YEAR)
    val messageDayOfYear = calendar.get(Calendar.DAY_OF_YEAR)

    return when {
        currentDayOfYear == messageDayOfYear && currentYear == messageYear -> {
            SimpleDateFormat("h:mm a", Locale.getDefault()).format(millis)
        }

        currentDayOfYear - messageDayOfYear == 1 && currentYear == messageYear -> {
            "yesterday"
        }

        else -> {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(millis)
        }
    }
}
