package com.calvinnor.data.util

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    fun insert(obj: T)

    @Insert
    fun insert(obj: List<T>)

    @Update
    fun update(obj: T)

    @Update
    fun update(obj: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(obj: List<T>)

    @Delete
    fun delete(obj: T)

    @Delete
    fun delete(obj: List<T>)

}
