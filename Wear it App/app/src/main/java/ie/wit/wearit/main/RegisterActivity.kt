

package ie.wit.wearit.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import ie.wit.wearit.R
import ie.wit.wearit.main.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        register_form_button.setOnClickListener {
            if (register_email.text.trim().toString().isNotEmpty() || register_password.text.trim().isNotEmpty() || register_fullname.text.trim().isNotEmpty()
            ) {
                createUser(
                    register_email.text.trim().toString(),
                    register_password.text.trim().toString(),
                        register_fullname.text.trim().toString()
                )
            } else {
                Toast.makeText(this, "Please Enter Details", Toast.LENGTH_LONG).show()
            }
        }
        account_already.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun createUser(email:String, password:String, fullname:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Log.e("Task Message","Successful")
                    Toast.makeText(baseContext, "Sign-up Successful.", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Log.e("Task Message","Failed..."+task.exception)
                    Toast.makeText(baseContext, "Incorrect Login.", Toast.LENGTH_SHORT).show()
                }
            }

    }

    }

