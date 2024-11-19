package com.example.rickandmortyexam.data.data_classes

// used to check whether we´ve reached the end of the list
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class CharacterList(
    val info: Info,
    val results: List<ApiRMCharacter>
)
