package com.prof18.hn.dto

import co.touchlab.stately.freeze
import kotlinx.serialization.json.Json

abstract class BaseDTO {

    @Throws(Exception::class)
    abstract fun deserialize(jsonString: String): BaseDTO

    protected val json = Json { ignoreUnknownKeys = true }

    fun makeFrozen() {
        freeze()
    }
}