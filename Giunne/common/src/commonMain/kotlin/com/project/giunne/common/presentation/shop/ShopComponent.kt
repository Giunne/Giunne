package com.project.giunne.common.presentation.shop

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent

class ShopComponent(
    componentContext: ComponentContext,
    onBackButtonClick: () -> Unit
): KoinComponent, ComponentContext by componentContext {

}