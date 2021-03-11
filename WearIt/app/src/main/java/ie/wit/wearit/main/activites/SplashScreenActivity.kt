package ie.wit.wearit.main.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import ie.wit.wearit.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

    @Suppress("DEPRECATION")
    Handler().postDelayed(
        {
            startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            finish()
        },
        3000
    )
    }
}