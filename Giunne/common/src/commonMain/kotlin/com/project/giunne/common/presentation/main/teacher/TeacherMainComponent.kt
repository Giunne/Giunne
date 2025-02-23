package com.project.giunne.common.presentation.main.teacher

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.certification.teacher.TeacherCertificationComponent
import com.project.giunne.common.presentation.community.student.dummy.CommunityDto
import com.project.giunne.common.presentation.community.teacher.TeacherCommunityComponent
import com.project.giunne.common.presentation.friend.teacher.TeacherFriendComponent
import com.project.giunne.common.presentation.home.teacher.TeacherHomeComponent
import com.project.giunne.common.presentation.mypage.teacher.TeacherMyPageComponent
import com.project.giunne.common.presentation.roadmap.teacher.TeacherRoadmapComponent
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
        class TeacherHomeChild(val component: TeacherHomeComponent): TeacherChild()
        class TeacherRoadmapChild(val component: TeacherRoadmapComponent) : TeacherChild()
        class TeacherCertificationChild(val component: TeacherCertificationComponent) : TeacherChild()
        class TeacherCommunityChild(val component: TeacherCommunityComponent, val pageType: CertPage) : TeacherChild()
        class TeacherCommunityDetailChild(val communityDto: CommunityDto) : TeacherChild()
        class TeacherFriendsChild(val component: TeacherFriendComponent) : TeacherChild()
        class TeacherMyPageChild(val component: TeacherMyPageComponent) : TeacherChild()
        class TeacherShopChild(val component: TeacherMyPageComponent) : TeacherChild()
        class TeacherGachaChild(val component: TeacherMyPageComponent) : TeacherChild()
    }

    private fun child(config: TeacherMainConfig, componentContext: ComponentContext): TeacherChild =
        when (config) {
            is TeacherMainConfig.Home -> TeacherChild.TeacherHomeChild(TeacherHomeComponent(componentContext))
            is TeacherMainConfig.Roadmap -> TeacherChild.TeacherRoadmapChild(TeacherRoadmapComponent(componentContext))
            is TeacherMainConfig.Certification -> TeacherChild.TeacherCertificationChild(
                component = TeacherCertificationComponent(componentContext)
            )
            is TeacherMainConfig.Community -> TeacherChild.TeacherCommunityChild(
                component = TeacherCommunityComponent(componentContext),
                pageType = config.pageType
            )
            is TeacherMainConfig.CommunityDetail -> TeacherChild.TeacherCommunityDetailChild(config.communityDto)
            is TeacherMainConfig.Friends -> TeacherChild.TeacherFriendsChild(TeacherFriendComponent(componentContext))
            is TeacherMainConfig.MyPage -> TeacherChild.TeacherMyPageChild(TeacherMyPageComponent(componentContext))
            is TeacherMainConfig.Shop -> TeacherChild.TeacherShopChild(TeacherMyPageComponent(componentContext))
            is TeacherMainConfig.Gacha -> TeacherChild.TeacherGachaChild(TeacherMyPageComponent(componentContext))
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
        data class Community(val pageType: CertPage) : TeacherMainConfig

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

    fun navigateToCommunity(
        pageType: CertPage
    ) {
        navigation.push(TeacherMainConfig.Community(pageType))
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