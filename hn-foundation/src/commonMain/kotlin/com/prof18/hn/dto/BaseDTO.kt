package com.prof18.hn.dto

import co.touchlab.stately.freeze

open class BaseDTO {
    fun makeFrozen() {
        freeze()
    }
}