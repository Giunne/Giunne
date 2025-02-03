package com.project.giunne.common.presentation.main.student

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
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
import androidx.compose.ui.unit.dp
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
import com.project.giunne.common.presentation.certification.student.StudentCertificationScreen
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.badge.GPNotificationBadge
import com.project.giunne.common.presentation.common.button.GPBackButton
import com.project.giunne.common.presentation.common.dropdown.GPDropdownMenu
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.topbar.GPMainTopBar
import com.project.giunne.common.presentation.community.student.StudentCommunityScreen
import com.project.giunne.common.presentation.friend.student.StudentFriendScreen
import com.project.giunne.common.presentation.home.student.StudentHomeScreen
import com.project.giunne.common.presentation.main.common.NotificationScreen
import com.project.giunne.common.presentation.main.dummy.notiList
import com.project.giunne.common.presentation.mypage.student.StudentMyPageScreen
import com.project.giunne.common.presentation.roadmap.student.StudentRoadmapScreen
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.BackHandler
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_back_arrow
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
fun StudentMainScreen(
    modifier: Modifier = Modifier,
    component: StudentMainComponent,
    exitProgram: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarState =  remember { SnackbarHostState() }
    var backPress by remember { mutableStateOf(false) }

    val childStack by component.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance
    var testOptionItem by remember { mutableStateOf("선택해주세요.") }

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
            if (backPress == false) {
                backPress = true
                snackbarState.showSnackbar("뒤로가기를 한번 더 누르면 종료됩니다.")
                backPress = false
            } else {
                exitProgram()
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
                        is StudentMainComponent.StudentChild.StudentHomeChild -> ""
                        is StudentMainComponent.StudentChild.StudentRoadmapChild -> "로드맵"
                        is StudentMainComponent.StudentChild.StudentCertificationChild -> "인증"
                        is StudentMainComponent.StudentChild.StudentCommunityChild -> "게시판"
                        is StudentMainComponent.StudentChild.StudentFriendsChild -> "친구"
                        is StudentMainComponent.StudentChild.StudentMyPageChild -> "내정보"
                    },
                    leftIcon = {
                        when(activeComponent) {
                            is StudentMainComponent.StudentChild.StudentCommunityChild -> {
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
                StudentChildren(
                    modifier = Modifier
                        .weight(1f),
                    component = component
                )
                StudentBottomNav(
                    component = component,
                    activeComponent = activeComponent
                )
            }
            if (activeComponent is StudentMainComponent.StudentChild.StudentHomeChild && !noti) {
                /* TODO(추후 API에서 불러오도록 변경) */
                GPDropdownMenu(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 8.gdp),
                    options = listOf("Option 1기", "Option 2", "Option 3", "Option 4", "Option 5", "Option 6", "Option 7"),
                    selectedOption = testOptionItem,
                    onOptionSelected = {
                        testOptionItem = it
                    }
                )
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
fun StudentBottomNav(
    modifier: Modifier = Modifier,
    component: StudentMainComponent,
    activeComponent: StudentMainComponent.StudentChild
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
            onTop = activeComponent is StudentMainComponent.StudentChild.StudentHomeChild,
            onClick = {
                if (activeComponent !is StudentMainComponent.StudentChild.StudentHomeChild)
                    component.navigateToHome()
            },
        )
        NavItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            title = "로드맵",
            icon = painterResource(Res.drawable.icon_roadmap),
            onTop = activeComponent is StudentMainComponent.StudentChild.StudentRoadmapChild,
            onClick = {
                if (activeComponent !is StudentMainComponent.StudentChild.StudentRoadmapChild)
                    component.navigateToRoadmap()
            },
        )
        NavItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            title = "인증",
            icon = painterResource(Res.drawable.icon_certification),
            onTop = activeComponent is StudentMainComponent.StudentChild.StudentCertificationChild
                    || activeComponent is StudentMainComponent.StudentChild.StudentCommunityChild,
            onClick = {
                if (activeComponent !is StudentMainComponent.StudentChild.StudentCertificationChild)
                    component.navigateToCertification()
            },
        )
        NavItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            title = "친구",
            icon = painterResource(Res.drawable.icon_friends),
            onTop = activeComponent is StudentMainComponent.StudentChild.StudentFriendsChild,
            onClick = {
                if (activeComponent !is StudentMainComponent.StudentChild.StudentFriendsChild)
                    component.navigateToFriends()
            },
        )
        NavItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            title = "내정보",
            icon = painterResource(Res.drawable.icon_mypage),
            onTop = activeComponent is StudentMainComponent.StudentChild.StudentMyPageChild,
            onClick = {
                if (activeComponent !is StudentMainComponent.StudentChild.StudentMyPageChild)
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
private fun StudentChildren(component: StudentMainComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,
//        animation = stackAnimation(fade()),
        animation = tabAnimation()
    ) {
        when (val child = it.instance) {
            is StudentMainComponent.StudentChild.StudentHomeChild -> StudentHomeScreen(component = child.component)
            is StudentMainComponent.StudentChild.StudentRoadmapChild -> StudentRoadmapScreen(component = child.component)
            is StudentMainComponent.StudentChild.StudentCertificationChild -> StudentCertificationScreen(
                component = child.component,
                onCommunityButtonClicked = {
                    component.navigateToCommunity()
                }
            )
            is StudentMainComponent.StudentChild.StudentCommunityChild -> StudentCommunityScreen(component = child.component)
            is StudentMainComponent.StudentChild.StudentFriendsChild -> StudentFriendScreen(component = child.component)
            is StudentMainComponent.StudentChild.StudentMyPageChild -> StudentMyPageScreen(component = child.component)
        }
    }
}

@OptIn(FaultyDecomposeApi::class)
@Composable
private fun tabAnimation(): StackAnimation<Any, StudentMainComponent.StudentChild> =
    stackAnimation { child, otherChild, direction ->
        val index = child.instance.index
        val otherIndex = otherChild.instance.index
        val anim = slide()
        if ((index > otherIndex) == direction.isEnter) anim else anim.flipSide()
    }

private val StudentMainComponent.StudentChild.index: Int
    get() =
        when (this) {
            is StudentMainComponent.StudentChild.StudentHomeChild -> 0
            is StudentMainComponent.StudentChild.StudentRoadmapChild -> 1
            is StudentMainComponent.StudentChild.StudentCertificationChild -> 2
            is StudentMainComponent.StudentChild.StudentCommunityChild -> 3
            is StudentMainComponent.StudentChild.StudentFriendsChild -> 4
            is StudentMainComponent.StudentChild.StudentMyPageChild -> 5
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