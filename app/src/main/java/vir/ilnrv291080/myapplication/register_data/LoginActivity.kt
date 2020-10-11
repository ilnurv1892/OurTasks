package vir.ilnrv291080.myapplication.register_data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import vir.ilnrv291080.myapplication.MainActivity
import vir.ilnrv291080.myapplication.R

class LoginActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()


        go_register_activity_btn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        login_ok_btn.setOnClickListener {

            var email = login_email_edt.text.toString()
            var password = login_password_edt.text.toString()


            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        updateUi()


                        Toast.makeText(
                            baseContext, "Authentication OK.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {


                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }


                }


        }
    }

    private fun updateUi() {

        var startActivityIntent = Intent(this, MainActivity::class.java)

        val user = auth.currentUser

        if (user != null) {
            startActivityIntent.putExtra("name", user?.email)

            startActivity(startActivityIntent)
            this.finish()
        }


    }


    override fun onStart() {
        super.onStart()


        val currentUser  = auth.currentUser
        if (currentUser != null) {
            updateUi()
        }


    }
}