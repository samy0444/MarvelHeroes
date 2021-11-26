package com.oyelabs.marvelapp.models

import com.oyelabs.marvelapp.models.characters.Item


data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)