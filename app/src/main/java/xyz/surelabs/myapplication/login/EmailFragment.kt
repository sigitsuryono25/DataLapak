package xyz.surelabs.myapplication.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.email_fragment.*
import xyz.surelabs.myapplication.R

class EmailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.email_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next.setOnClickListener {
            if (Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
                activity?.supportFragmentManager?.beginTransaction()?.detach(this)?.commit()

                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(
                        R.id.container,
                        PasswordFragment.newInstance(username.text.toString()),
                        "password"
                    )

                    ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    ?.commit()
            } else {
                username.error = "Please enter a valid email"
            }
        }
    }

}