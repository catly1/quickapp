package com.catly.quickapp.data.model

import java.util.*

data class RepositoryListItem(
    val name: String,
    val updated_at: Date,
    val description: String,
    val html_url: String
)