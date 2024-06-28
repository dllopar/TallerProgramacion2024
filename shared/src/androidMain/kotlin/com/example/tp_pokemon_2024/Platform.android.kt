package com.example.tp_pokemon_2024

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import com.example.pokedex.Database

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}


actual fun initLogger(){
    Napier.base(DebugAntilog())
}

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            Database.Schema, context,
            "pokedex.db"
        )
    }
}