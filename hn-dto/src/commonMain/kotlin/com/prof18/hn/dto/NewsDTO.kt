package com.prof18.hn.dto

/*
   Example:
   {
    "by": "rbanffy",
    "id": 24401439,
    "score": 47,
    "time": 1599503461,
    "title": "Arm Announces Cortex-R82: First 64-Bit Real Time Processor",
    "type": "story",
    "url": "https://www.anandtech.com/show/16056/arm-announces-cortexr82-first-64bit-real-time-processor"
  },
 */
data class NewsDTO(
    val author: String,
    val id: Long,
    val score: Int,
    val timestamp: Long,
    val title: String,
    val type: String,
    val url: String
)