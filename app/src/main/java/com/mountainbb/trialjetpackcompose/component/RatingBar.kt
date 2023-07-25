package com.mountainbb.trialjetpackcompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.util.clickableWithoutRipple

@Composable
fun ratingBar(modifier: Modifier): Int {
    var ratingImage1 by remember { mutableStateOf(R.drawable.stars_unfilled) }
    var ratingImage2 by remember { mutableStateOf(R.drawable.stars_unfilled) }
    var ratingImage3 by remember { mutableStateOf(R.drawable.stars_unfilled) }
    var ratingImage4 by remember { mutableStateOf(R.drawable.stars_unfilled) }
    var ratingImage5 by remember { mutableStateOf(R.drawable.stars_unfilled) }
    var ratingValue by remember {
        mutableStateOf(0)
    }
    Row(
        modifier = modifier
            .width(200.dp)
            .height(40.dp)
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clickableWithoutRipple {
                    ratingImage1 = R.drawable.stars_filled
                    ratingImage2 = R.drawable.stars_unfilled
                    ratingImage3 = R.drawable.stars_unfilled
                    ratingImage4 = R.drawable.stars_unfilled
                    ratingImage5 = R.drawable.stars_unfilled

                    ratingValue = 1
                },
            painter = painterResource(ratingImage1),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .size(40.dp)
                .clickableWithoutRipple {
                    ratingImage1 = R.drawable.stars_filled
                    ratingImage2 = R.drawable.stars_filled
                    ratingImage3 = R.drawable.stars_unfilled
                    ratingImage4 = R.drawable.stars_unfilled
                    ratingImage5 = R.drawable.stars_unfilled

                    ratingValue = 2
                },
            painter = painterResource(ratingImage2),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .size(40.dp)
                .clickableWithoutRipple {
                    ratingImage1 = R.drawable.stars_filled
                    ratingImage2 = R.drawable.stars_filled
                    ratingImage3 = R.drawable.stars_filled
                    ratingImage4 = R.drawable.stars_unfilled
                    ratingImage5 = R.drawable.stars_unfilled

                    ratingValue = 3
                },
            painter = painterResource(ratingImage3),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .size(40.dp)
                .clickableWithoutRipple {
                    ratingImage1 = R.drawable.stars_filled
                    ratingImage2 = R.drawable.stars_filled
                    ratingImage3 = R.drawable.stars_filled
                    ratingImage4 = R.drawable.stars_filled
                    ratingImage5 = R.drawable.stars_unfilled

                    ratingValue = 4
                },
            painter = painterResource(ratingImage4),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .size(40.dp)
                .clickableWithoutRipple {
                    ratingImage1 = R.drawable.stars_filled
                    ratingImage2 = R.drawable.stars_filled
                    ratingImage3 = R.drawable.stars_filled
                    ratingImage4 = R.drawable.stars_filled
                    ratingImage5 = R.drawable.stars_filled

                    ratingValue = 5
                },
            painter = painterResource(ratingImage5),
            contentDescription = null
        )
    }
    return ratingValue
}


@Preview
@Composable
fun RatingBarPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ratingBar(modifier = Modifier)
    }
}