package com.project.giunne.common.presentation.main.student

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.project.giunne.common.presentation.certification.student.StudentCertificationComponent
import com.project.giunne.common.presentation.community.student.StudentCommunityComponent
import com.project.giunne.common.presentation.community.student.dummy.CommunityDto
import com.project.giunne.common.presentation.friend.student.StudentFriendComponent
import com.project.giunne.common.presentation.home.student.StudentHomeComponent
import com.project.giunne.common.presentation.mypage.student.StudentMyPageComponent
import com.project.giunne.common.presentation.roadmap.student.StudentRoadmapComponent
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNull.content
import org.koin.core.component.KoinComponent

class StudentMainComponent(
    componentContext: ComponentContext
): KoinComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<StudentMainConfig>()

    private val stack =
        childStack(
            source = navigation,
            serializer = StudentMainConfig.serializer(),
            initialStack = { listOf(StudentMainConfig.Home) },
            childFactory = ::child,
        )

    val childStack: Value<ChildStack<*, StudentChild>> = stack

    sealed class StudentChild {
        class StudentHomeChild(val component: StudentHomeComponent): StudentChild()
        class StudentRoadmapChild(val component: StudentRoadmapComponent) : StudentChild()
        class StudentCertificationChild(val component: StudentCertificationComponent) : StudentChild()
        class StudentCommunityChild(val component: StudentCommunityComponent) : StudentChild()
        class StudentCommunityDetailChild(val communityDto: CommunityDto) : StudentChild()
        class StudentFriendsChild(val component: StudentFriendComponent) : StudentChild()
        class StudentMyPageChild(val component: StudentMyPageComponent) : StudentChild()
        class StudentShopChild(val component: StudentMyPageComponent) : StudentChild()
        class StudentGachaChild(val component: StudentMyPageComponent) : StudentChild()
    }

    private fun child(config: StudentMainConfig, componentContext: ComponentContext): StudentChild =
        when (config) {
            is StudentMainConfig.Home -> StudentChild.StudentHomeChild(StudentHomeComponent(componentContext))
            is StudentMainConfig.Roadmap -> StudentChild.StudentRoadmapChild(StudentRoadmapComponent(componentContext))
            is StudentMainConfig.Certification -> StudentChild.StudentCertificationChild(StudentCertificationComponent(componentContext))
            is StudentMainConfig.Community -> StudentChild.StudentCommunityChild(StudentCommunityComponent(componentContext))
            is StudentMainConfig.CommunityDetail -> StudentChild.StudentCommunityDetailChild(config.communityDto)
            is StudentMainConfig.Friends -> StudentChild.StudentFriendsChild(StudentFriendComponent(componentContext))
            is StudentMainConfig.MyPage -> StudentChild.StudentMyPageChild(StudentMyPageComponent(componentContext))
            is StudentMainConfig.Shop -> StudentChild.StudentShopChild(StudentMyPageComponent(componentContext))
            is StudentMainConfig.Gacha -> StudentChild.StudentGachaChild(StudentMyPageComponent(componentContext))
        }

    @Serializable
    sealed interface StudentMainConfig {
        @Serializable
        data object Home : StudentMainConfig

        @Serializable
        data object Roadmap : StudentMainConfig

        @Serializable
        data object Certification : StudentMainConfig

        @Serializable
        data object Community : StudentMainConfig

        @Serializable
        data class CommunityDetail(val communityDto: CommunityDto) : StudentMainConfig

        @Serializable
        data object Friends : StudentMainConfig

        @Serializable
        data object MyPage : StudentMainConfig

        @Serializable
        data object Shop : StudentMainConfig

        @Serializable
        data object Gacha : StudentMainConfig
    }

    fun navigateToHome() {
        navigation.replaceAll(StudentMainConfig.Home)
    }

    fun navigateToRoadmap() {
        navigation.replaceAll(StudentMainConfig.Roadmap)
    }

    fun navigateToCertification() {
        navigation.replaceAll(StudentMainConfig.Certification)
    }

    fun navigateToCommunity() {
        navigation.push(StudentMainConfig.Community)
    }

    fun navigateToCommunityDetail(
        communityDto: CommunityDto
    ) {
        navigation.push(StudentMainConfig.CommunityDetail(communityDto))
    }

    fun navigateToFriends() {
        navigation.replaceAll(StudentMainConfig.Friends)
    }

    fun navigateToMyPage() {
        navigation.replaceAll(StudentMainConfig.MyPage)
    }

    fun navigateToShop() {
        navigation.push(StudentMainConfig.Shop)
    }

    fun navigateToGacha() {
        navigation.push(StudentMainConfig.Gacha)
    }

    fun navigateBack() {
        navigation.pop()
    }
}