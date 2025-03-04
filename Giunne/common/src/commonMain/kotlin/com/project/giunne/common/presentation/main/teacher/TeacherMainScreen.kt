package com.project.giunne.common.presentation.main.teacher

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import com.arkivanov.decompose.FaultyDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.Direction
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.isEnter
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.certification.teacher.TeacherCertificationScreen
import com.project.giunne.common.presentation.common.badge.GPNotificationBadge
import com.project.giunne.common.presentation.common.button.GPBackButton
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.topbar.GPMainTopBar
import com.project.giunne.common.presentation.community.teacher.TeacherCommunityDetailScreen
import com.project.giunne.common.presentation.community.teacher.TeacherCommunityScreen
import com.project.giunne.common.presentation.friend.teacher.TeacherFriendScreen
import com.project.giunne.common.presentation.home.teacher.TeacherHomeScreen
import com.project.giunne.common.presentation.home.teacher.recreation.TeacherRecreationScreen
import com.project.giunne.common.presentation.main.common.NotificationScreen
import com.project.giunne.common.presentation.main.dummy.notiList
import com.project.giunne.common.presentation.mypage.teacher.TeacherMyPageScreen
import com.project.giunne.common.presentation.roadmap.teacher.TeacherRoadmapScreen
import com.project.giunne.common.presentation.shop.GachaScreen
import com.project.giunne.common.presentation.shop.ShopScreen
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.BackHandler
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.exitProgram
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_certification
import com.project.giunne.icon_friends
import com.project.giunne.icon_home
import com.project.giunne.icon_mypage
import com.project.giunne.icon_roadmap
import com.project.giunne.image_giunne
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun TeacherMainScreen(
    modifier: Modifier = Modifier,
    component: TeacherMainComponent,
    onLogout: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarState =  remember { SnackbarHostState() }
    var backPress by remember { mutableStateOf(false) }

    val childStack by component.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance

    //// TEST ////
    var noti by remember { mutableStateOf(false) }
    var notiAnim by remember { mutableStateOf(false) }
    val animatedDP by animateDpAsState(
        targetValue = if (notiAnim) 0.gdp else 400.gdp,
        animationSpec = tween(durationMillis = 150, easing = LinearEasing)
    )
    //////////////

    BackHandler {
        scope.launch {
            if (noti) {
                notiAnim = false
                delay(150)
                noti = false
            } else if (childStack.backStack.isNotEmpty()) {
                component.navigateBack()
            } else {
                if (backPress == false) {
                    backPress = true
                    snackbarState.showSnackbar("뒤로가기를 한번 더 누르면 종료됩니다.")
                    backPress = false
                } else {
                    exitProgram()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                snackbarState
            )
        }
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GPColor.BackgroundLightGray)
            ) {
                GPMainTopBar(
                    titleText = when (activeComponent) {
                        is TeacherMainComponent.TeacherChild.TeacherHomeChild -> ""
                        is TeacherMainComponent.TeacherChild.TeacherRoadmapChild -> "로드맵"
                        is TeacherMainComponent.TeacherChild.TeacherCertificationChild -> "인증"
                        is TeacherMainComponent.TeacherChild.TeacherCommunityChild -> "게시판"
                        is TeacherMainComponent.TeacherChild.TeacherCommunityDetailChild -> activeComponent.communityDto.content
                        is TeacherMainComponent.TeacherChild.TeacherFriendsChild -> "학생들"
                        is TeacherMainComponent.TeacherChild.TeacherMyPageChild -> "내정보"
                        is TeacherMainComponent.TeacherChild.TeacherShopChild -> "꾸미기"
                        is TeacherMainComponent.TeacherChild.TeacherGachaChild -> ""
                        is TeacherMainComponent.TeacherChild.TeacherRecreationChild -> "진행할 로드맵 변경"
                    },
                    leftIcon = {
                        when(activeComponent) {
                            is TeacherMainComponent.TeacherChild.TeacherCommunityChild,
                            is TeacherMainComponent.TeacherChild.TeacherCommunityDetailChild,
                            is TeacherMainComponent.TeacherChild.TeacherShopChild,
                            is TeacherMainComponent.TeacherChild.TeacherGachaChild,
                            is TeacherMainComponent.TeacherChild.TeacherRecreationChild -> {
                                GPBackButton(
                                    onClick = {
                                        component.navigateBack()
                                    }
                                )
                            }
                            else -> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .aspectRatio(1f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        modifier = Modifier.size(32.gdp),
                                        painter = painterResource(Res.drawable.image_giunne),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    },
                    rightIcon = {
                        GPNotificationBadge(
                            count = notiList.filter { !it.isRead }.size,
                            onClick = {
                                noti = true
                                notiAnim = true
                            }
                        )
                    }
                )
                TeacherChildren(
                    modifier = Modifier
                        .weight(1f),
                    component = component,
                    activeComponent = activeComponent,
                    onLogout = { onLogout() }
                )

                when (activeComponent) {
                    is TeacherMainComponent.TeacherChild.TeacherCertificationChild,
                    is TeacherMainComponent.TeacherChild.TeacherCommunityChild,
                    is TeacherMainComponent.TeacherChild.TeacherFriendsChild,
                    is TeacherMainComponent.TeacherChild.TeacherHomeChild,
                    is TeacherMainComponent.TeacherChild.TeacherMyPageChild,
                    is TeacherMainComponent.TeacherChild.TeacherRoadmapChild -> {
                        TeacherBottomNav(
                            component = component,
                            activeComponent = activeComponent
                        )
                    }
                    is TeacherMainComponent.TeacherChild.TeacherShopChild -> Unit
                    is TeacherMainComponent.TeacherChild.TeacherGachaChild -> Unit
                    is TeacherMainComponent.TeacherChild.TeacherCommunityDetailChild -> Unit
                    is TeacherMainComponent.TeacherChild.TeacherRecreationChild -> Unit
                }
            }

            if (noti) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(x = animatedDP)
                ) {
                    GPMainTopBar(
                        titleText = "알림",
                        leftIcon = {
                            GPBackButton(
                                onClick = {
                                    scope.launch {
                                        notiAnim = false
                                        delay(150)
                                        noti = false
                                    }
                                }
                            )
                        },
                    )
                    NotificationScreen(
                        notificationItemList = notiList
                    )
                }
            }
        }
    }
}

@Composable
fun TeacherBottomNav(
    modifier: Modifier = Modifier,
    component: TeacherMainComponent,
    activeComponent: TeacherMainComponent.TeacherChild
) {
    Row(
        modifier = modifier
            .background(color = GPColor.BackgroundLightGray)
            .fillMaxWidth()
            .height(66.gdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        NavItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            title = "홈",
            icon = painterResource(Res.drawable.icon_home),
            onTop = activeComponent is TeacherMainComponent.TeacherChild.TeacherHomeChild,
            onClick = {
                if (activeComponent !is TeacherMainComponent.TeacherChild.TeacherHomeChild)
                    component.navigateToHome()
            },
        )
        NavItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            title = "로드맵",
            icon = painterResource(Res.drawable.icon_roadmap),
            onTop = activeComponent is TeacherMainComponent.TeacherChild.TeacherRoadmapChild,
            onClick = {
                if (activeComponent !is TeacherMainComponent.TeacherChild.TeacherRoadmapChild)
                    component.navigateToRoadmap()
            },
        )
        NavItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            title = "인증",
            icon = painterResource(Res.drawable.icon_certification),
            onTop = activeComponent is TeacherMainComponent.TeacherChild.TeacherCertificationChild
                    || activeComponent is TeacherMainComponent.TeacherChild.TeacherCommunityChild
                    || activeComponent is TeacherMainComponent.TeacherChild.TeacherCommunityDetailChild,
            onClick = {
                if (activeComponent !is TeacherMainComponent.TeacherChild.TeacherCertificationChild)
                    component.navigateToCertification()
            },
        )
        NavItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            title = "학생들",
            icon = painterResource(Res.drawable.icon_friends),
            onTop = activeComponent is TeacherMainComponent.TeacherChild.TeacherFriendsChild,
            onClick = {
                if (activeComponent !is TeacherMainComponent.TeacherChild.TeacherFriendsChild)
                    component.navigateToFriends()
            },
        )
        NavItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            title = "내정보",
            icon = painterResource(Res.drawable.icon_mypage),
            onTop = activeComponent is TeacherMainComponent.TeacherChild.TeacherMyPageChild
                    || activeComponent is TeacherMainComponent.TeacherChild.TeacherShopChild
                    || activeComponent is TeacherMainComponent.TeacherChild.TeacherGachaChild,
            onClick = {
                if (activeComponent !is TeacherMainComponent.TeacherChild.TeacherMyPageChild)
                    component.navigateToMyPage()
            },
        )
    }
}

@Composable
fun NavItem(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    title: String,
    icon: Painter,
    onTop: Boolean,
    onClick: () -> Unit
) {
    val iconColor by animateColorAsState(
        targetValue = when {
            onTop -> GPColor.MainOrangeColor
            else -> GPColor.ButtonGray
        },
    )

    Box(
        modifier = modifier
            .noRippleClickable(interactionSource = interactionSource) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier,
                painter = icon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = iconColor)
            )
            if (onTop) {
                SpH(4.gdp)
                GPText(
                    modifier = Modifier,
                    text = title,
                    textSize = 12.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = iconColor
                )
            }
        }
    }
}

@Composable
private fun TeacherChildren(
    component: TeacherMainComponent,
    modifier: Modifier = Modifier,
    activeComponent: TeacherMainComponent.TeacherChild,
    onLogout: () -> Unit
) {
    Children(
        stack = component.childStack,
        modifier = modifier,
//        animation = stackAnimation(fade()),
        animation = tabAnimation()
    ) {
        when (val child = it.instance) {
            is TeacherMainComponent.TeacherChild.TeacherHomeChild -> TeacherHomeScreen(
                component = child.component,
                navigateToCommunity = {
                    component.navigateToCommunity(CertPage.RoadMap)
                },
                navigateToRecreation = {
                    component.navigateToRecreation()
                }
            )
            is TeacherMainComponent.TeacherChild.TeacherRoadmapChild -> TeacherRoadmapScreen(component = child.component)
            is TeacherMainComponent.TeacherChild.TeacherCertificationChild -> TeacherCertificationScreen(
                component = child.component,
                onCommunityButtonClicked = { type ->
                    component.navigateToCommunity(type)
                },
                navigateToDetail = {communityDto ->
                    component.navigateToCommunityDetail(communityDto)
                }
            )
            is TeacherMainComponent.TeacherChild.TeacherCommunityChild -> TeacherCommunityScreen(
                component = child.component,
                navigateToDetail = { communityDto ->
                    component.navigateToCommunityDetail(communityDto)
                },
                pageType = if (activeComponent is TeacherMainComponent.TeacherChild.TeacherCommunityChild)
                    activeComponent.pageType else CertPage.RoadMap
            )
            is TeacherMainComponent.TeacherChild.TeacherCommunityDetailChild -> TeacherCommunityDetailScreen(
                communityDto = if (activeComponent is TeacherMainComponent.TeacherChild.TeacherCommunityDetailChild)
                    activeComponent.communityDto else null
            )
            is TeacherMainComponent.TeacherChild.TeacherFriendsChild -> TeacherFriendScreen(component = child.component)
            is TeacherMainComponent.TeacherChild.TeacherMyPageChild -> TeacherMyPageScreen(
                component = child.component,
                navigateToShop = { component.navigateToShop() },
                navigateToGacha = { component.navigateToGacha() },
                onLogout = { onLogout() }
            )
            is TeacherMainComponent.TeacherChild.TeacherShopChild -> ShopScreen()
            is TeacherMainComponent.TeacherChild.TeacherGachaChild -> GachaScreen {

            }
            is TeacherMainComponent.TeacherChild.TeacherRecreationChild -> TeacherRecreationScreen(
                component = child.component,
                onBackClick = {
                    component.navigateBack()
                }
            )
        }
    }
}

@OptIn(FaultyDecomposeApi::class)
@Composable
private fun tabAnimation(): StackAnimation<Any, TeacherMainComponent.TeacherChild> =
    stackAnimation { child, otherChild, direction ->
        val index = child.instance.index
        val otherIndex = otherChild.instance.index
        val anim = slide()
        if ((index > otherIndex) == direction.isEnter) anim else anim.flipSide()
    }

private val TeacherMainComponent.TeacherChild.index: Int
    get() =
        when (this) {
            is TeacherMainComponent.TeacherChild.TeacherHomeChild -> 0
            is TeacherMainComponent.TeacherChild.TeacherRoadmapChild -> 1
            is TeacherMainComponent.TeacherChild.TeacherCertificationChild -> 2
            is TeacherMainComponent.TeacherChild.TeacherCommunityChild -> 3
            is TeacherMainComponent.TeacherChild.TeacherCommunityDetailChild -> 4
            is TeacherMainComponent.TeacherChild.TeacherFriendsChild -> 5
            is TeacherMainComponent.TeacherChild.TeacherMyPageChild -> 6
            is TeacherMainComponent.TeacherChild.TeacherShopChild -> 7
            is TeacherMainComponent.TeacherChild.TeacherGachaChild -> 8
            is TeacherMainComponent.TeacherChild.TeacherRecreationChild -> 9
        }

private fun StackAnimator.flipSide(): StackAnimator =
    StackAnimator { direction, isInitial, onFinished, content ->
        invoke(
            direction = direction.flipSide(),
            isInitial = isInitial,
            onFinished = onFinished,
            content = content,
        )
    }

private fun Direction.flipSide(): Direction =
    when (this) {
        Direction.ENTER_FRONT -> Direction.ENTER_BACK
        Direction.EXIT_FRONT -> Direction.EXIT_FRONT
        Direction.ENTER_BACK -> Direction.ENTER_BACK
        Direction.EXIT_BACK -> Direction.EXIT_FRONT
    }