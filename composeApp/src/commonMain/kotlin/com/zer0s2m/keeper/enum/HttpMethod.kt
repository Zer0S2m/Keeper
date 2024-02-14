package com.zer0s2m.keeper.enum

import androidx.compose.ui.graphics.Color

enum class HttpMethod(color: Color) {

    GET(color = Color(red = 97, green = 175, blue = 254)),

    POST(color = Color(red = 73, green = 204, blue = 144)),

    PUT(color = Color(red = 252, green = 161, blue = 48)),

    PATCH(color = Color(red = 80, green = 227, blue = 194)),

    DELETE(color = Color(red = 249, green = 62, blue = 62)),

    OPTIONS(color = Color(red = 13, green = 92, blue = 167)),

    HEAD(color = Color(red = 144, green = 39, blue = 250))

}