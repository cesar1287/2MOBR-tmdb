package com.github.cesar1287.a2mobr_tmdb.utils

sealed class Command {
    class Loading(val value: Boolean): Command()
    class Error(val error: Int): Command()
}