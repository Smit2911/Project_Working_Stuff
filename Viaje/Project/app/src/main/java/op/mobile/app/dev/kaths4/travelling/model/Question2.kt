package op.mobile.app.dev.kaths4.travelling.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question2(
    val image : String,
    val question : String,
    val option : Option,
    val answer : String
): Parcelable
