package com.slakra.domain.arch

import kotlinx.coroutines.flow.Flow

/**
 * Base param useCase interface to be implemented by all
 * app use cases for local operations
 * @author sumitlakra
 * @date 06/05/2021
 */
interface BaseLocalUseCaseWithParam<P,T> {

    fun execute(param: P): Flow<T>
}