package com.project.giunne.android

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.project.giunne.R
import com.project.giunne.common.util.GLog

private const val TAG = "GPFirebaseMessagingService"
class GPFirebaseMessageService : FirebaseMessagingService() {
    // 새로운 토큰이 생성될 때 마다 해당 콜백이 호출된다.
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        GLog.d(TAG, "onNewToken: $token")
        // 새로운 토큰 수신 시 서버로 전송
//        MainActivity.uploadToken(token) // TODO API
    }

    // Foreground에서 Push Service를 받기 위해 Notification 설정
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        GLog.d(TAG, "onMessageReceived :: ${remoteMessage.notification?.body}")
        remoteMessage.notification?.apply {
            val intent = Intent(this@GPFirebaseMessageService, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(
                this@GPFirebaseMessageService,
                101,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
            val builder =
                NotificationCompat.Builder(this@GPFirebaseMessageService, MainActivity.channel_id)
//                    .setSmallIcon(R.mipmap.icon_app_small)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setContentIntent(pendingIntent)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setAutoCancel(true)

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(101, builder.build())
        }
    }
}