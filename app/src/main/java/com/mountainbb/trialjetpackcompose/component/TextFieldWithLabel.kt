package com.mountainbb.trialjetpackcompose.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.ui.theme.MontserratFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.welcome.Keyboard

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithLabel(
    labelTextField: String,
    value: String = "",
    enabled: Boolean = true,
    inputType: InputTypeClass = InputTypeClass.Text,
    maxChar: Int = 200
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        var valueEdit = remember {
            mutableStateOf(TextFieldValue(value))
        }
        val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
        val interactionSource = remember { MutableInteractionSource() }
        val colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Gray)
        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        Text(
            text = labelTextField,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            color = Color(0xFF6B6A6A),
            style = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
            )
        )

        BasicTextField(
            value = valueEdit.value,
            onValueChange = {
                if (it.text.length <= maxChar){
                    valueEdit.value = it
                }
            },
            visualTransformation  = handleVisualTransform(passwordVisible, inputType),
            modifier = Modifier
                .fillMaxWidth()
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
                color = Color(0xFF1273A7)
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = handleKeyboardType(inputType)
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            enabled = enabled
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
                trailingIcon = {
                    if (inputType == InputTypeClass.Password) {
                        val image = if (passwordVisible) R.drawable.visibility_on
                        else R.drawable.visibility_off

                        // Please provide localized description for accessibility services
                        val description = if (passwordVisible) "Hide password" else "Show password"

                        IconButton(
                            onClick = {passwordVisible = !passwordVisible},
                            modifier = Modifier
                                .size(20.dp)
                        ) {
                            Icon(painter = painterResource(id = image), description)
                        }
                    }
                },
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

enum class InputTypeClass {
    Text, Password
}

fun handleVisualTransform(passwordVisible: Boolean, inputType: InputTypeClass) : VisualTransformation {
    if (inputType == InputTypeClass.Password) {
        return if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    }
    else {
        return VisualTransformation.None
    }
}

fun handleKeyboardType(inputType: InputTypeClass): KeyboardType {
    return if (inputType == InputTypeClass.Text) {
        KeyboardType.Text
    }
    else {
        KeyboardType.NumberPassword
    }
}