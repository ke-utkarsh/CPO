package ymsli.com.cpo.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ymsli.com.cpo.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        loadSplash()
    }


    private fun loadSplash()
    {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            finish()
            startActivity(intent)

        },2000)

    }
}