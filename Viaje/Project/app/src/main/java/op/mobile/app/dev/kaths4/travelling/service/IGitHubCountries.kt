package op.mobile.app.dev.kaths4.travelling.service

import op.mobile.app.dev.kaths4.travelling.model.Countries
import op.mobile.app.dev.kaths4.travelling.model.Quiz
import retrofit2.http.GET

interface IGitHubCountries {
    @GET("Smit2911/2ef7ee898c618508fd54bea1515da815/raw")
    suspend fun getResponse(): List<Countries>

    @GET("Smit2911/53bca7a8c9133691ec71d0cb90c7a76d/raw")
    suspend fun getQuiz(): List<Quiz>
}


