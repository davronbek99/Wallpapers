package com.example.a4kfullwalpapers.ui.home.filter

import java.util.*

class FilterList {
    val names: MutableList<String> = LinkedList()
    val filters: MutableList<FilterType> = LinkedList()
    val images: MutableList<Int> = LinkedList()

    fun addFilter(name: String, filter: FilterType, image: Int) {
        names.add(name)
        filters.add(filter)
        images.add(image)
    }
}