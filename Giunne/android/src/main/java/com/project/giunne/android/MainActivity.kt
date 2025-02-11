package com.project.giunne.android

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.DefaultComponentContext
import com.project.giunne.common.data.local.preference.SettingRepository
import com.project.giunne.common.presentation.root.RootComponent
import com.project.giunne.common.presentation.root.RootContent
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.ui.theme.GiunnaeTheme
import com.russhwolf.settings.SharedPreferencesSettings

class MainActivity : AppCompatActivity() {
    private val settingsRepository by lazy {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val settings = SharedPreferencesSettings(sharedPrefs)
        SettingRepository(settings)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = RootComponent(
            componentContext = DefaultComponentContext(
                lifecycle = lifecycle,
            ),
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
                    exitProgram = { this@MainActivity.finishAffinity() }
                )
            }
        }
    }
}