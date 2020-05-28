package pe.speira.antropometria.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AntropometriaEntity(
    @ColumnInfo(name = "p_tricipital") var pTricipital: Double,
    @ColumnInfo(name = "p_bicipital") var pBicipital: Double,
    @ColumnInfo(name = "p_subescapular") var pSubescapular: Double,
    @ColumnInfo(name = "p_suprailiaco") var pSuprailiaco: Double
): Parcelable