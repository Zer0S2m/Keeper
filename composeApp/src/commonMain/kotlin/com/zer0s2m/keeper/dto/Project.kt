package com.zer0s2m.keeper.dto

data class Project(

    override val id: Long,

    val organizationID: Long,

    val title: String

) : Model
