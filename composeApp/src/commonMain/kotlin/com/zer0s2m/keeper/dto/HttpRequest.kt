package com.zer0s2m.keeper.dto

import com.zer0s2m.keeper.enum.HttpMethod

data class HttpRequest(

    override val id: Long,

    val title: String,

    val method: HttpMethod,

    val collectionID: Long

) : Model
