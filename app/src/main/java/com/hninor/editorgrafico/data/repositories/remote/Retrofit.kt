package com.hninor.editorgrafico.data.repositories.remote

import com.hninor.editorgrafico.data.entities.Figure
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FigureService {
    @GET("polygons")
    suspend fun getFigures(): List<Figure>
}


fun provideFigureService(): FigureService {

    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(logging)
    val retrofit = Retrofit.Builder()
        .baseUrl("https://gca.traces.com.co/pruebamovil/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    return retrofit.create(FigureService::class.java)
}