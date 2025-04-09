package com.project.giunne.android

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.DefaultComponentContext
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration
import com.project.giunne.R
import com.project.giunne.common.data.local.preference.SettingRepository
import com.project.giunne.common.presentation.root.RootComponent
import com.project.giunne.common.presentation.root.RootContent
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.ui.theme.GiunnaeTheme
import com.project.giunne.common.util.GLog
import com.russhwolf.settings.SharedPreferencesSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private val settingsRepository by lazy {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val settings = SharedPreferencesSettings(sharedPrefs)
        SettingRepository(settings)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        NotifierManager.addListener(object : NotifierManager.Listener {
//            override fun onNewToken(token: String) {
//                GLog.d(TAG, "onNewToken: $token") //Update user token in the server if needed
//            }
//        })

        NotifierManager.initialize(
            configuration = NotificationPlatformConfiguration.Android(
                notificationIconResId = android.R.drawable.star_on,
                showPushNotification = true,
            )
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(channel_id, "기운내")
        }

        val root = RootComponent(
            componentContext = DefaultComponentContext(
                lifecycle = lifecycle,
            ),
            getFirebaseToken = { NotifierManager.getPushNotifier().getToken() }
        )

        // 앱이 콘텐츠를 그리는 위치를 완전히 제어할 수 있도록 하기 위한 설정
        // 이 호출을 통해 앱이 시스템 UI 뒤에 표시되도록 요청
        WindowCompat.setDecorFitsSystemWindows(window, false)
//        window.statusBarColor = android.graphics.Color.WHITE
        window.statusBarColor = GPColor.BackgroundLightGray.toArgb()
        window.navigationBarColor = GPColor.BackgroundLightGray.toArgb()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setContent {
            LaunchedEffect(Unit) {
                this@MainActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }

            GiunnaeTheme {
                RootContent(
                    root,
                    modifier = Modifier.systemBarsPadding(),
                    exitProgram = { this@MainActivity.finishAffinity() },
                    getFirebaseToken = {
                        NotifierManager.getPushNotifier().getToken()
                    }
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    // Notification 수신을 위한 체널 추가
    private fun createNotificationChannel(id: String, name: String) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)

        val notificationManager: NotificationManager
                = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
        GLog.d(TAG, "createChannel :: $channel")
    }

    companion object {
        val channel_id = "giunne_channel"
    }
}