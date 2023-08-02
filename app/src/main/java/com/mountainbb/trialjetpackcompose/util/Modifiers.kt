package com.mountainbb.trialjetpackcompose.util

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.regex.Pattern

fun Modifier.supportWideScreen() = this
    .fillMaxWidth()
    .wrapContentWidth(align = Alignment.CenterHorizontally)
    .widthIn(max = 840.dp)


@Composable
fun Modifier.clickableWithoutRipple(onClick: () -> Unit): Modifier = composed {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    return@composed then(clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
    ))
}

fun Modifier.customDialogModifier(pos: CustomDialogPosition) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints);
    layout(constraints.maxWidth, constraints.maxHeight){
        when(pos) {
            CustomDialogPosition.BOTTOM -> {
                placeable.place(0, constraints.maxHeight - placeable.height, 10f)
            }
            CustomDialogPosition.TOP -> {
                placeable.place(0,0,10f)
            }
        }
    }
}
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun validatePasteAmount(s: Editable) {
    s.filters = arrayOf(
        maxLength(),
        InputFilter.LengthFilter(15), InputFilter { source, _, _, _, _, _ ->
            val phone = source.toString().replace("[^\\d.]".toRegex(), "")
            phone
        })
}

fun maxLength(): InputFilter {
    return InputFilter { source, start, end, _, _, _ ->
        for (i in start until end) {
            if (!Pattern.compile("[1234567890]*")
                    .matcher(
                        source[i].toString().digitsOnly()
                    ).matches()
            ) {
                return@InputFilter ""
            }
        }
        null
    }
}

fun String.digitsOnly(): String{
    val regex = Regex("[^0-9]")
    return regex.replace(this, "")
}

fun Double.currencyFormatter(): String {
    val myFormatter = DecimalFormat("###,###,###")
    return myFormatter.format(this).replace(".", ",")
}

class ThousandSeparatorTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {

        val symbols = DecimalFormat().decimalFormatSymbols
        val decimalSeparator = symbols.exponentSeparator

        var outputText = ""
        var integerPart = 0L
        var decimalPart = ""

        if (text.text.isNotEmpty()) {
            val number = text.text.toDouble()
            integerPart = number.toLong()
            outputText += NumberFormat.getIntegerInstance().format(integerPart)
            if (text.text.contains(decimalSeparator)) {
                decimalPart = text.text.substring(text.text.indexOf(decimalSeparator))
                if (decimalPart.isNotEmpty()) {
                    outputText += decimalPart
                }
            }
        }

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return outputText.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                return text.length
            }
        }

        return TransformedText(
            text = AnnotatedString(outputText.replace(".", ",")),
            offsetMapping = numberOffsetTranslator
        )
    }
}