package xyz.surelabs.myapplication.scanner


import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_scanner.*
import xyz.surelabs.myapplication.R
import xyz.surelabs.myapplication.network.datamodel.detailpelapak.Data


private const val DETAIL = "detail"

class DetailFragment : BottomSheetDialogFragment() {
    private var detailLapak: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            detailLapak = it.getSerializable(DETAIL) as Data
        }
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView =
            View.inflate(context, R.layout.fragment_detail, null)
        dialog.setContentView(contentView)

        val params =
            (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior

        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(p0: View, p1: Float) {

                }

                @SuppressLint("SwitchIntDef")
                override fun onStateChanged(p0: View, p1: Int) {
                    when (p1) {
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            activity?.scanner_view?.performClick()
                        }
                    }
                }

            })
        }

        val pelapak = contentView.findViewById<TextView>(R.id.pelapak)
        val market = contentView.findViewById<TextView>(R.id.pasar)
        val alamat = contentView.findViewById<TextView>(R.id.alamat)
        val phone = contentView.findViewById<TextView>(R.id.phone)

        pelapak.text = detailLapak?.penaggungJawab
        market.text = detailLapak?.namaPasar
        alamat.text = detailLapak?.alamat
        phone.text = detailLapak?.telepon
    }


    companion object {
        @JvmStatic
        fun newInstance(responseDetailPelapak: Data?) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(DETAIL, responseDetailPelapak)
                }
            }
    }
}
