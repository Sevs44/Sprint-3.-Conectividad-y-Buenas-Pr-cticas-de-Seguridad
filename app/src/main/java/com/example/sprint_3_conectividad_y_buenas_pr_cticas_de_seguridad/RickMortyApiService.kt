package com.example.sprint_3_conectividad_y_buenas_pr_cticas_de_seguridad

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RickMortyApiService {
    @GET
    suspend fun getCharacterByName(@Url url: String): Response<Results?>
}