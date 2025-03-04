package com.project.giunne.common.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.project.giunne.common.data.repository.ShopRepositoryImpl
import com.project.giunne.common.data.repository.AuthRepositoryImpl
import com.project.giunne.common.data.repository.CommonRepositoryImpl
import com.project.giunne.common.data.repository.RecreationRepositoryImpl
import com.project.giunne.common.data.repository.AvatarRepositoryImpl
import com.project.giunne.common.data.repository.MyPageRepositoryImpl
import com.project.giunne.common.domain.repository.AuthRepository
import com.project.giunne.common.domain.repository.AvatarRepository
import com.project.giunne.common.domain.repository.CommonRepository
import com.project.giunne.common.domain.repository.MyPageRepository
import com.project.giunne.common.domain.repository.RecreationRepository
import com.project.giunne.common.domain.repository.ShopRepository
import org.koin.dsl.bind

val repositoryModule: Module = module {
    singleOf(::ShopRepositoryImpl) bind ShopRepository::class
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    singleOf(::CommonRepositoryImpl) bind CommonRepository::class
    singleOf(::RecreationRepositoryImpl) bind RecreationRepository::class
    singleOf(::AvatarRepositoryImpl) bind AvatarRepository::class
    singleOf(::MyPageRepositoryImpl) bind MyPageRepository::class
}