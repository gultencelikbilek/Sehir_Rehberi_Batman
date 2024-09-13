package com.yaman.belediyehizmet.feature.news


data class NewsItem(
    val title: String = "",
    val description: String = "",
    val pageDetailUrl: String = "",
    val imageUrl: String = "",
    val date: String =""
){
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            title,
            description
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}


