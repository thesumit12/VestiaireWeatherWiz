package com.slakra.data.local

import androidx.room.*

/**
 * Base Room dao with common CRUD operations
 * @author sumitlakra
 * @date 06/04/2021
 */
interface RoomBaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertEntity(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntityWithReplaceStrategy(item: T): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(items: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllWithReplaceStrategy(items: List<T>)

    @Update
    fun updateEntity(item: T): Int

    @Delete
    fun deleteEntity(item: T)
}