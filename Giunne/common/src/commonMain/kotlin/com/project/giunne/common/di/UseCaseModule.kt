package com.project.giunne.common.di

import com.project.giunne.common.domain.usecase.shop.GetGachaMapUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule: Module = module {
    single { GetGachaMapUseCase(get())}
}