package com.project.giunne.common.di

import com.project.giunne.common.domain.usecase.shop.GetCategoryItemListUseCase
import com.project.giunne.common.domain.usecase.shop.GetCategoryMapUseCase
import com.project.giunne.common.domain.usecase.shop.GetGachaTypeUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule: Module = module {
    single { GetCategoryMapUseCase(get()) }
    single { GetCategoryItemListUseCase(get()) }
    single { GetGachaTypeUseCase(get()) }
}