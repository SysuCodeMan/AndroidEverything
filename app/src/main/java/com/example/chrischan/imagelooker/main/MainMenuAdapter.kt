package com.example.chrischan.imagelooker.main

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.chrischan.imagelooker.R
import com.example.chrischan.imagelooker.bean.MenuDO

/**
 * @author by Lucifer on 2019/5/9.
 */
class MainMenuAdapter(private val menus:ArrayList<MenuDO>): RecyclerView.Adapter<MainMenuAdapter.MenuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false) as TextView
        return MenuViewHolder((textView))
    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.textView.apply {
            text = menus[position].text
            setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(menus[position].scheme))
                context.startActivity(intent)
            }
        }
    }

    class MenuViewHolder(val textView: TextView):RecyclerView.ViewHolder(textView)
}