package com.project.giunne.common.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.navigate
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.project.giunne.common.presentation.login.LoginComponent
import com.project.giunne.common.presentation.main.student.StudentMainComponent
import com.project.giunne.common.presentation.main.teacher.TeacherMainComponent
import com.project.giunne.common.presentation.signup.SignupComponent
import com.project.giunne.common.util.PreferencesUtil
import kotlinx.serialization.Serializable

private const val TAG = "RootComponent"
public class RootComponent(
    componentContext: ComponentContext
): ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    private val stack =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialStack = { listOf(Config.Splash) },
            childFactory = ::child,
        )

    val childStack: Value<ChildStack<*, Child>> = stack

    sealed class Child {
        class SplashChild: Child()
        class LoginChild(val component: LoginComponent) : Child()
        class SignupTypeChild(): Child()
        class SignupChild(val component: SignupComponent, val type: String) : Child()
        class StudentMainChild(val component: StudentMainComponent) : Child()
        class TeacherMainChild(val component: TeacherMainComponent) : Child()
    }

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.Splash -> Child.SplashChild()
            is Config.Login -> Child.LoginChild(
                LoginComponent(
                    componentContext = componentContext,
                    prefRepository = PreferencesUtil.settingsRepository!!,
                    goToStudentMain = { navigateToStudentMain() },
                    goToTeacherMain = { navigateToTeacherMain() },
                )
            )
            is Config.SignupType -> Child.SignupTypeChild()
            is Config.Signup -> Child.SignupChild(SignupComponent(componentContext), config.type)
            is Config.StudentMain -> Child.StudentMainChild(StudentMainComponent(componentContext))
            is Config.TeacherMain -> Child.TeacherMainChild(TeacherMainComponent(componentContext))
        }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Splash : Config

        @Serializable
        data object Login : Config

        @Serializable
        data object SignupType : Config

        @Serializable
        data class Signup(val type: String) : Config

        @Serializable
        data object StudentMain : Config

        @Serializable
        data object TeacherMain : Config
    }

    fun navigateBack() {
        navigation.pop()
    }

    fun navigateToLogin() {
        navigation.replaceCurrent(Config.Login)
    }

    fun navigateToSignupType() {
        navigation.navigate {
            it.toMutableList().apply {
                add(Config.SignupType)
            }
        }
    }

    fun navigateToSignup(type: String) {
        navigation.navigate {
            it.toMutableList().apply {
                add(Config.Signup(type))
            }
        }
    }

    fun navigateToStudentMain() {
        navigation.replaceCurrent(Config.StudentMain)
    }

    fun navigateToTeacherMain() {
        navigation.replaceCurrent(Config.TeacherMain)
    }
}