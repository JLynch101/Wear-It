package ie.wit.wearit.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.wit.wearit.R
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import ie.wit.wearit.activities.RegisterActivity
import ie.wit.wearit.main.activites.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        Register_form_text.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        Login_form_button.setOnClickListener {
            if (login_email.text.trim().toString().isNotEmpty() || login_password.text.trim().toString().isNotEmpty()) {
                signInUser(login_email.text.trim().toString(), login_password.text.trim().toString())
            } else {
                Toast.makeText(this, "Input Required", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Error" + task.exception, Toast.LENGTH_LONG).show()
                    }
                }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser;

        if (user != null) {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
