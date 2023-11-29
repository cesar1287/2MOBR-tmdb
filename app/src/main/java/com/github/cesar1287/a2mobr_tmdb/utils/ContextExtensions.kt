package com.github.cesar1287.a2mobr_tmdb.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.github.cesar1287.a2mobr_tmdb.R
import com.github.cesar1287.a2mobr_tmdb.model.Movie

fun Context.shareMovie(movie: Movie) {
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.putExtra(
        Intent.EXTRA_TEXT,
        this.getString(
            R.string.share_movie_message,
            movie.title,
            movie.overview
        )
    )
    intent.type = "text/plain"
    this.startActivity(
        Intent.createChooser(
            intent,
            this.getString(R.string.share_using)
        )
    )
}

fun Activity.showNearbyTheaters() {
    // Create a Uri from an intent string. Use the result to create an Intent.
    val gmmIntentUri = Uri.parse("geo:0,0?q=cinema")

    // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    // Make the Intent explicit by setting the Google Maps package
    mapIntent.setPackage("com.google.android.apps.maps")

    mapIntent.resolveActivity(packageManager)?.let {
        // Attempt to start an activity that can handle the Intent
        startActivity(mapIntent)
    }
}