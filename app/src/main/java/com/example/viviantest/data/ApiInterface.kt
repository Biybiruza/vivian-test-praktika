package com.example.viviantest.data

import com.example.viviantest.data.login.User
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    //SignIn
    @POST("api/employee/signIn")
    fun signIn(@Body postUser: PostUser): Observable<GenericResponse<User>>

    @POST("api/users")
    fun regisration(
        @Header("Authorization") token: String,
        @Body registrationClient: RegistrationClient
    ): Observable<GenericResponse<ResultRegistrationClient>>
}