package com.prof18.hn.dto

open class ListResponseDTO<T>(
    var items: List<T>
): BaseDTO() {
    constructor(): this(listOf())
}