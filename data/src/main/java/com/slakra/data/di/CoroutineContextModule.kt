package com.slakra.data.di

import com.slakra.common.utils.CoroutineContextProvider
import org.koin.dsl.module

/**
 * Koin module to provide Coroutine context dependencies
 * @author sumitlakra
 * @date 06/04/2021
 */
internal val coroutineContextProviderModule = module {
    single { CoroutineContextProvider() }
}