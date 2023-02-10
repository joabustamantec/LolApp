package com.example.lolapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lolapp.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    private lateinit var auth: FirebaseAuth;
    private val GOOGLE_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(700)
        setTheme(R.style.Theme_Lolapp)


        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle();
        bundle.putString("mensaje", "Analytics funcionando...")
        analytics.logEvent("MainActivity", bundle)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.btnCrear.setOnClickListener {
            startActivity(Intent(this, RegistrarActivity::class.java))
        }
        binding.btnConfiguracion.setOnClickListener {
            startActivity(Intent(this, ConfiguracionActivity::class.java))
        }

        binding.btnIniciar.setOnClickListener {
            if (binding.txtEmail.text.isNotEmpty() && binding.txtPassword.text.isNotEmpty()) {
                auth.signInWithEmailAndPassword(
                    binding.txtEmail.text.toString(),
                    binding.txtPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email?:"", ProviderType.BASIC)
                        Toast.makeText(this, "Usuario logeado correctamente", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, NavigationHome::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Usuario logeado incorrectamente", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }

        binding.btnGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient: GoogleSignInClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)

        }

    }

    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, NavigationHome::class.java).apply {
            putExtra("email",email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)

                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{
                        if ( it.isSuccessful){
                            showHome(account.email?: "",ProviderType.GOOGLE)
                            Toast.makeText(this, "Usuario logueado Correctamente", Toast.LENGTH_LONG).show()

                        } else {
                            Toast.makeText(this, "Error en las credenciales del usuario", Toast.LENGTH_LONG).show()
                        }
                    }
                }

            } catch (e:ApiException){
                Toast.makeText(this, "Error en las credenciales ", Toast.LENGTH_LONG).show()

            }

        }
    }

}



