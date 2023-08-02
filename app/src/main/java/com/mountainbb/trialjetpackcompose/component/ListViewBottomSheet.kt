package com.mountainbb.trialjetpackcompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.entity.CurrencyData
import com.mountainbb.trialjetpackcompose.entity.PurposeData
import com.mountainbb.trialjetpackcompose.ui.theme.OpensansFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.util.BottomSheetType
import com.mountainbb.trialjetpackcompose.util.BottomSheetType.CURRENCY
import com.mountainbb.trialjetpackcompose.util.BottomSheetType.PURPOSE
import com.mountainbb.trialjetpackcompose.util.clickableWithoutRipple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetListView(
    onClick: () -> Unit,
    dataPurpose: ArrayList<PurposeData> = ArrayList(),
    dataCurrency: ArrayList<CurrencyData> = ArrayList(),
    bottomSheetType: BottomSheetType,
    onSelectedItem: (String) -> Unit = {}
) {
    var tittleBottomSheet = when (bottomSheetType) {
        PURPOSE -> stringResource(id = R.string.title_select_purpose)
        CURRENCY -> stringResource(id = R.string.title_select_currency)
        else -> stringResource(id = R.string.title_select_purpose)
    }

    val listState = rememberLazyListState()

    Column (
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_cross_close),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 10.dp, start = 15.dp)
                    .size(20.dp)
                    .clickableWithoutRipple {
                        onClick()
                    }
            )

            Text(
                text = tittleBottomSheet,
                style = TextStyle(
                    fontFamily = OpensansFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF333333)
                ),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )

            Spacer(
                modifier = Modifier
                    .size(25.dp)
                    .padding(end = 15.dp)
            )
        }

        Card {

        }

        SuggestionList(
            modifier = Modifier
                .heightIn(min = 20.dp, max = 400.dp)
                .offset(y = (10).dp)
                .background(Color(0xFFFFFFFF)),
            state = listState,
            dataPurpose = dataPurpose,
            dataCurrency = dataCurrency,
            onSelectedItem = onSelectedItem,
            bottomSheetType
        )
    }
}


@Composable
fun SuggestionList(
    modifier: Modifier, state: LazyListState, dataPurpose: ArrayList<PurposeData>,
    dataCurrency: ArrayList<CurrencyData>, onSelectedItem: (String) -> Unit = {},
    bottomSheetType: BottomSheetType
) {
    LazyColumn(
        modifier = modifier, state = state
    ) {
        when (bottomSheetType) {
            PURPOSE -> {
                itemsIndexed(dataPurpose) { _, item ->
                    Row(
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 15.dp)
                            .clickableWithoutRipple {
                                onSelectedItem("${item.purposeCode!!} - ${item.purposeName!!}")
                            }
                    ) {
                        Text(
                            text = "${item.purposeCode!!} - ${item.purposeName!!}", style = TextStyle(
                                fontFamily = OpensansFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                letterSpacing = 0.15.sp,
                                textAlign = TextAlign.Start,
                                color = Color(0xFF000000)
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                                .padding(start = 15.dp)
                        )

                        if (item.isSelected!!) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_check_green),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .padding(end = 15.dp)
                            )
                        }
                    }

                    Divider(
                        color = Color(0xFFC1C1C1),
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp)
                    )
                }
            }
            else -> {
                itemsIndexed(dataCurrency) { _, item ->
                    Row(
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 15.dp)
                            .clickableWithoutRipple {
                                onSelectedItem(item.currencyName!!)
                            }
                    ) {
                        Text(
                            text = item.currencyName!!, style = TextStyle(
                                fontFamily = OpensansFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                letterSpacing = 0.15.sp,
                                textAlign = TextAlign.Start,
                                color = Color(0xFF000000)
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                                .padding(start = 15.dp)
                        )

                        if (item.isSelected!!) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_check_green),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .padding(end = 15.dp)
                            )
                        }
                    }

                    Divider(
                        color = Color(0xFFC1C1C1),
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBottomSheetListView() {
    val purposeData: ArrayList<PurposeData> = ArrayList<PurposeData>().apply {
        add(PurposeData("01", "Investasi"))
        add(PurposeData("02", "Transfer", true))
        add(PurposeData("03", "Pembelian"))
        add(PurposeData("99", "Lain-lain"))
    }
    TrialJetpackComposeTheme {
        BottomSheetListView({}, purposeData, ArrayList(), PURPOSE)
    }
}