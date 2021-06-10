package op.mobile.app.dev.kaths4.travelling.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quiz(
    val id: Int,
    val name: String,
    val quizid: Int,
    val question1 : Question1,
    val question2 : Question2,
    val question3 : Question3,
    val question4 : Question4,
    val question5 : Question5,
    val question6 : Question6
) : Parcelable
