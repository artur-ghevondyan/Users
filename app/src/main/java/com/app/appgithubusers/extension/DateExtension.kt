package com.app.appgithubusers.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.parser(): Date? =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(this)

fun Date.formater(): String =
    SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(this)
