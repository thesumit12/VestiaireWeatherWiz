package com.slakra.data.mapper.remote

/**
 * Base mapper interface to map data
 * @author sumitlakra
 * @date 06/04/2021
 */
interface RemoteToModelMapper<R,M> {

    fun map(remote: R): M
}