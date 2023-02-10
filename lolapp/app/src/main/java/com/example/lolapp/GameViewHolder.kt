package com.example.lolapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lolapp.databinding.ItemGamesBinding
import com.squareup.picasso.Picasso

class GameViewHolder(view: View):RecyclerView.ViewHolder(view){

    private val binding = ItemGamesBinding.bind(view)


    //val auxImagen = view.findViewById<ImageView>(R.id.txtImagen)
    val auxNombreJuego = view.findViewById<TextView>(R.id.txtNombreJuego)
    val auxGenero = view.findViewById<TextView>(R.id.txtGenero)
    val auxPlataforma = view.findViewById<TextView>(R.id.txtPlataforma)
    val auxFecha = view.findViewById<TextView>(R.id.txtFecha)

    fun render(gamesResponseItemModel: GameResponseItem){
        auxNombreJuego.text = gamesResponseItemModel.title
        auxGenero.text = gamesResponseItemModel.genre
        auxPlataforma.text = gamesResponseItemModel.platform
        auxFecha.text = gamesResponseItemModel.release_date
        Picasso.get().load(gamesResponseItemModel.thumbnail).into(binding.txtImagen)
    }
}