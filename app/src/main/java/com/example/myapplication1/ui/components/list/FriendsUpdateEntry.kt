package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.models_unused.UserLog
import java.time.Duration
import java.time.LocalDateTime
import java.time.Period

@Composable
fun FriendsUpdateEntry(userLog: UserLog) {
    val currentDateTime = LocalDateTime.now()
    val timeDiff: String = if (userLog.time != null) {
        getTimeDifference(currentDateTime, userLog.time)
    } else {
        "알 수 없음"
    }

    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(R.drawable.dummy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(100))
        )
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight(600))) {
                        append(userLog.userName)
                    }
                    append("님이 ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight(600))) {
                        append(userLog.itemName)
                    }
                    append("을 좋아합니다.")
                },
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
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
                .size(36.dp)
                .clip(RoundedCornerShape(10))
        )
    }
}

fun getTimeDifference(currentTime: LocalDateTime, recordedTime: LocalDateTime): String {
    val duration = Duration.between(recordedTime, currentTime).abs()

    return when {
        duration.toMinutes() < 1 -> "방금"
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