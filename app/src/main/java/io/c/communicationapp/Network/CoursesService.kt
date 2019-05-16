package io.c.communicationapp.Network

import io.c.communicationapp.model.Course
import retrofit2.http.GET
import retrofit2.Call

interface CoursesService {


    @GET("/courses")
    fun listCourses(): Call<List<Course>>


}