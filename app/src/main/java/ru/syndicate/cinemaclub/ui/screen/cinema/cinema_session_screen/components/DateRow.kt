package ru.syndicate.cinemaclub.ui.screen.cinema.cinema_session_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DateRow(
    modifier: Modifier = Modifier,
    selectedDate: MutableState<LocalDate> = mutableStateOf(LocalDate.now())
) {

    val weeks = getWeeksFromStartDate(
        LocalDate.of(LocalDate.now().year, Month.JANUARY, 1),
        78
    )
    val initWeek = getCurrentWeek(weeks, LocalDate.now())
    var monthText by remember {
        mutableStateOf(
            getMonthTitle(selectedDate.value.month)
        )
    }


    val pagerState = rememberPagerState(
        initialPage = initWeek,
        initialPageOffsetFraction = 0f,
        pageCount = { weeks.size }
    )

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->

            val weekDates = weeks[page]

            monthText = if (selectedDate.value !in weekDates) {
                getMonthTitle(weekDates[3].month)
            } else {
                getMonthTitle(selectedDate.value.month)
            }
        }
    }

    LaunchedEffect(key1 = selectedDate.value) {
        snapshotFlow { pagerState.currentPage }.collect { page ->

            val weekDates = weeks[page]

            monthText = if (selectedDate.value !in weekDates) {
                getMonthTitle(weekDates[3].month)
            } else {
                getMonthTitle(selectedDate.value.month)
            }
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = monthText,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = CustomGray
        )

        HorizontalPager(
            state = pagerState
        ) { page ->

            val weekDates = weeks[page]

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                weekDates.forEach {

                    DayItem(
                        date = it,
                        isSelected = it == selectedDate.value,
                        onClick = { date ->
                            selectedDate.value = date
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DayItem(
    date: LocalDate = LocalDate.now(),
    isSelected: Boolean = false,
    onClick: (LocalDate) -> Unit = { }
) {

    Column(
        modifier = Modifier
            .width(40.dp)
            .clickable { onClick(date) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = getDayTitle(date.dayOfWeek),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = if (isSelected) CustomBlue else CustomGray
        )

        Text(
            text = date.dayOfMonth.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = if (isSelected) CustomBlue else CustomGray
        )

        if (isSelected) {

            Box(
                modifier = Modifier
                    .padding(
                        top = 4.dp
                    )
                    .background(
                        color = CustomBlue
                    )
                    .fillMaxWidth()
                    .height(2.dp)
            )
        }
    }
}

private fun getWeeksFromStartDate(
    startDate: LocalDate,
    weeksCount: Int
): List<List<LocalDate>> {
    val weeks = mutableListOf<List<LocalDate>>()
    var currentStartOfWeek = startDate

    while (currentStartOfWeek.dayOfWeek != DayOfWeek.MONDAY) {
        currentStartOfWeek = currentStartOfWeek.minusDays(1)
    }

    repeat(weeksCount) {
        val week = (0 until 7).map { currentStartOfWeek.plusDays(it.toLong()) }
        weeks.add(week)
        currentStartOfWeek = currentStartOfWeek.plusWeeks(1)
    }

    return weeks
}

fun getCurrentWeek(weeks: List<List<LocalDate>>, currentDate: LocalDate): Int {
    for (i in weeks.indices) {

        for (j in weeks[i].indices) {

            if (weeks[i][j].month == currentDate.month) {
                weeks[i].forEach { day ->
                    if (day.dayOfMonth == currentDate.dayOfMonth)
                        return i
                }
            }
        }
    }

    return 0
}

fun getDayTitle(dayOfWeek: DayOfWeek) = when (dayOfWeek) {
    DayOfWeek.MONDAY -> "ПН"
    DayOfWeek.TUESDAY -> "ВТ"
    DayOfWeek.WEDNESDAY -> "СР"
    DayOfWeek.THURSDAY -> "ЧТ"
    DayOfWeek.FRIDAY -> "ПТ"
    DayOfWeek.SATURDAY -> "СБ"
    DayOfWeek.SUNDAY -> "ВС"
}

fun getMonthTitle(month: Month) = when (month) {
    Month.JANUARY -> "Янв."
    Month.FEBRUARY -> "Фев."
    Month.MARCH -> "Мар."
    Month.APRIL -> "Апр."
    Month.MAY -> "Май"
    Month.JUNE -> "Июн."
    Month.JULY -> "Июл."
    Month.AUGUST -> "Авг."
    Month.SEPTEMBER -> "Сен."
    Month.OCTOBER -> "Окт."
    Month.NOVEMBER -> "Ноя."
    Month.DECEMBER -> "Дек."
}

@Preview
@Composable
private fun PreviewDateRow() {
    DateRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BackgroundColor
            )
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            )
    )
}

@Preview
@Composable
private fun PreviewDayItem() {
    DayItem()
}

@Preview
@Composable
private fun PreviewDayItemSelected() {
    DayItem(
        isSelected = true
    )
}