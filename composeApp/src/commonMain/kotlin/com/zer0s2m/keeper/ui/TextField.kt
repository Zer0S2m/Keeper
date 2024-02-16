package com.zer0s2m.keeper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TextFieldURLHttpRequest(
    url: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    val colors: TextFieldColors = TextFieldDefaults.textFieldColors()

    BasicTextField(
        value = url,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.None
        ),
        textStyle = LocalTextStyle
            .current
            .merge(
                TextStyle(
                color = colors.textColor(true).value,
                fontSize = 14.sp
            )
            ),
        cursorBrush = SolidColor(
            TextFieldDefaults
            .textFieldColors()
            .cursorColor(false).value),
        modifier = modifier
            .background(
                colors.backgroundColor(true).value, RoundedCornerShape(4.dp)
            ),
        singleLine = true,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = url,
                enabled = true,
                innerTextField = innerTextField,
                singleLine = true,
                interactionSource = remember { MutableInteractionSource() },
                visualTransformation = VisualTransformation.None,
                contentPadding = PaddingValues(start = 8.dp)
            )
        }
    )
}

