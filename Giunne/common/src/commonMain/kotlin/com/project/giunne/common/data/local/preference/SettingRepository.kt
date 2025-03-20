package com.project.giunne.common.data.local.preference

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SettingsListener

class SettingRepository(
    private val settings: Settings
) {
//    public val mySettings: List<SettingConfig<*>> = listOf(
//        StringSettingConfig(settings, "MY_STRING", ""),
//        StringSettingConfig(settings, "EMAIL", "")
//    )
    val idPref: SettingConfig<*> = StringSettingConfig(settings, "ID", "")
    val accessTokenPref: SettingConfig<*> = StringSettingConfig(settings, "AC_TOKEN", "")
    val refreshTokenPref: SettingConfig<*> = StringSettingConfig(settings, "RE_TOKEN", "")
    val rolePref: SettingConfig<*> = StringSettingConfig(settings, "ROLE", "")
    val playerPref: SettingConfig<*> = LongSettingConfig(settings, "PLAYER", 0)
    val recreationIdPref: SettingConfig<*> = LongSettingConfig(settings, "RECREATION", 0)

    fun clear(): Unit = settings.clear()
}

sealed class SettingConfig<T>(
    private val settings: Settings,
    public val key: String,
    private val defaultValue: T
) {
    protected abstract fun getStringValue(settings: Settings, key: String, defaultValue: T): String
    protected abstract fun setStringValue(settings: Settings, key: String, value: String)
    protected abstract fun addListener(
        settings: ObservableSettings,
        key: String,
        defaultValue: T,
        callback: (T) -> Unit
    ): SettingsListener

    private var listener: SettingsListener? = null

    fun remove(): Unit = settings.remove(key)
    fun exists(): Boolean = settings.hasKey(key)

    fun get(): String = getStringValue(settings, key, defaultValue)
    fun set(value: String): Boolean {
        return try {
            setStringValue(settings, key, value)
            true
        } catch (exception: Exception) {
            false
        }
    }

    var isLoggingEnabled: Boolean
        get() = listener != null
        set(value) {
            val settings = settings as? ObservableSettings ?: return
            listener = if (value) {
                listener?.deactivate() // just in case
                addListener(settings, key, defaultValue) { println("$key = ${get()}") }
            } else {
                listener?.deactivate()
                null
            }
        }

    override fun toString(): String = key
}

class StringSettingConfig(settings: Settings, key: String, defaultValue: String) :
    SettingConfig<String>(settings, key, defaultValue) {

    override fun getStringValue(settings: Settings, key: String, defaultValue: String): String =
        settings.getString(key, defaultValue)

    override fun setStringValue(settings: Settings, key: String, value: String): Unit =
        settings.putString(key, value)

    override fun addListener(
        settings: ObservableSettings,
        key: String,
        defaultValue: String,
        callback: (String) -> Unit
    ): SettingsListener =
        settings.addStringListener(key, defaultValue, callback)
}

class BooleanSettingConfig(settings: Settings, key: String, defaultValue: Boolean) :
        SettingConfig<Boolean>(settings, key, defaultValue) {
    override fun getStringValue(settings: Settings, key: String, defaultValue: Boolean): String =
        settings.getBoolean(key, defaultValue).toString()

    override fun setStringValue(settings: Settings, key: String, value: String): Unit =
        settings.putBoolean(key, value.toBoolean())

    override fun addListener(
        settings: ObservableSettings,
        key: String,
        defaultValue: Boolean,
        callback: (Boolean) -> Unit
    ): SettingsListener =
        settings.addBooleanListener(key, defaultValue, callback)
}

class LongSettingConfig(settings: Settings, key: String, defaultValue: Long) :
    SettingConfig<Long>(settings, key, defaultValue) {
    override fun getStringValue(settings: Settings, key: String, defaultValue: Long): String =
        settings.getLong(key, defaultValue).toString()

    override fun setStringValue(settings: Settings, key: String, value: String): Unit =
        settings.putLong(key, value.toLong())

    override fun addListener(
        settings: ObservableSettings,
        key: String,
        defaultValue: Long,
        callback: (Long) -> Unit
    ): SettingsListener =
        settings.addLongListener(key, defaultValue, callback)
}