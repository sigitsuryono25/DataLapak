package xyz.surelabs.myapplication.login.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import xyz.surelabs.myapplication.R
import xyz.surelabs.myapplication.login.EmailFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        supportFragmentManager.beginTransaction().add(R.id.container, EmailFragment(), "email")
            .commit()

    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentByTag("email")?.isDetached == true) {
            supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .detach(supportFragmentManager.findFragmentByTag("password")!!).commit()
            supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .attach(supportFragmentManager.findFragmentByTag("email")!!).commit()
            Log.d("Detach", "detach")
        } else {
            super.onBackPressed()
        }
    }
}
