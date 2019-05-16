package io.c.communicationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.c.communicationapp.Network.CoursesService
import io.c.communicationapp.model.Course
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var retrofit:Retrofit

    companion object{


        //url d'attaque de l'api .. attention à bien en supprimer la fin de la requête
        //car lorsque de l'utilisation de l'url, l'interface 'CoursesService' va ajouter
        //ce qu'il faut qui va bien, lors de l'appel de l'API
        const val url = "https://mobile-courses-server.herokuapp.com"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //

        createRetrofitInstance()

        sendHTTPSRequest()

    }


    //Signature de la Classe ...
    //Génération de la requêtte à envoyer ...
    //
    private fun sendHTTPSRequest() {
        val service = retrofit.create(CoursesService::class.java)
        val request = service.listCourses()


        //la fonction "enqueue().." envoyer de manière asyncronne
        //la fonction "().." envoyer de manière synchrone

        //Création d'une classe à la voilée...
        //courseRequest.enqueue(object : Callback<List<Course>> { ...

        //Callback<List<Course>> c'est le type d'objet que revoie la requette API ...

        //Obligation d'implémenter les fonctions
        //              onResponse
        //              onFailure


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //                  !!!!!!!!!!!!!!!!!!          TOUCHY      !!!!!!!!!!!!!!!!!!!!!!!!!!

        //          Classe Anonyme
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        request.enqueue(object : Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                val allCourse = response.body()
                if(allCourse != null)
                {
                    Log.i("onResponse","HERE is ALL COURSES FROM HEROKU SERVER:")
                    for (c in allCourse)
                        Log.i("onReponse_"," one course : ${c.title} : ${c.cover}")

                }
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                error("KO")
            }

        })




    }


    //Construction du Retrofit etc ...
    private fun createRetrofitInstance() {
        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .build()
    }
}
