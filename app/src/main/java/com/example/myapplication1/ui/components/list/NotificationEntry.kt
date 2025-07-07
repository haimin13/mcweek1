package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.R
import java.time.Duration
import java.time.LocalDateTime
import java.time.Period

@Composable
fun NotificationEntry(
    userId: String?,
    itemId: String?,
    recordedTime: LocalDateTime?
) {
    val currentDateTime = LocalDateTime.now()
    val timeDiff: String = if (recordedTime != null) {
        getTimeDifference(currentDateTime, recordedTime)
    } else {
        "알 수 없음"
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {}
    ){
        Image(
            painter = painterResource(R.drawable.dummy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(10))
        )
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = userId + "님이 " + itemId + "을(를) 좋아합니다.",
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 10.dp, end = 5.dp)
            )
            Text(
                text = timeDiff,
                fontSize = 10.sp,
                color = Color.Gray
            )
        }
        Image(
            painter = painterResource(R.drawable.drowning),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(10))
        )
    }
}

fun getTimeDifference(currentTime: LocalDateTime, recordedTime: LocalDateTime): String {
    val duration = Duration.between(recordedTime, currentTime).abs()

    return when {
        duration.toMinutes() < 1 -> "방금 전"
        duration.toHours() < 1 -> "${duration.toMinutes()}분 전"
        duration.toDays() < 1 -> "${duration.toHours()}시간 전"
        else -> {
            val period = Period.between(recordedTime.toLocalDate(), currentTime.toLocalDate()).normalized()
            when {
                period.days < 7 -> "${period.days}일 전"
                period.days < 30 -> "${period.days / 7}주 전"
                period.months < 12 -> "${period.months}개월 전"
                else -> "${period.years}년 전"
            }
        }
    }
}