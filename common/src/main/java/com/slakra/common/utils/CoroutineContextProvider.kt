package com.slakra.common.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Class to provide Coroutine Context
 * @author sumitlakra
 * @date 06/04/2021
 */
open class CoroutineContextProvider {
    open val Main: CoroutineContext by lazy { Dispatchers.Main }
    open val IO: CoroutineContext by lazy { Dispatchers.IO }
    open val DEFAULT: CoroutineContext by lazy { Dispatchers.Default }
}