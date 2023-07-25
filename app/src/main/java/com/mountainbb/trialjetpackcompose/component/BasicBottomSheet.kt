package com.mountainbb.trialjetpackcompose.component

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.entity.SuggestionData
import com.mountainbb.trialjetpackcompose.ui.theme.OpensansFontBold
import com.mountainbb.trialjetpackcompose.ui.theme.OpensansFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.util.clickableWithoutRipple
import com.mountainbb.trialjetpackcompose.util.supportWideScreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLayout() {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    var isSheetDisplay by remember {
        mutableStateOf(true)
    }

    val roundedCornerRadius =  12.dp
    val modifier = Modifier.fillMaxWidth()

    var isBifastSelected by remember {
        mutableStateOf(true)
    }
    var isOnlineSelected by remember {
        mutableStateOf(false)
    }
    var isSknSelected by remember {
        mutableStateOf(false)
    }
    var isRtgsSelected by remember {
        mutableStateOf(false)
    }
    var isAccountNumberSelected by remember {
        mutableStateOf(true)
    }
    var isAliasSelected by remember {
        mutableStateOf(false)
    }

    val suggestState = rememberLazyListState()

    var suggestData: ArrayList<SuggestionData> = ArrayList<SuggestionData>().apply {
        add(SuggestionData(R.drawable.ic_bca, "Restu", "889344845"))
        add(SuggestionData(R.drawable.ic_bca, "Ayu", "783642767"))
        add(SuggestionData(R.drawable.ic_bca, "Lingga", "77829348"))
        add(SuggestionData(R.drawable.ic_bca, "Arif", "100023112"))
        add(SuggestionData(R.drawable.ic_bca, "Sukmawan", "44523833"))
        add(SuggestionData(R.drawable.ic_bca, "Bli Joe", "3323425"))
    }

    var showSuggest by remember {
        mutableStateOf(ArrayList<SuggestionData>())
    }

    var stringFilter by remember {
        mutableStateOf("")
    }

    var accountNumber by remember {
        mutableStateOf("")
    }

    var datePickerStatus by remember {
        mutableStateOf(false)
    }

    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

                Button(
                    onClick = {
                        coroutineScope.launch { modalSheetState.hide() }
                    }
                ) {
                    Text(text = "Hide Sheet")
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier.supportWideScreen()
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight()
            ) {
                TrialTopAppBar(title = stringResource(id = R.string.title_other_bank),
                    leftIcon = R.drawable.baseline_keyboard_arrow_left_24,
                    leftIconAction = {
                        Activity().finish()
                    })

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .weight(1f)
                        .align(Alignment.CenterHorizontally)
                ) {
                    item {
                        androidx.compose.material3.Text(
                            text = stringResource(id = R.string.title_transfer_from), style = TextStyle(
                                fontFamily = OpensansFontBold,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                letterSpacing = 0.15.sp,
                                textAlign = TextAlign.Start,
                                color = Color(0xFF80000000)
                            ), modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp, bottom = 24.dp)
                        )

                        SofCard(
                            cardModifier = Modifier
                                .fillMaxWidth()
                                .height(88.dp)
                                .shadow(4.dp, shape = RoundedCornerShape(15.dp))
                        )

                        androidx.compose.material3.Text(
                            text = stringResource(id = R.string.title_transfer_type), style = TextStyle(
                                fontFamily = OpensansFontBold,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                letterSpacing = 0.15.sp,
                                textAlign = TextAlign.Start,
                                color = Color(0xFF80000000)
                            ), modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 34.dp, bottom = 24.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TransferTypeCard(
                                Modifier
                                    .weight(1f)
                                    .clickableWithoutRipple {
                                        isBifastSelected = true
                                        isOnlineSelected = false
                                        isSknSelected = false
                                        isRtgsSelected = false
                                    },
                                stringResource(id = R.string.title_bifast),
                                stringResource(id = R.string.title_bifast_desc),
                                isBifastSelected
                            )

                            Spacer(
                                modifier = Modifier.size(10.dp)
                            )

                            TransferTypeCard(
                                Modifier
                                    .weight(1f)
                                    .clickableWithoutRipple {
                                        isBifastSelected = false
                                        isOnlineSelected = true
                                        isSknSelected = false
                                        isRtgsSelected = false
                                    },
                                stringResource(id = R.string.title_online),
                                stringResource(id = R.string.title_online_desc),
                                isOnlineSelected
                            )
                        }

                        Spacer(
                            modifier = Modifier.size(15.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TransferTypeCard(
                                Modifier
                                    .weight(1f)
                                    .clickableWithoutRipple {
                                        isBifastSelected = false
                                        isOnlineSelected = false
                                        isSknSelected = true
                                        isRtgsSelected = false
                                    },
                                stringResource(id = R.string.title_skn),
                                stringResource(id = R.string.title_skn_desc),
                                isSknSelected
                            )

                            Spacer(
                                modifier = Modifier.size(10.dp)
                            )

                            TransferTypeCard(
                                Modifier
                                    .weight(1f)
                                    .clickableWithoutRipple {
                                        isBifastSelected = false
                                        isOnlineSelected = false
                                        isSknSelected = false
                                        isRtgsSelected = true
                                    },
                                stringResource(id = R.string.title_rtgs),
                                stringResource(id = R.string.title_rtgs_desc),
                                isRtgsSelected
                            )
                        }

                        Spacer(
                            modifier = Modifier.size(24.dp)
                        )

                        if (isBifastSelected) {
                            androidx.compose.material3.Text(
                                text = stringResource(id = R.string.title_recipient_type),
                                style = TextStyle(
                                    fontFamily = OpensansFontBold,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    letterSpacing = 0.15.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color(0xFF80000000)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 20.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                RecipientTypeCard(
                                    Modifier.clickableWithoutRipple {
                                        isAccountNumberSelected = true
                                        isAliasSelected = false
                                    },
                                    stringResource(id = R.string.title_account_number),
                                    isAccountNumberSelected
                                )

                                Spacer(
                                    modifier = Modifier.size(10.dp)
                                )

                                RecipientTypeCard(
                                    Modifier.clickableWithoutRipple {
                                        isAccountNumberSelected = false
                                        isAliasSelected = true
                                    }, stringResource(id = R.string.title_alias), isAliasSelected
                                )
                            }

                            Spacer(
                                modifier = Modifier.size(18.dp)
                            )

                            if (isAliasSelected) {
                                InfoCard(
                                    descText = stringResource(id = R.string.title_alias_desc),
                                    cardModifier = Modifier.fillMaxWidth()
                                )

                                Spacer(
                                    modifier = Modifier.size(18.dp)
                                )

                                TextFieldRightImage(
                                    label = stringResource(id = R.string.title_recipient_account),
                                    placeholder = stringResource(id = R.string.title_placeholder_type_alias),
                                    detailInfo = stringResource(id = R.string.title_detail_input_alias),
                                    imageResource = R.drawable.ic_favorite_not_filled
                                )

                                Spacer(
                                    modifier = Modifier.size(10.dp)
                                )

                                InputAmount(
                                    label = stringResource(id = R.string.title_transfer_amount),
                                    placeholder = stringResource(id = R.string.title_input_amount),
                                    currency = stringResource(id = R.string.title_idr)
                                )

                                Spacer(
                                    modifier = Modifier.size(16.dp)
                                )

                                TextFieldRightImage(
                                    label = stringResource(id = R.string.title_label_message),
                                    placeholder = stringResource(id = R.string.title_placeholder_message)
                                )

                                Spacer(
                                    modifier = Modifier.size(16.dp)
                                )

                                TextRightImage(
                                    label = stringResource(id = R.string.title_purpose_transfer),
                                    placeholder = stringResource(id = R.string.title_default_purpose_transfer),
                                    imageResource = R.drawable.ic_drop_down_maroon
                                )

                                Spacer(
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            if (isAccountNumberSelected) {
                                ConstraintLayout {
                                    val (
                                        recipAcct, bankName, spacer1, inputAmt, spacer2, inputMsg,
                                        spacer3, purposeTrf, spacer4, rvSuggest
                                    ) = createRefs()

                                    TextFieldRightImage(
                                        label = stringResource(id = R.string.title_recipient_account),
                                        placeholder = stringResource(id = R.string.title_placeholder_type_number),
                                        imageResource = R.drawable.ic_favorite_not_filled,
                                        cardModifier = Modifier.constrainAs(recipAcct) {
                                            top.linkTo(parent.top)
                                        },
                                        onValueChange = {
                                            stringFilter = it
                                            showSuggest =
                                                if (stringFilter != "" || stringFilter.isNotEmpty()) {
                                                    suggestData.filterListAccounts(stringFilter) as ArrayList<SuggestionData>
                                                } else {
                                                    arrayListOf()
                                                }
                                            accountNumber = ""
                                        },
                                        onClearClick = {
                                            showSuggest = arrayListOf()
                                        },
                                        textValue = accountNumber,
                                        onFavoriteClick = {
                                            coroutineScope.launch {
                                                if (modalSheetState.isVisible)
                                                    modalSheetState.hide()
                                                else
                                                    modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                                            }
//                                            datePickerStatus = !datePickerStatus
                                        }
                                    )

                                    TextSurroundedWithImage(label = stringResource(id = R.string.title_bank_name),
                                        placeholder = stringResource(id = R.string.title_select_bank),
                                        cardModifier = Modifier.constrainAs(bankName) {
                                            top.linkTo(recipAcct.bottom)
                                        })

                                    Spacer(modifier = Modifier
                                        .constrainAs(spacer1) {
                                            top.linkTo(bankName.bottom)
                                        }
                                        .size(15.dp))

                                    InputAmount(label = stringResource(id = R.string.title_transfer_amount),
                                        placeholder = stringResource(id = R.string.title_input_amount),
                                        currency = stringResource(id = R.string.title_idr),
                                        cardModifier = Modifier.constrainAs(inputAmt) {
                                            top.linkTo(spacer1.bottom)
                                        })

                                    Spacer(modifier = Modifier
                                        .constrainAs(spacer2) {
                                            top.linkTo(inputAmt.bottom)
                                        }
                                        .size(16.dp))

                                    TextFieldRightImage(label = stringResource(id = R.string.title_label_message),
                                        placeholder = stringResource(id = R.string.title_placeholder_message),
                                        cardModifier = Modifier.constrainAs(inputMsg) {
                                            top.linkTo(spacer2.bottom)
                                        })

                                    Spacer(modifier = Modifier
                                        .constrainAs(spacer3) {
                                            top.linkTo(inputMsg.bottom)
                                        }
                                        .size(16.dp))

                                    TextRightImage(label = stringResource(id = R.string.title_purpose_transfer),
                                        placeholder = stringResource(id = R.string.title_default_purpose_transfer),
                                        imageResource = R.drawable.ic_drop_down_maroon,
                                        cardModifier = Modifier.constrainAs(purposeTrf) {
                                            top.linkTo(spacer3.bottom)
                                        })

                                    Spacer(modifier = Modifier
                                        .constrainAs(spacer4) {
                                            top.linkTo(purposeTrf.bottom)
                                        }
                                        .size(16.dp))

                                    if (showSuggest.isNotEmpty()) {
                                        suggestionList(
                                            modifier = Modifier
                                                .heightIn(min = 20.dp, max = 200.dp)
                                                .offset(y = (-15).dp)
                                                .background(Color(0xFFECECEC))
                                                .constrainAs(rvSuggest) {
                                                    top.linkTo(recipAcct.bottom)
                                                },
                                            state = suggestState,
                                            data = showSuggest,
                                            onSelectedItem = {
                                                accountNumber = it
                                                showSuggest = arrayListOf()
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                androidx.compose.material3.Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(62.dp)
                        .padding(bottom = 8.dp, top = 8.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFF55753), Color(0xFF961917)
                                )
                            ), shape = RoundedCornerShape(50.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    androidx.compose.material3.Text(
                        text = stringResource(id = R.string.title_next),
                        style = TextStyle(
                            fontFamily = OpensansFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            letterSpacing = 0.25.sp,
                        ),
                        color = Color.White,
                    )
                }

                if (datePickerStatus) {
                    datePickerDialog {
                        datePickerStatus = !datePickerStatus
                    }
                }
            }
        }
    }
}

@Composable
fun suggestionList(
    modifier: Modifier, state: LazyListState, data: ArrayList<SuggestionData>,
    onSelectedItem: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier, state = state
    ) {
        itemsIndexed(data) { _, item ->
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .clickableWithoutRipple {
                        onSelectedItem(item.accountNumber!!)
                    }
            ) {
                Image(
                    painter = painterResource(id = item.imageBank!!),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.padding(start = 5.dp)
                )

                androidx.compose.material3.Text(
                    text = item.accountName!!, style = TextStyle(
                        fontFamily = OpensansFontBold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        letterSpacing = 0.15.sp,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF000000)
                    ), modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically)
                )

                androidx.compose.material3.Text(
                    text = " - " + item.accountNumber!!, style = TextStyle(
                        fontFamily = OpensansFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        letterSpacing = 0.15.sp,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF000000)
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Divider(color = Color(0xFFC1C1C1))
        }
    }
}

private fun List<SuggestionData>?.filterListAccounts(query: String): List<SuggestionData> {
    this?.let { listFavorite ->
        return listFavorite.filter {
            it.accountName!!.contains(query, true) || it.accountNumber!!.contains(query, true)
        }
    }
    return listOf()
}


@Preview
@Composable
fun PreviewBottomSheet() {
    TrialJetpackComposeTheme {
        BottomSheetLayout()
    }
}