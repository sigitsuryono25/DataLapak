package xyz.surelabs.myapplication.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.password_input.*
import xyz.surelabs.myapplication.MainActivity
import xyz.surelabs.myapplication.R

private const val EMAIL_ARGS = "email"

class PasswordFragment : Fragment() {
    private var emailSet: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            emailSet = it?.getString(EMAIL_ARGS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.password_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email.text = emailSet
        signIn.setOnClickListener {
            activity?.finish()
            startActivity(Intent(activity, MainActivity::class.java))
        }

    }


    companion object {
        fun newInstance(email: String?) = PasswordFragment().apply {
            arguments = Bundle().apply {
                putString(EMAIL_ARGS, email)
            }
        }
    }

}