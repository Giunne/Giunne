package com.project.giunne.common.presentation.community.student.dummy

data class CommentDto(
    val name: String,
    val content: String,
    var like: Boolean = false,
)

val commentTestList = listOf(
    CommentDto("박준후", "요 아주 좋아요 아주 주 좋아요 아주 좋아요 아주 좋아요 "),
    CommentDto("박준후", "아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 "),
    CommentDto("박준후", "아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 "),
    CommentDto("박준후", "아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 아주 좋아요 "),
)