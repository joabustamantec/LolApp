package com.example.lolapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lolapp.databinding.FragmentNotificationsBinding
import com.google.firebase.firestore.FirebaseFirestore

class NotificationsFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
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
            db.collection("campeones").document(binding.txtNombreCampeon.text.toString()).set(
                hashMapOf(
                    "nombreCampeon" to binding.txtNombreCampeon.text.toString(),
                    "rolCampeon" to binding.txtRolCampeon.text.toString(),
                    "posicionCampeon" to binding.txtPosicionCampeon.text.toString(),
                )
            )
            Toast.makeText(context, "Campeon Guardado", Toast.LENGTH_SHORT).show()
        }
        binding.btnEliminar.setOnClickListener {
            db.collection("campeones").document(binding.txtNombreCampeon.text.toString()).delete()
            Toast.makeText(context, "Campeon Eliminado", Toast.LENGTH_SHORT).show()
        }
        binding.btnBuscar.setOnClickListener {
            db.collection("campeones").document(binding.txtNombreCampeon.text.toString()).get()
                .addOnCompleteListener {
                    binding.txtNombreCampeon.setText(it.result.get("nombreCampeon") as String?)
                    binding.txtRolCampeon.setText(it.result.get("rolCampeon") as String?)
                    binding.txtPosicionCampeon.setText(it.result.get("posicionCampeon") as String?)
                }
        }
    }

}