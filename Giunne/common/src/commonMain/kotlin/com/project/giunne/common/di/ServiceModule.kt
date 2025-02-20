package com.project.giunne.common.di

import com.project.giunne.common.data.service.ShopService
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent

val serviceModule: Module = module {
    single {
        defaultKtorfit().create<ShopService>()
    }
}

private fun defaultKtorfit(): Ktorfit = KoinJavaComponent.get(Ktorfit::class.java, named("Default"))
private fun authKtorfit(): Ktorfit = KoinJavaComponent.get(Ktorfit::class.java, named("Auth"))