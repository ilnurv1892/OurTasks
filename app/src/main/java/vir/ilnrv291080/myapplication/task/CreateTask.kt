package vir.ilnrv291080.myapplication.task

import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_task.*
import vir.ilnrv291080.myapplication.R
import vir.ilnrv291080.myapplication.task.model.Task
import java.util.*


class CreateTask : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)


        auth = FirebaseAuth.getInstance()



        tast_submit.setOnClickListener {

            val currentUser = auth.currentUser?.uid
            val uuid = UUID.randomUUID().toString()
            val title = create_task_titile.text.toString().trim()
            val description = create_task_description.text.toString().trim()

            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)) {
                val task: Task = Task(uuid, currentUser.toString(), title, description, 5)
                database.reference.child("tasks").child(task.uuid).setValue(task)
                finish()
            }

        }


    }
}