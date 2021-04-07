package com.example.giphyapp.dataModel

data class GifModel(
    val data: List<Data>
)
data class Data(
    val url: String,
    val title: String,
    val images: Images
)
data class Images(
    val original: Original
)
data class Original(
    val url: String
)

