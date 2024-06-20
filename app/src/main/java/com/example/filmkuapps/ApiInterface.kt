package com.example.filmkuapps


import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZmQ5Y2NmMTFjYmMwNTc4ZDY2YzM4MjI4OGJkN2Q2OCIsInN1YiI6IjYyYmE1MjA5YTZkZGNiMDA1MDIwZWYwYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.spdJdN16Pt4LMm1KtGuyOvpMzabmXE9HMROwbGDkjZA")
    @GET("movie/popular")
    fun getMoviesPopular(): retrofit2.Call<MovieListResponse>

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZmQ5Y2NmMTFjYmMwNTc4ZDY2YzM4MjI4OGJkN2Q2OCIsInN1YiI6IjYyYmE1MjA5YTZkZGNiMDA1MDIwZWYwYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.spdJdN16Pt4LMm1KtGuyOvpMzabmXE9HMROwbGDkjZA")
    @GET("movie/top_rated")
    fun getMoviesTopRated(): retrofit2.Call<MovieListResponse>

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZmQ5Y2NmMTFjYmMwNTc4ZDY2YzM4MjI4OGJkN2Q2OCIsInN1YiI6IjYyYmE1MjA5YTZkZGNiMDA1MDIwZWYwYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.spdJdN16Pt4LMm1KtGuyOvpMzabmXE9HMROwbGDkjZA")
    @GET("movie/{movieId}") // {} artinya variabel dinamis, contoh /movie/999
    fun getMovieDetails(@Path("movieId") movieId: Int): retrofit2.Call<DetailsMovie>

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZmQ5Y2NmMTFjYmMwNTc4ZDY2YzM4MjI4OGJkN2Q2OCIsInN1YiI6IjYyYmE1MjA5YTZkZGNiMDA1MDIwZWYwYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.spdJdN16Pt4LMm1KtGuyOvpMzabmXE9HMROwbGDkjZA")
    @GET("search/movie")
    fun getSearch(@Query("query") query: String): retrofit2.Call<MovieListResponse>



}