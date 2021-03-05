package ie.wit.wearit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import ie.wit.wearit.R
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        register_button.setOnClickListener {
            if (register_email.text.trim().toString().isNotEmpty() || register_password.text.trim()
                    .isNotEmpty()
            ) {
                createUser(
                    register_email.text.trim().toString(),
                    register_password.text.trim().toString()
                )
            } else {
                Toast.makeText(this, "Enter Details", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun createUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Log.e("Task Message","Successful")
                    Toast.makeText(baseContext, "Sign-up Successful.", Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.e("Task Message","Failed..."+task.exception)
                    Toast.makeText(baseContext, "Incorrect Login.", Toast.LENGTH_SHORT).show()
                }
            }

    }

}
