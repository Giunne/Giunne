package com.project.giunne.common.presentation.main.dummy

val notiList = listOf(
    Noti("선생님이 이벤트 퀘스트를 보냈어요.", "1시간 전", false),
    Noti("선생님이 공지사항을 보냈어요.", "1시간 전", false),
    Noti("선생님이 이벤트 퀘스트를 보냈어요.", "1시간 전", false),
    Noti("선생님이 공지사항을 보냈어요.", "2시간 전", true),
    Noti("선생님이 이벤트 퀘스트를 보냈어요.", "4시간 전", true),
    Noti("선생님이 공지사항을 보냈어요.", "5시간 전", true),
)

data class Noti(
    val content: String,
    val time: String,
    val isRead: Boolean
)