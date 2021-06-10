package op.mobile.app.dev.kaths4.travelling.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Countries(val id: String,
                     val name: String,
                     val capital: String,
                     val flagImg: String) : Parcelable




