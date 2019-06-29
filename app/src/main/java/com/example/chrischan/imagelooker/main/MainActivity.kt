package com.example.chrischan.imagelooker.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.chrischan.imagelooker.R
import com.example.chrischan.imagelooker.bean.MenuDO

/**
 * @author by Lucifer on 2019/1/19.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var menuRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        val menus = ArrayList<MenuDO>().apply {
            add(MenuDO("音乐播放器", "bruce://media"))
            add(MenuDO("动画", "bruce://media"))
            add(MenuDO("NDK", "bruce://ndk"))
        }
        val menuManager = LinearLayoutManager(this)
        val menuAdapter = MainMenuAdapter(menus)
        menuRecyclerView = findViewById<RecyclerView>(R.id.rv_menu).apply {
            layoutManager = menuManager
            adapter = menuAdapter
        }
    }
}
