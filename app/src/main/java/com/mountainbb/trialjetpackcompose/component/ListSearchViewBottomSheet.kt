package com.mountainbb.trialjetpackcompose.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.mountainbb.trialjetpackcompose.entity.BankData
import com.mountainbb.trialjetpackcompose.ui.theme.OpensansFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.util.BottomSheetType
import com.mountainbb.trialjetpackcompose.util.clickableWithoutRipple


@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetListSearchView(
    onClick: () -> Unit,
    dataBank: ArrayList<BankData> = ArrayList(),
    bottomSheetType: BottomSheetType,
    onSelectedItem: (String, Int) -> Unit = { _: String, _: Int -> }
) {
    val listState = rememberLazyListState()
    var text by rememberSaveable { mutableStateOf("") }
    var isClearVisible by remember {
        mutableStateOf(false)
    }
    var stringFilter by remember {
        mutableStateOf("false")
    }
    var showBankList by remember {
        mutableStateOf(dataBank)
    }
    val placeholder = stringResource(id = R.string.title_placeholder_search_bank)
    val focusRequester = remember { FocusRequester() }

    val tittleBottomSheet = when (bottomSheetType) {
        BottomSheetType.BANK -> stringResource(id = R.string.title_select_bank)
        else -> stringResource(id = R.string.title_select_bank)
    }

    Column (
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
            .imePadding()
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

        Spacer(
            modifier = Modifier
                .size(10.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(34.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .align(Alignment.CenterHorizontally)
            ) {
                if (isClearVisible) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_line_search),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight()
                    )

                    Spacer(
                        modifier = Modifier
                            .size(8.dp)
                    )
                }

                BasicTextField(
                    value = text,
                    onValueChange = {
                        if (text.length <= 30){
                            text = it
                        }
                        isClearVisible = text.isNotEmpty()

                        stringFilter = it
                        showBankList =
                            if (stringFilter != "" || stringFilter.isNotEmpty()) {
                                dataBank.filterListAccounts(stringFilter) as ArrayList<BankData>
                            } else {
                                dataBank
                            }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    textStyle = TextStyle(
                        fontFamily = OpensansFontFamily,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.001.sp,
                        fontSize = 12.sp,
                    ),
                    singleLine = true,
                    decorationBox = {
                        Text(
                            text = if (text.isEmpty()) placeholder else "",
                            fontFamily = OpensansFontFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 0.001.sp,
                            color = Color(0xFF333333),
                            modifier = Modifier
                                .focusRequester(focusRequester)
                                .align(Alignment.CenterVertically)
                        )
                        it()
                    }
                )

                Image(
                    imageVector = ImageVector.vectorResource(id = if(isClearVisible)
                        R.drawable.ic_clear_grey else R.drawable.ic_line_search),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickableWithoutRipple {
                            if (isClearVisible) {
                                isClearVisible = false
                                text = ""
                                showBankList = dataBank
                            }
                        }
                )
            }
        }

        SuggestionList(
            modifier = Modifier
                .heightIn(min = 20.dp, max = 500.dp)
                .offset(y = (10).dp)
                .background(Color(0xFFFFFFFF)),
            state = listState,
            dataBank = showBankList,
            onSelectedItem = onSelectedItem,
            bottomSheetType
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SuggestionList(
    modifier: Modifier, state: LazyListState, dataBank: ArrayList<BankData>,
    onSelectedItem: (String, Int) -> Unit = { _: String, _: Int -> }, bottomSheetType: BottomSheetType
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    LazyColumn(
        modifier = modifier, state = state
    ) {
        when (bottomSheetType) {
            BottomSheetType.BANK -> {
                itemsIndexed(dataBank) { _, item ->
                    Row(
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 15.dp)
                            .clickableWithoutRipple {
                                onSelectedItem(item.bankName!!, item.bankImage!!)
                                keyboardController?.hide()
                            }
                    ) {
                        Image(
                            painter = painterResource(id = item.bankImage!!),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .width(24.dp)
                                .height(24.dp)
                        )

                        Text(
                            text = item.bankName!!, style = TextStyle(
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
                                .padding(start = 5.dp)
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
                            .padding(start = 30.dp, end = 30.dp)
                    )
                }
            }
            else -> {

            }
        }
    }
}

private fun List<BankData>?.filterListAccounts(query: String): List<BankData> {
    this?.let { listBank ->
        return listBank.filter {
            it.bankName!!.contains(query, true)
        }
    }
    return listOf()
}

@Preview
@Composable
fun PreviewBottomSheetListSearchView() {
    val bankData: ArrayList<BankData> = ArrayList<BankData>().apply {
        add(BankData(R.drawable.ic_bca, "Bank Central Asia"))
        add(BankData(R.drawable.ic_bca, "Bank Negara Indonesia"))
        add(BankData(R.drawable.ic_bca, "Bank Mandiri"))
        add(BankData(R.drawable.ic_bca, "Bank Danamon"))
    }
    TrialJetpackComposeTheme {
        BottomSheetListSearchView({},  bankData, BottomSheetType.BANK)
    }
}