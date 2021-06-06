package com.slakra.di

import com.slakra.data.di.loadDataModules
import org.koin.core.context.loadKoinModules

/**
 * Function to load domain layer dependencies
 * @author sumitlakra
 * @date 06/04/2021
 */
fun initKoin() {
    loadKoinModules(listOf(useCaseModule))
}

/**
 * Function to load data layer dependencies
 * @author sumitlakra
 * @date 06/04/2021
 */
fun loadDataModules() {
    loadDataModules()
}