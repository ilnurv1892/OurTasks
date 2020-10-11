package vir.ilnrv291080.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import vir.ilnrv291080.myapplication.register_data.LoginActivity
import vir.ilnrv291080.myapplication.task.CreateTask

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = FirebaseDatabase.getInstance().reference

        val get = intent.extras?.get("name")

        hello.text = get.toString()

        auth = FirebaseAuth.getInstance()



        create_task_activity_btn.setOnClickListener {
            startActivity(Intent(this,CreateTask::class.java))
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.logout_menu) {
            auth.signOut()
            this.finish()
            startActivity(Intent(this,LoginActivity::class.java))
        }
        return true
    }
}