package com.slakra.domain.arch

import com.slakra.common.ResultState


/**
 * Base useCase interface to be implemented by all
 * app use cases for network operations
 * @author sumitlakra
 * @date 06/04/2021
 */
interface BaseNetworkWithParamUseCase<P,T> {

    suspend fun execute(param: P): ResultState<T>
}