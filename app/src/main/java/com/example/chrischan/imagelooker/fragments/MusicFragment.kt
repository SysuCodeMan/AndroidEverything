package com.example.chrischan.imagelooker.fragments


import android.content.ComponentName
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.media.MediaBrowserCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chrischan.imagelooker.R
import com.example.chrischan.imagelooker.bean.MusicItem
import com.example.chrischan.imagelooker.pages.media.MediaService
import com.example.chrischan.imagelooker.pages.media.MusicListAdapter

/**
 * @author by Lucifer on 2019/6/5.
 */
class MusicFragment : Fragment() {
    private lateinit var musicBrowser: MediaBrowserCompat
    private lateinit var musicList: RecyclerView
    private lateinit var musicAdapter: MusicListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.music_fragment_layout, container, false)
        musicList = view.findViewById(R.id.rv_music_list)
        val musicLayoutManager = LinearLayoutManager(context)
        val musicData = ArrayList<MusicItem>()
        musicData.add(MusicItem(
                "wake_up_01",
                "Intro - The Way Of Waking Up (feat. Alan Watts)",
                "The Kyoto Connection",
                Uri.parse("https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/art.jpg")
                ))
        musicAdapter = MusicListAdapter(musicData) {

        }
        musicList.apply {
            layoutManager = musicLayoutManager
            adapter = musicAdapter
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val subscriptionCallback = object : MediaBrowserCompat.SubscriptionCallback() {
            override fun onChildrenLoaded(parentId: String, children: MutableList<MediaBrowserCompat.MediaItem>) {
                val musicList:List<MusicItem> = children.map {
                    MusicItem(
                            it.mediaId!!,
                            it.description.title.toString(),
                            it.description.subtitle.toString(),
                            it.description.iconUri!!
                    )
                }
                musicAdapter.updateData(musicList)
            }
        }
        musicBrowser = MediaBrowserCompat(context, ComponentName(context, MediaService::class.java), object: MediaBrowserCompat.ConnectionCallback() {
            override fun onConnected() {
                if (musicBrowser.isConnected) {
                    val mediaId = musicBrowser.root
                    musicBrowser.unsubscribe(mediaId)
                    musicBrowser.subscribe(mediaId, subscriptionCallback)
                }
            }
        }, null)
    }

    override fun onStart() {
        super.onStart()
        musicBrowser.connect()
    }

    override fun onStop() {
        super.onStop()
        musicBrowser.disconnect()
    }
}