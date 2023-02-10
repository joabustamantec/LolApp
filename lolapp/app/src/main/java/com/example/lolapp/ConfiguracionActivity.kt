package com.example.lolapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lolapp.databinding.ActivityConfiguracionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ConfiguracionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfiguracionBinding;
    private lateinit var auth: FirebaseAuth;
    val CHANNELID = "CANAL1"
    val notificationID = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfiguracionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

    }

    /*private fun crarCanalNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES .0 ){
            val nombreTitulo = "Titulo Notificacion"
            val descripcion = "mensaje de la notificacion"
            val importancia = NotificationManager.IMPORTANCE_DEFAULT
            val canal = NotificationChannel(CHANNELID, nombreTitulo, importancia).apply {
                descripcion = descripcion
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(canal)

        }

        private fun enviarNotificacion() {

        }
    }*/
}