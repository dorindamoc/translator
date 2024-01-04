package com.example.translator_kmm.translate.data.local

import android.content.Context
import com.example.translator_kmm.database.TranslateDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver = AndroidSqliteDriver(
        schema = TranslateDatabase.Schema,
        context = context,
        name = "translate.db"
    )
}