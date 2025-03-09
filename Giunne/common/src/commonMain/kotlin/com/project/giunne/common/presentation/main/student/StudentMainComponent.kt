package com.project.giunne.common.presentation.main.student

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popWhile
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.project.giunne.common.data.remote.request.GachaType
import com.project.giunne.common.presentation.certification.student.StudentCertificationComponent
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.community.student.StudentCommunityComponent
import com.project.giunne.common.presentation.community.student.dummy.CommunityDto
import com.project.giunne.common.presentation.friend.student.StudentFriendComponent
import com.project.giunne.common.presentation.home.student.home.StudentHomeComponent
import com.project.giunne.common.presentation.home.student.join.StudentJoinRecreationComponent
import com.project.giunne.common.presentation.home.student.search.SearchRoadMapComponent
import com.project.giunne.common.presentation.mypage.student.StudentMyPageComponent
import com.project.giunne.common.presentation.roadmap.student.StudentRoadmapComponent
import com.project.giunne.common.presentation.select.StudentSelectCharacterComponent
import kotlinx.serialization.Serializable
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
        class SearchRoadMapChild(val component: SearchRoadMapComponent): StudentChild()
        class StudentSelectCharacterChild(val component: StudentSelectCharacterComponent, val recreationId: Int): StudentChild()
        class StudentRoadmapChild(val component: StudentRoadmapComponent) : StudentChild()
        class StudentCertificationChild(val component: StudentCertificationComponent) : StudentChild()
        class StudentCommunityChild(val component: StudentCommunityComponent, val pageType: CertPage) : StudentChild()
        class StudentCommunityDetailChild(val communityDto: CommunityDto) : StudentChild()
        class StudentFriendsChild(val component: StudentFriendComponent) : StudentChild()
        class StudentMyPageChild(val component: StudentMyPageComponent) : StudentChild()
        class StudentShopChild : StudentChild()
        class StudentGachaChild : StudentChild()
        class StudentPickingItemChild(val gachaType: GachaType) : StudentChild()
        class StudentJoinRecreationChild(val component: StudentJoinRecreationComponent) : StudentChild()
    }

    private fun child(config: StudentMainConfig, componentContext: ComponentContext): StudentChild =
        when (config) {
            is StudentMainConfig.Home -> StudentChild.StudentHomeChild(StudentHomeComponent(componentContext))
            is StudentMainConfig.SearchRoadMap -> StudentChild.SearchRoadMapChild(SearchRoadMapComponent(componentContext))
            is StudentMainConfig.Roadmap -> StudentChild.StudentRoadmapChild(StudentRoadmapComponent(componentContext))
            is StudentMainConfig.Certification -> StudentChild.StudentCertificationChild(StudentCertificationComponent(componentContext))
            is StudentMainConfig.Community -> StudentChild.StudentCommunityChild(StudentCommunityComponent(componentContext), config.pageType)
            is StudentMainConfig.CommunityDetail -> StudentChild.StudentCommunityDetailChild(config.communityDto)
            is StudentMainConfig.Friends -> StudentChild.StudentFriendsChild(StudentFriendComponent(componentContext))
            is StudentMainConfig.MyPage -> StudentChild.StudentMyPageChild(StudentMyPageComponent(componentContext))
            is StudentMainConfig.Shop -> StudentChild.StudentShopChild()
            is StudentMainConfig.Gacha -> StudentChild.StudentGachaChild()
            is StudentMainConfig.SelectedCharacter -> StudentChild.StudentSelectCharacterChild(StudentSelectCharacterComponent(componentContext), config.recreationId)
            is StudentMainConfig.PickingItem -> StudentChild.StudentPickingItemChild(config.gachaType)
            is StudentMainConfig.JoinRecreation -> StudentChild.StudentJoinRecreationChild(StudentJoinRecreationComponent(componentContext))
        }

    @Serializable
    sealed interface StudentMainConfig {
        @Serializable
        data object Home : StudentMainConfig

        @Serializable
        data object SearchRoadMap : StudentMainConfig

        @Serializable
        data class SelectedCharacter(val recreationId: Int) : StudentMainConfig

        @Serializable
        data object Roadmap : StudentMainConfig

        @Serializable
        data object Certification : StudentMainConfig

        @Serializable
        data class Community(val pageType: CertPage) : StudentMainConfig

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

        @Serializable
        data class PickingItem(val gachaType: GachaType) : StudentMainConfig

        @Serializable
        data object JoinRecreation : StudentMainConfig
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

    fun navigateToCommunity(
        pageType: CertPage
    ) {
        navigation.push(StudentMainConfig.Community(pageType))
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

    fun navigateToSearchRoadMap() {
        navigation.push(StudentMainConfig.SearchRoadMap)
    }

    fun navigateToSelectedCharacter(
        recreationId: Int
    ) {
        navigation.push(StudentMainConfig.SelectedCharacter(recreationId))
    }

    fun navigateToPickingItem(
        gachaType: GachaType
    ) {
        navigation.push(StudentMainConfig.PickingItem(gachaType = gachaType))
    }

    fun navigateFromPickingItemToShop() {
        navigation.popWhile { config ->
            config !is StudentMainConfig.MyPage
        }
        navigation.push(StudentMainConfig.Shop)
    }

    fun navigateToJoinRecreation() {
        navigation.push(StudentMainConfig.JoinRecreation)
    }

    fun navigateBack() {
        navigation.pop()
    }
}