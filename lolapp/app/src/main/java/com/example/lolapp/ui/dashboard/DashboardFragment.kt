package com.example.lolapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lolapp.databinding.FragmentDashboardBinding
import com.google.firebase.firestore.FirebaseFirestore

class DashboardFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val itemViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setup() {
        binding.btnGuardar.setOnClickListener {
            db.collection("items").document(binding.txtNombreItem.text.toString()).set(
                hashMapOf(
                    "nombreItem" to binding.txtNombreItem.text.toString(),
                    "rolItem" to binding.txtRolItem.text.toString(),
                    "nivelItem" to binding.txtNivelItem.text.toString(),
                )
            )
            Toast.makeText(context, "Item Guardado", Toast.LENGTH_SHORT).show()
        }
        binding.btnEliminar.setOnClickListener {
            db.collection("items").document(binding.txtNombreItem.text.toString()).delete()
            Toast.makeText(context, "Item Eliminado", Toast.LENGTH_SHORT).show()
        }
        binding.btnBuscar.setOnClickListener {
            db.collection("items").document(binding.txtNombreItem.text.toString()).get()
                .addOnCompleteListener {
                    binding.txtNombreItem.setText(it.result.get("nombreItem") as String?)
                    binding.txtRolItem.setText(it.result.get("rolItem") as String?)
                    binding.txtNivelItem.setText(it.result.get("nivelItem") as String?)
                }
        }
    }
}