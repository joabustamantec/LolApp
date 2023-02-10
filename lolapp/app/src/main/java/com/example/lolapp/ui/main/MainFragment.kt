package com.example.lolapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lolapp.APIService
import com.example.lolapp.GameResponseItem
import com.example.lolapp.GamesAdapter
import com.example.lolapp.databinding.FragmentMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {

    private lateinit var adapter:GamesAdapter
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val lista = mutableListOf<GameResponseItem>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainVewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initRecycle()
        buscarGames()

    }

    private fun initRecycle() {
        adapter = GamesAdapter(lista)
        binding.rvJuegos.layoutManager = LinearLayoutManager(context)
        binding.rvJuegos.adapter = adapter
    }

    private fun getRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun buscarGames(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getGames("games")
            val juegos = call.body()!!
            activity?.runOnUiThread(){
                if (call.isSuccessful){
                    //mostramos en el rv(recyclerview)
                    lista.clear()
                    for(aux in juegos){
                        lista.add(aux)
                        adapter.notifyDataSetChanged()
                    }

                }else{
                    //mostramos el error
                }
            }

        }
    }

}

/*binding.btnCerrarSesion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }*/