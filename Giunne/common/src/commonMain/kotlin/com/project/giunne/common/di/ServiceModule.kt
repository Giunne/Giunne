package com.project.giunne.common.di

import com.project.giunne.common.data.service.AuthService
import com.project.giunne.common.data.service.AvatarService
import com.project.giunne.common.data.service.CommonService
import com.project.giunne.common.data.service.MyPageService
import com.project.giunne.common.data.service.RecreationService
import com.project.giunne.common.data.service.RoadMapService
import com.project.giunne.common.data.service.ShopService
import com.project.giunne.common.data.service.createAuthService
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent

val serviceModule: Module = module {
    single {
        authKtorfit().create<ShopService>()
    }
    single {
        defaultKtorfit().create<AuthService>()
    }
    single {
        defaultKtorfit().create<CommonService>()
    }
    single {
        authKtorfit().create<RecreationService>()
    }
    single {
        authKtorfit().create<AvatarService>()
    }
    single {
        authKtorfit().create<RoadMapService>()
    }
    single {
        authKtorfit().create<MyPageService>()
    }
}

private fun defaultKtorfit(): Ktorfit = KoinJavaComponent.get(Ktorfit::class.java, named("Default"))
private fun authKtorfit(): Ktorfit = KoinJavaComponent.get(Ktorfit::class.java, named("Auth"))