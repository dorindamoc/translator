package com.example.translator_kmm.translate.data.local

import com.example.translator_kmm.database.TranslateDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver = NativeSqliteDriver(
        schema = TranslateDatabase.Schema, name = "translate.db"
    )
}