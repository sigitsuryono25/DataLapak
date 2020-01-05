package xyz.surelabs.myapplication.network.datamodel.detailpelapak

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class Data(

    @field:SerializedName("id_pasar")
    val idPasar: String? = null,

    @field:SerializedName("id_pelapak")
    val idPelapak: String? = null,

    @field:SerializedName("foto")
    val foto: String? = null,

    @field:SerializedName("nama_pasar")
    val namaPasar: String? = null,

    @field:SerializedName("lokasi_pasar")
    val lokasiPasar: String? = null,

    @field:SerializedName("telepon")
    val telepon: String? = null,

    @field:SerializedName("penaggung_jawab")
    val penaggungJawab: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null,

    @field:SerializedName("qr_files")
    val qrFiles: String? = null
) : Serializable