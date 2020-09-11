package com.prof18.hn.dto

open class NewsDTO(
    var author: String,
    var id: Long,
    var score: Int,
    var timestamp: Long,
    var title: String,
    var type: String,
    var url: String
): BaseDTO() {

    constructor() : this(
        author = "",
        id = -1,
        score = -1,
        timestamp = -1,
        title = "",
        type = "",
        url = ""
    ) {
        this.author = ""
        this.id = -1
        this.score = -1
        this.timestamp = -1
        this.title = ""
        this.type = ""
        this.url = ""
    }
}