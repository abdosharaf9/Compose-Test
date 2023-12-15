package com.abdosharaf.composetest

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}