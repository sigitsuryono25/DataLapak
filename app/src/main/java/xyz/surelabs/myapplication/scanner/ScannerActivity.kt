package xyz.surelabs.myapplication.scanner

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.AutoFocusMode.SAFE
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode.SINGLE
import com.google.zxing.BarcodeFormat
import kotlinx.android.synthetic.main.activity_scanner.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.surelabs.myapplication.R
import xyz.surelabs.myapplication.network.NetworkModule
import xyz.surelabs.myapplication.network.datamodel.detailpelapak.ResponseDetailPelapak


class ScannerActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        )

        setContentView(R.layout.activity_scanner)

        codeScanner = CodeScanner(this, scanner_view)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = listOf(BarcodeFormat.QR_CODE) // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = SAFE // or CONTINUOUS
        codeScanner.scanMode = SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                //                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
                getDetailPelapak(it.text)
            }
        }
        codeScanner.errorCallback = ErrorCallback {
            // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(
                    this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        scanner_view.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


    private fun getDetailPelapak(idPelapak: String) {
        NetworkModule.getService().getDetailLapak(idPelapak)
            .enqueue(object : Callback<ResponseDetailPelapak> {
                override fun onFailure(call: Call<ResponseDetailPelapak>, t: Throwable) {
                    Toast.makeText(this@ScannerActivity, t.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<ResponseDetailPelapak>,
                    response: Response<ResponseDetailPelapak>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.error == 200) {
                            DetailFragment.newInstance(response.body()?.data)
                                .show(supportFragmentManager, "Detail Lapak")
                        }
                    }
                }

            })
    }

}
