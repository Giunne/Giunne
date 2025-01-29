package com.project.giunne.common.presentation.main.dummy

val notiList = listOf(
    Noti(NotiType.LoadMap, "홍길동님이 로드맵 인증을 올렸습니다.", "1시간 전", false),
    Noti(NotiType.Comment, "박준후님이 게시글에 댓글을 달았습니다.", "1시간 전", false),
    Noti(NotiType.LoadMap, "조영재님이 로드맵 인증을 올렸습니다.", "1시간 전", false),
    Noti(NotiType.Running, "김희웅님이 러닝 인증을 올렸습니다.", "2시간 전", true),
    Noti(NotiType.Comment, "홍길동님이 게시글에 댓글을 달았습니다.", "4시간 전", true),
    Noti(NotiType.LoadMap, "홍길동님이 로드맵 인증을 올렸습니다.", "5시간 전", true),
)

data class Noti(
    val type: NotiType,
    val content: String,
    val time: String,
    val isRead: Boolean
)

enum class NotiType {
    LoadMap, Comment, Running
}