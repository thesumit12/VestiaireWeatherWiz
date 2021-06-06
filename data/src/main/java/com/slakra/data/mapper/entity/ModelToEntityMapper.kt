package com.slakra.data.mapper.entity

interface ModelToEntityMapper<M,E> {

    fun map(model: M): E
}