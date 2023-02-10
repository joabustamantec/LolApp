package com.example.lolapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lolapp.databinding.ActivityRegistrarseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegistrarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarseBinding;
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        setup()

    }

    private fun setup() {

        binding.btnCrearCuenta.setOnClickListener() {
            if (binding.txtEmail.text.isNotEmpty() && binding.txtPassword.text.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(
                    binding.txtEmail.text.toString(),
                    binding.txtPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(this, "Error en la creacion", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }
}
