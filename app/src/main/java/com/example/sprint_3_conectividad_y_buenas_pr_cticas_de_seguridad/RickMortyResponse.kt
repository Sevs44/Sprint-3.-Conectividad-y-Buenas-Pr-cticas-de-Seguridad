package com.example.sprint_3_conectividad_y_buenas_pr_cticas_de_seguridad

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("results")
    val characters: List<Character>
)

data class Character(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("type")
    val subspecies: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("origin")
    val origin: Origin?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("episode")
    val episode: List<String>?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("created")
    val dateCreated: String?
)

data class Origin(
    @SerializedName("name")
    val place: String?,
    @SerializedName("url")
    val url: String?
)

data class Location(
    @SerializedName("name")
    val place: String?,
    @SerializedName("url")
    val url: String?
)