package com.mountainbb.trialjetpackcompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.ui.theme.OpensansFontFamily

@Composable
fun TrialTopAppBar(
    title: String? = null,
    leftIcon: Int? = null,
    leftIconAction: () -> Unit? = {},
    rightIcon: Int? = null,
    rightIconAction: () -> Unit? = {}
) {
    CenterAlignedTopAppBar(
        title = {
            title?.let {
                Text(
                    text = it,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 63.dp),
                    style = TextStyle(
                        fontFamily = OpensansFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
                )
            }
        },
        modifier = Modifier
            .height(100.dp)
            .background(
                Color(0xFF961917),
                shape = RoundedCornerShape(
                    bottomEnd = 25.dp, bottomStart = 25.dp
                )
            ),
        navigationIcon = {
            leftIcon?.let { ImageVector.vectorResource(id = it) }?.let {
                Image(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier
                        .width(40.dp)
                        .height(130.dp)
                        .padding(top = 60.dp, start = 10.dp, bottom = 10.dp)
                        .clickable {
                            leftIconAction
                        }
                )
            }
        },
        actions = {
            rightIcon?.let { painterResource(id = it) }?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier
                        .width(50.dp)
                        .height(150.dp)
                        .padding(top = 60.dp, start = 10.dp, bottom = 10.dp)
                        .clickable {
                            rightIconAction
                        }
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent),
        scrollBehavior = null
    )
}

@Preview
@Composable
fun PreviewTopAppBar() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TrialTopAppBar()
    }
}