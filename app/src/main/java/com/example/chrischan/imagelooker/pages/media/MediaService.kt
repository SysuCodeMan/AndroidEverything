package com.example.chrischan.imagelooker.pages.media

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaBrowserServiceCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat

/**
 * @author by Lucifer on 2019/6/12.
 */

class MediaService : MediaBrowserServiceCompat() {

    private var mediaSession: MediaSessionCompat? = null
    private lateinit var state: PlaybackStateCompat
    private lateinit var mediaPlayer: MediaPlayer
    private val mediaItems = ArrayList<MediaBrowserCompat.MediaItem>()

    private val controlCallback = object : MediaSessionCompat.Callback(){
        override fun onPause() {
            if (state.state == PlaybackStateCompat.STATE_PLAYING) {
                mediaPlayer.pause()
                state = PlaybackStateCompat.Builder()
                        .setState(PlaybackStateCompat.STATE_PAUSED, 0, 1.0f)
                        .build()
                mediaSession!!.setPlaybackState(state)
            }
        }

        override fun onPlay() {
            if (state.state == PlaybackStateCompat.STATE_PAUSED) {
                mediaPlayer.start()
                state = PlaybackStateCompat.Builder()
                        .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1.0f)
                        .build()
                mediaSession!!.setPlaybackState(state)
            }
        }

        override fun onPlayFromMediaId(mediaId: String?, extras: Bundle?) {
            val mediaItem = mediaItems.filter {
                it.mediaId == mediaId
            }[0]
            try {
                when (state.state) {
                    PlaybackStateCompat.STATE_NONE -> {
                        mediaPlayer.apply {
                            reset()
                            setDataSource(this@MediaService, mediaItem.description.mediaUri)
                            prepare()
                        }
                        state = PlaybackStateCompat.Builder().setState(PlaybackStateCompat.STATE_CONNECTING, 0, 1.0f)
                                .build()
                        mediaSession!!.setPlaybackState(state)
                    }
                    else -> {
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnPreparedListener {
            it.start()
            state = PlaybackStateCompat.Builder()
                    .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1.0f)
                    .build()
            mediaSession!!.setPlaybackState(state)
        }

        mediaPlayer.setOnCompletionListener {
            state = PlaybackStateCompat.Builder()
                    .setState(PlaybackStateCompat.STATE_NONE, 0, 1.0f)
                    .build()
            mediaSession!!.setPlaybackState(state)
            it.reset()
        }

        mediaSession = MediaSessionCompat(baseContext, "MediaService").apply {
//            // Enable callbacks from MediaButtons and TransportControls
            setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)
//
//            // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player
            state = PlaybackStateCompat.Builder()
                    .setActions(PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PAUSE)
                    .build()
//
            setPlaybackState(state)
//
            setCallback(controlCallback)
//          需要设置token之后才会触发MediaBrowserCompat.ConnectionCallback的回调，来表示MediaBrowser与
            setSessionToken(sessionToken)
        }
    }

    override fun onLoadChildren(parentId: String, result: Result<MutableList<MediaBrowserCompat.MediaItem>>) {
        result.detach()

        val firstItem = MediaMetadataCompat.Builder().apply {
            putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, "wake_up_02")
            putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, "The Kyoto Connection")
            putString(MediaMetadataCompat.METADATA_KEY_TITLE,"Geisha")
            putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/02_-_Geisha.mp3")
            putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/art.jpg")
        }.build()

        val secondItem = MediaMetadataCompat.Builder().apply {
            putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, "wake_up_03")
            putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, "The Kyoto Connection")
            putString(MediaMetadataCompat.METADATA_KEY_TITLE,"Voyage I - Waterfall")
            putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/03_-_Voyage_I_-_Waterfall.mp3")
            putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/art.jpg")
        }.build()

        mediaItems.add(MediaBrowserCompat.MediaItem(firstItem.description, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE))
        mediaItems.add(MediaBrowserCompat.MediaItem(secondItem.description, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE))
        result.sendResult(mediaItems)
    }

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot? {
        return BrowserRoot("/", rootHints)
    }
}