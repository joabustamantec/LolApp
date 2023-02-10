package com.example.lolapp.ui.lista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolapp.databinding.FragmentListaBinding
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class ListaViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView)
class ListaFragment: Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, SavedInstance: Bundle?) {
        val query = db.collection("items")
        val options = FirestoreRecyclerOptions.Builder<ListaViewModel>()
            .setQuery(query, ListaViewModel::class.java).setLifecycleOwner(this).build()

        val adapter = object : FirestoreRecyclerAdapter<ListaViewModel, ListaViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaViewHolder {
                val view = LayoutInflater.from(context).inflate(com.example.lolapp.R.layout.listaitems,parent,false)
                return ListaViewHolder(view)
            }

            override fun onBindViewHolder(
                holder: ListaViewHolder,
                position: Int,
                model: ListaViewModel
            ) {
                val nombreItem: TextView = holder.itemView.findViewById(com.example.lolapp.R.id.TextViewNombreItem)
                val rolItem: TextView = holder.itemView.findViewById(com.example.lolapp.R.id.TextViewRolItem)
                val nivelItem: TextView = holder.itemView.findViewById(com.example.lolapp.R.id.TextViewNivelItem)
                nombreItem.text=model.NombreItem
                rolItem.text=model.RolItem
                nivelItem.text=model.NivelItem

            }

        }
        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = LinearLayoutManager(context)


    }

}