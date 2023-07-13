package com.mountainbb.trialjetpackcompose.component

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.ui.theme.MontserratFontFamily
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date


var dateText = "01"
var monthText = "Januari"
var yearText = "2023"
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DatePickerDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
                .background(Color(0xFFEAF1F1), shape = RoundedCornerShape(15.dp))
        ) {
            val date = listOf(
                "","","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                "30", "31", "", ""
            )
            val month = listOf(
                "","","Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus",
                "September", "Oktober", "November", "Desember", "", ""
            )
            val year = listOf(
                "1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909",
                "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919",
                "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929",
                "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939",
                "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949",
                "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959",
                "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969",
                "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979",
                "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989",
                "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999",
                "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
                "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
                "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029",
                "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039",
                "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049",
                "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059",
                "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069",
                "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079",
                "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089",
                "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099",
                "2100"
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var choosenDate by remember { mutableStateOf("12 Januari 2023") }
                Text(
                    text = stringResource(id = R.string.title_choose_date),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp, bottom = 2.dp, start = 20.dp),
                    color = Color(0xFF000000)
                )

                Text(
                    text = choosenDate,
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 18.dp, start = 20.dp),
                    color = Color(0xFF000000)
                )

                Box(
                    modifier = Modifier
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Box (
                        modifier = Modifier
                            .fillMaxWidth(0.94f)
                            .padding(top = 10.dp)
                            .background(Color(0xFFEAF1F1), shape = RoundedCornerShape(10.dp))
                            .height(40.dp)
                    )
                    Row(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(top = 10.dp)
                            .height(180.dp)
                    ) {

                        var padding = 10.dp

                        var dateFocused by remember { mutableStateOf(false) }
                        var monthFocused by remember { mutableStateOf(false) }
                        var yearFocused by remember { mutableStateOf(false) }

                        val dateScrollConnection = remember {
                            object : NestedScrollConnection {
                                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                                    dateFocused = true
                                    monthFocused = false
                                    yearFocused = false
                                    return Offset.Zero
                                }

                                override fun onPostScroll(
                                    consumed: Offset,
                                    available: Offset,
                                    source: NestedScrollSource
                                ): Offset {
                                    choosenDate = "$dateText $monthText $yearText"
                                    return super.onPostScroll(consumed, available, source)
                                }
                            }
                        }

                        val monthScrollConnection = remember {
                            object : NestedScrollConnection {
                                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                                    dateFocused = false
                                    monthFocused = true
                                    yearFocused = false
                                    return Offset.Zero
                                }

                                override fun onPostScroll(
                                    consumed: Offset,
                                    available: Offset,
                                    source: NestedScrollSource
                                ): Offset {
                                    choosenDate = "$dateText $monthText $yearText"
                                    return super.onPostScroll(consumed, available, source)
                                }
                            }
                        }

                        val yearScrollConnection = remember {
                            object : NestedScrollConnection {
                                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                                    dateFocused = false
                                    monthFocused = false
                                    yearFocused = true
                                    return Offset.Zero
                                }

                                override fun onPostScroll(
                                    consumed: Offset,
                                    available: Offset,
                                    source: NestedScrollSource
                                ): Offset {
                                    choosenDate = "$dateText $monthText $yearText"
                                    return super.onPostScroll(consumed, available, source)
                                }
                            }
                        }

                        val dateState = rememberLazyListState()
                        val monthState = rememberLazyListState()
                        val yearState = rememberLazyListState()

                        var isFirstTimer by remember {
                            mutableStateOf(true)
                        }

                        LaunchedEffect(key1 = isFirstTimer) {
                            if (isFirstTimer) {
                                isFirstTimer = false

                                val sdf = SimpleDateFormat("dd MMMM yyyy")
                                val currentDateAndTime = sdf.format(Date())

                                val arrayDate = currentDateAndTime.split(" ")

                                choosenDate = currentDateAndTime

                                this.launch{
                                    dateState.scrollToItem(findIndexFromList(arrayDate[0], data = date)-2, 16)
                                    monthState.scrollToItem(findIndexFromList(arrayDate[1], data = month)-2, 16)
                                    yearState.scrollToItem(findIndexFromList(arrayDate[2], data = year)-2, 16)
                                }
                            }
                        }
                        callLazyColumn(
                            modifier = Modifier
                                .weight(0.3f)
                                .nestedScroll(dateScrollConnection),
                            dataList = date,
                            isActive = dateFocused,
                            padding = padding,
                            align = TextAlign.End,
                            state = dateState
                        )

                        callLazyColumn(
                            modifier = Modifier
                                .weight(0.4f)
                                .nestedScroll(monthScrollConnection),
                            dataList = month,
                            isActive = monthFocused,
                            padding = padding,
                            align = TextAlign.Center,
                            state = monthState
                        )

                        callLazyColumn(
                            modifier = Modifier
                                .weight(0.3f)
                                .nestedScroll(yearScrollConnection),
                            dataList = year,
                            isActive = yearFocused,
                            padding = padding,
                            align = TextAlign.Start,
                            state = yearState
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)
                        )
                        .fillMaxHeight()
                ) {
                    Text(
                        text = "Konfirmasi",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onDismiss()
                            }
                            .align(Alignment.CenterVertically),
                        color = Color(0xFFEC701D),
                        style = TextStyle(
                            fontFamily = MontserratFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

fun findIndexFromList(find: String, data: List<String>) : Int {
    var foundId = 0
    for ((id, item) in data.withIndex()) {
        if (item == find) {
            foundId = id
        }
    }
    return foundId
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun callLazyColumn(modifier: Modifier, dataList: List<String>, isActive: Boolean, padding: Dp,
                   align: TextAlign, state: LazyListState
) {
    LazyColumn (
        modifier = modifier,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state),
        state = state
    ) {
        itemsIndexed(dataList) { index, item ->

            val color: Color = when(index) {
                getIndexPosition(index = index, state = state, position = 3) -> {
                    if (isActive) Color(0xFF21ABA1) else Color(0xFF000000)
                }
                else -> {
                    if (isActive) Color(0xFF9DC6C9) else Color(0xFFA0A0A0)
                }
            }

            val handlePadding: Dp = when(index) {
                getIndexPosition(index = index, state = state, position = 1) -> 12.dp
                getIndexPosition(index = index, state = state, position = 2) -> 20.dp
                getIndexPosition(index = index, state = state, position = 3) -> 15.dp
                getIndexPosition(index = index, state = state, position = 4) -> 10.dp
                else -> 0.dp
            }

            val handleTextSize: TextUnit = when(index) {
                getIndexPosition(index = index, state = state, position = 1) -> 12.sp
                getIndexPosition(index = index, state = state, position = 2) -> 16.sp
                getIndexPosition(index = index, state = state, position = 3) -> 20.sp
                getIndexPosition(index = index, state = state, position = 4) -> 16.sp
                else -> 12.sp
            }

            if (index == getIndexPosition(index = index, state = state, position = 3) && align == TextAlign.End) {
                dateText = dataList[index]
            }
            else if (index == getIndexPosition(index = index, state = state, position = 3) && align == TextAlign.Center) {
                monthText = dataList[index]
            }
            else if (index == getIndexPosition(index = index, state = state, position = 3) && align == TextAlign.Start) {
                yearText = dataList[index]
            }

            val modifierText: Modifier = when(align) {
                TextAlign.Start -> {
                    Modifier
                        .fillMaxWidth()
                        .padding(start = handlePadding, top = padding, bottom = padding)
                }
                TextAlign.End -> {
                    Modifier
                        .fillMaxWidth()
                        .padding(end = handlePadding, top = padding, bottom = padding)
                }
                else -> {
                    Modifier
                        .fillMaxWidth()
                        .padding(top = padding, bottom = padding)
                }
            }

            Text(
                text = item,
                modifier = modifierText,
                color = color,
                style = TextStyle(
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = handleTextSize,
                    lineHeight = 24.sp,
                    letterSpacing = 0.15.sp,
                    textAlign = align
                )
            )
        }
    }
}

fun handleFling() : FlingBehavior {
    val stopFling = object : FlingBehavior {
        override suspend fun ScrollScope.performFling(
            initialVelocity: Float
        ): Float {
            return 0f
        }
    }
    return stopFling
}

@Composable
fun getIndexPosition(index: Int, state: LazyListState, position: Int) : Any? {
    val findMiddleItem by remember(index) {
        derivedStateOf {
            val layoutInfo = state.layoutInfo
            val visibleItemInfo = layoutInfo.visibleItemsInfo
            val itemInfo = visibleItemInfo.firstOrNull { it.index == index }

            itemInfo?.let {
                val delta = it.size/2

                val startRange = when(position) {
                    1 -> -delta+(-it.size*2)
                    2 -> -delta+(-it.size)
                    3 -> -delta
                    4 -> delta
                    else -> delta+it.size
                }
                val endRange = when(position) {
                    1 -> -delta+(-it.size)
                    2 -> -delta
                    3 -> delta
                    4 -> delta+it.size
                    else -> delta+(it.size*2)
                }

                val center = state.layoutInfo.viewportEndOffset / 2
                val childCenter = it.offset + it.size / 2
                val target = childCenter - center
                if (target in startRange..endRange) return@derivedStateOf index
            }
        }
    }
    return findMiddleItem
}