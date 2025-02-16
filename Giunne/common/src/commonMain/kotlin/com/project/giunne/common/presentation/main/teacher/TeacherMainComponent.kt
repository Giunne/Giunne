package com.project.giunne.common.presentation.main.teacher

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.project.giunne.common.presentation.certification.student.StudentCertificationComponent
import com.project.giunne.common.presentation.community.student.StudentCommunityComponent
import com.project.giunne.common.presentation.community.student.dummy.CommunityDto
import com.project.giunne.common.presentation.friend.student.StudentFriendComponent
import com.project.giunne.common.presentation.home.student.StudentHomeComponent
import com.project.giunne.common.presentation.mypage.student.StudentMyPageComponent
import com.project.giunne.common.presentation.roadmap.student.StudentRoadmapComponent
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

class TeacherMainComponent(
    componentContext: ComponentContext
): KoinComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<TeacherMainConfig>()

    private val stack =
        childStack(
            source = navigation,
            serializer = TeacherMainConfig.serializer(),
            initialStack = { listOf(TeacherMainConfig.Home) },
            childFactory = ::child,
        )

    val childStack: Value<ChildStack<*, TeacherChild>> = stack

    sealed class TeacherChild {
        class TeacherHomeChild(val component: StudentHomeComponent): TeacherChild()
        class TeacherRoadmapChild(val component: StudentRoadmapComponent) : TeacherChild()
        class TeacherCertificationChild(val component: StudentCertificationComponent) : TeacherChild()
        class TeacherCommunityChild(val component: StudentCommunityComponent) : TeacherChild()
        class TeacherCommunityDetailChild(val communityDto: CommunityDto) : TeacherChild()
        class TeacherFriendsChild(val component: StudentFriendComponent) : TeacherChild()
        class TeacherMyPageChild(val component: StudentMyPageComponent) : TeacherChild()
        class TeacherShopChild(val component: StudentMyPageComponent) : TeacherChild()
        class TeacherGachaChild(val component: StudentMyPageComponent) : TeacherChild()
    }

    private fun child(config: TeacherMainConfig, componentContext: ComponentContext): TeacherChild =
        when (config) {
            is TeacherMainConfig.Home -> TeacherChild.TeacherHomeChild(StudentHomeComponent(componentContext))
            is TeacherMainConfig.Roadmap -> TeacherChild.TeacherRoadmapChild(StudentRoadmapComponent(componentContext))
            is TeacherMainConfig.Certification -> TeacherChild.TeacherCertificationChild(
                StudentCertificationComponent(componentContext)
            )
            is TeacherMainConfig.Community -> TeacherChild.TeacherCommunityChild(
                StudentCommunityComponent(componentContext)
            )
            is TeacherMainConfig.CommunityDetail -> TeacherChild.TeacherCommunityDetailChild(config.communityDto)
            is TeacherMainConfig.Friends -> TeacherChild.TeacherFriendsChild(StudentFriendComponent(componentContext))
            is TeacherMainConfig.MyPage -> TeacherChild.TeacherMyPageChild(StudentMyPageComponent(componentContext))
            is TeacherMainConfig.Shop -> TeacherChild.TeacherShopChild(StudentMyPageComponent(componentContext))
            is TeacherMainConfig.Gacha -> TeacherChild.TeacherGachaChild(StudentMyPageComponent(componentContext))
        }

    @Serializable
    sealed interface TeacherMainConfig {
        @Serializable
        data object Home : TeacherMainConfig

        @Serializable
        data object Roadmap : TeacherMainConfig

        @Serializable
        data object Certification : TeacherMainConfig

        @Serializable
        data object Community : TeacherMainConfig

        @Serializable
        data class CommunityDetail(val communityDto: CommunityDto) : TeacherMainConfig

        @Serializable
        data object Friends : TeacherMainConfig

        @Serializable
        data object MyPage : TeacherMainConfig

        @Serializable
        data object Shop : TeacherMainConfig

        @Serializable
        data object Gacha : TeacherMainConfig
    }

    fun navigateToHome() {
        navigation.replaceAll(TeacherMainConfig.Home)
    }

    fun navigateToRoadmap() {
        navigation.replaceAll(TeacherMainConfig.Roadmap)
    }

    fun navigateToCertification() {
        navigation.replaceAll(TeacherMainConfig.Certification)
    }

    fun navigateToCommunity() {
        navigation.push(TeacherMainConfig.Community)
    }

    fun navigateToCommunityDetail(
        communityDto: CommunityDto
    ) {
        navigation.push(TeacherMainConfig.CommunityDetail(communityDto))
    }

    fun navigateToFriends() {
        navigation.replaceAll(TeacherMainConfig.Friends)
    }

    fun navigateToMyPage() {
        navigation.replaceAll(TeacherMainConfig.MyPage)
    }

    fun navigateToShop() {
        navigation.push(TeacherMainConfig.Shop)
    }

    fun navigateToGacha() {
        navigation.push(TeacherMainConfig.Gacha)
    }

    fun navigateBack() {
        navigation.pop()
    }
}