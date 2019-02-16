package com.calvinnor.core.utils

import androidx.recyclerview.widget.DiffUtil

abstract class DiffUtilCallback<T>(
    private val oldList: List<T>,
    private val newList: List<T>

) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    fun getResult(detectMoves: Boolean = false) = DiffUtil.calculateDiff(this, detectMoves)

}
