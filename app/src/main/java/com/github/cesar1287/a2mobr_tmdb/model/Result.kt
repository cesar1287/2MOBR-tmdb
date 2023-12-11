package com.github.cesar1287.a2mobr_tmdb.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class Movie(
    @PrimaryKey
    val id: Int = 0,
    val adult: Boolean = false,
    @ColumnInfo("backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String = "",
    @ColumnInfo("original_language")
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @ColumnInfo("original_title")
    @SerializedName("original_title")
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @ColumnInfo("poster_path")
    @SerializedName("poster_path")
    var posterPath: String = "",
    @ColumnInfo("release_date")
    @SerializedName("release_date")
    var releaseDate: String = "",
    val title: String = "",
): Parcelable {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}