package com.example.sprint_3_conectividad_y_buenas_pr_cticas_de_seguridad

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("result")
    val characters: List<Character>
)

data class Character(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val subspecies: String?,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("location")
    val location: Location,
    @SerializedName("image")
    val image: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val dateCreated: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        TODO("origin"),
        TODO("location"),
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(status)
        parcel.writeString(species)
        parcel.writeString(subspecies)
        parcel.writeString(gender)
        parcel.writeString(image)
        parcel.writeStringList(episode)
        parcel.writeString(url)
        parcel.writeString(dateCreated)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}

data class Origin(
    @SerializedName("name")
    val place: String,
    @SerializedName("url")
    val url: String
)

data class Location(
    @SerializedName("name")
    val place: String,
    @SerializedName("url")
    val url: String
)