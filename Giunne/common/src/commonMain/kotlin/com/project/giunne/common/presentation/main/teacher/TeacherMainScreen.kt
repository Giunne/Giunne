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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.project.giunne.common.presentation.certification.student.StudentCertificationScreen
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.community.student.StudentCommunityDetailScreen
import com.project.giunne.common.presentation.community.student.StudentCommunityScreen
import com.project.giunne.common.presentation.friend.student.StudentFriendScreen
import com.project.giunne.common.presentation.home.student.StudentHomeScreen
import com.project.giunne.common.presentation.home.teacher.TeacherEmptyHomeScreen
import com.project.giunne.common.presentation.main.student.StudentMainComponent
import com.project.giunne.common.presentation.mypage.student.StudentMyPageScreen
import com.project.giunne.common.presentation.roadmap.student.StudentRoadmapScreen
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
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun TeacherMainScreen(
    modifier: Modifier = Modifier,
    component: TeacherMainComponent
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
        /* TODO("Default 화면 나중에 API 통신 후 구현") */
        TeacherEmptyHomeScreen(
            modifier = Modifier.fillMaxSize(),
            roadMapTitle = "",
            onValueChange = {},
            onCreateRoadMapClick = {}
        )
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
            title = "친구",
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

//@Composable
//private fun TeacherChildren(
//    component: TeacherMainComponent,
//    modifier: Modifier = Modifier,
//    activeComponent: TeacherMainComponent.TeacherChild
//) {
//    Children(
//        stack = component.childStack,
//        modifier = modifier,
////        animation = stackAnimation(fade()),
//        animation = tabAnimation()
//    ) {
//        when (val child = it.instance) {
//            is TeacherMainComponent.TeacherChild.TeacherHomeChild -> TeacherHomeScreen(component = child.component)
//            is TeacherMainComponent.TeacherChild.TeacherRoadmapChild -> TeacherRoadmapScreen(component = child.component)
//            is TeacherMainComponent.TeacherChild.TeacherCertificationChild -> TeacherCertificationScreen(
//                component = child.component,
//                onCommunityButtonClicked = {
//                    component.navigateToCommunity()
//                }
//            )
//            is TeacherMainComponent.TeacherChild.TeacherCommunityChild -> TeacherCommunityScreen(
//                component = child.component,
//                navigateToDetail = { communityDto ->
//                    component.navigateToCommunityDetail(communityDto)
//                }
//            )
//            is TeacherMainComponent.TeacherChild.TeacherCommunityDetailChild -> TeacherCommunityDetailScreen(
////                communityDto = (activeComponent as TeacherMainComponent.TeacherChild.TeacherCommunityDetailChild).communityDto
//                communityDto = if (activeComponent is TeacherMainComponent.TeacherChild.TeacherCommunityDetailChild)
//                    activeComponent.communityDto else null
//            )
//            is TeacherMainComponent.TeacherChild.TeacherFriendsChild -> TeacherFriendScreen(component = child.component)
//            is TeacherMainComponent.TeacherChild.TeacherMyPageChild -> TeacherMyPageScreen(
//                component = child.component,
//                navigateToShop = { component.navigateToShop() },
//                navigateToGacha = { component.navigateToGacha() }
//            )
//            is TeacherMainComponent.TeacherChild.TeacherShopChild -> ShopScreen()
//            is TeacherMainComponent.TeacherChild.TeacherGachaChild -> GachaScreen()
//        }
//    }
//}

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