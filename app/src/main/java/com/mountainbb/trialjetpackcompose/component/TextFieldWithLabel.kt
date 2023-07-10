package com.mountainbb.trialjetpackcompose.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mountainbb.trialjetpackcompose.ui.theme.MontserratFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithLabel(
    labelTextField: String,
    placeHolder: String
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val valueEdit = remember {
            mutableStateOf(TextFieldValue(placeHolder))
        }

        val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
        val interactionSource = remember { MutableInteractionSource() }
        val colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Gray)

        Text(
            text = labelTextField,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally),
            color = Gray,
            style = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
            )
        )

        BasicTextField(
            value = valueEdit.value,
            onValueChange = {
                valueEdit.value = it
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
                .indicatorLine(
                    enabled = true,
                    isError = false,
                    colors = colors,
                    interactionSource = interactionSource,
                    focusedIndicatorLineThickness = 1.dp,
                    unfocusedIndicatorLineThickness = 1.dp
                )
                .align(Alignment.CenterHorizontally),
            textStyle = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        ) { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                label = { Text(text = "")},
                value = valueEdit.value.toString(),
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                singleLine = false,
                enabled = true,
                interactionSource = interactionSource,
                contentPadding =
                PaddingValues(0.dp), // this is how you can remove the padding
            )
        }
    }
}

@Preview
@Composable
fun PreviewTextFieldWithLabel(){
    TrialJetpackComposeTheme {
        TextFieldWithLabel("labelTextField", "placeHolder")
    }
}