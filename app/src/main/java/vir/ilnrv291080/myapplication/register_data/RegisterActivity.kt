package vir.ilnrv291080.myapplication.register_data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import vir.ilnrv291080.myapplication.MainActivity
import vir.ilnrv291080.myapplication.R
import vir.ilnrv291080.myapplication.register_data.model.User

class RegisterActivity : AppCompatActivity() {

// TODO: 08.09.20  three instancess of main_activity, need to remove 2


    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        database = FirebaseDatabase.getInstance().reference

        auth = FirebaseAuth.getInstance()



        register_ok_btn.setOnClickListener {

            var email = register_email_edt.text.toString()
            var password = register_password_edt.text.toString()


            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    var userName = register_name_edt.text.toString()

                    var userId = auth.currentUser?.uid

                    writeNewUser(userId.toString(), userName, email)

                    updateUi()


                } else {
                    Toast.makeText(this, task.exception.toString() + " ", Toast.LENGTH_SHORT).show()

                }


            }


        }

    }

    private fun writeNewUser(userId: String, userName: String, email: String) {

        val user: User = User(userName, email)
        database.child("users").child(userId).setValue(user)
    }


    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUi()
            this.finish()
        }

    }


    private fun updateUi() {
        var startActivityIntent = Intent(this, MainActivity::class.java)

        val user = auth.currentUser


        startActivityIntent.putExtra("name", user?.email)

        startActivity(startActivityIntent)
    }
}