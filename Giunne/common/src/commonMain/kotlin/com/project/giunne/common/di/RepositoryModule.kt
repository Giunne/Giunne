package com.project.giunne.common.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.project.giunne.common.data.repository.ShopRepositoryImpl
import com.project.giunne.common.domain.repository.ShopRepository
import org.koin.dsl.bind

val repositoryModule: Module = module {
    singleOf(::ShopRepositoryImpl) bind ShopRepository::class
}