package com.example.lolapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lolapp.databinding.FragmentHomeBinding
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class NotificationsViewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView)
class HomeFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, SavedInstance: Bundle?) {
        val query = db.collection("campeones")
        val options = FirestoreRecyclerOptions.Builder<HomeViewModel>()
            .setQuery(query, HomeViewModel::class.java).setLifecycleOwner(this).build()

        val adapter = object : FirestoreRecyclerAdapter<HomeViewModel,NotificationsViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):NotificationsViewHolder{
                val view = LayoutInflater.from(context).inflate(com.example.lolapp.R.layout.listacampeones,parent,false)
                return NotificationsViewHolder(view)
            }

            override fun onBindViewHolder(
                holder: NotificationsViewHolder,
                position: Int,
                model: HomeViewModel
            ) {
                val nombreCampeon: TextView = holder.itemView.findViewById(com.example.lolapp.R.id.TextViewNombreCampeon)
                val rolCampeon: TextView = holder.itemView.findViewById(com.example.lolapp.R.id.TextViewRolCampeon)
                val posicionCampeon: TextView = holder.itemView.findViewById(com.example.lolapp.R.id.TextViewPosicionCampeon)
                nombreCampeon.text=model.NombreCampeon
                rolCampeon.text=model.RolCampeon
                posicionCampeon.text=model.PosicionCampeon

            }

        }
        binding.rvCampeones.adapter = adapter
        binding.rvCampeones.layoutManager = LinearLayoutManager(context)


    }

}