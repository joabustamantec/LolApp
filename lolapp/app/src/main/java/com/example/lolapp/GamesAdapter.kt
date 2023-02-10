package com.example.lolapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater


class GamesAdapter(private val lista:List<GameResponseItem>): RecyclerView.Adapter<GameViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):GameViewHolder{
        val layoutInflater=LayoutInflater.from(parent.context)
        return GameViewHolder(layoutInflater.inflate(R.layout.item_games,parent,false))
    }

    override fun onBindViewHolder(holder:GameViewHolder,position: Int){
        val item = lista[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = lista.size
}