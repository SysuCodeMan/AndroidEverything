package com.example.chrischan.imagelooker.bean

import android.net.Uri

/**
 * @author by Lucifer on 2019-06-25.
 */
data class MusicItem(
        val mediaId: String,
        val title: String,
        val subtitle: String,
        val albumArtUri: Uri
)