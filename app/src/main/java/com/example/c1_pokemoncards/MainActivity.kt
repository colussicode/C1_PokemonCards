package com.example.c1_pokemoncards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.c1_pokemoncards.data.models.Pokemon
import com.example.c1_pokemoncards.data.models.PokemonResult
import com.example.c1_pokemoncards.data.remote.RetrofitService
import com.example.c1_pokemoncards.data.remote.service.PokemonService
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service: PokemonService = RetrofitService.getInstance().create(PokemonService::class.java)

        val data = service.getData()
        data.enqueue(object: retrofit2.Callback<PokemonResult> {
            override fun onResponse(call: Call<PokemonResult>, response: Response<PokemonResult>) {
                println(response.body())
            }

            override fun onFailure(call: Call<PokemonResult>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}