package com.slakra.data.di

import org.koin.core.context.loadKoinModules

/**
 * Function to load data layer dependencies
 * @author sumitlakra
 * @date 06/04/2021
 */
fun loadDataModules() {
    loadKoinModules(
        listOf(
            retrofitModule,
            mapperModule,
            coroutineContextProviderModule,
            repositoryModule,
            dbModule
        )
    )
}