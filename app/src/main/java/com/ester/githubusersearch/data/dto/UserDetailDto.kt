package com.ester.githubusersearch.data.dto

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_detail")
data class UserDetailDto(
    @PrimaryKey
    @field:Json(name = "login")
    val login: String,
    @field:Json(name = "avatar_url")
    val avatarUrl: String,
    @field:Json(name = "html_url")
    val htmlUrl: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "company")
    val company: String,
    @field:Json(name = "blog")
    val blog: String,
    @field:Json(name = "location")
    val location: String,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "bio")
    val bio: String,
    @field:Json(name = "twitter_username")
    val twitterUsername: String,
    @field:Json(name = "public_repos")
    val publicRepos: Int,
    @field:Json(name = "public_gists")
    val publicGists: Int,
    @field:Json(name = "followers")
    val followers: Int,
    @field:Json(name = "following")
    val following: Int
) : Parcelable, Comparable<UserDetailDto> {

    override fun compareTo(other: UserDetailDto): Int = compareBy(
        UserDetailDto::login,
        UserDetailDto::avatarUrl,
        UserDetailDto::htmlUrl,
        UserDetailDto::name,
        UserDetailDto::company,
        UserDetailDto::blog,
        UserDetailDto::location,
        UserDetailDto::email,
        UserDetailDto::bio,
        UserDetailDto::twitterUsername,
        UserDetailDto::publicRepos,
        UserDetailDto::publicGists,
        UserDetailDto::followers,
        UserDetailDto::following,
    ).compare(this, other)

    companion object : Parceler<UserDetailDto> {
        override fun UserDetailDto.write(parcel: Parcel, flags: Int) {
            parcel.writeString(login)
            parcel.writeString(avatarUrl)
            parcel.writeString(htmlUrl)
            parcel.writeString(name)
            parcel.writeString(company)
            parcel.writeString(blog)
            parcel.writeString(location)
            parcel.writeString(email)
            parcel.writeString(bio)
            parcel.writeString(twitterUsername)
            parcel.writeInt(publicRepos)
            parcel.writeInt(publicGists)
            parcel.writeInt(followers)
            parcel.writeInt(following)
        }

        override fun create(parcel: Parcel): UserDetailDto = UserDetailDto(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
        )
    }
}
