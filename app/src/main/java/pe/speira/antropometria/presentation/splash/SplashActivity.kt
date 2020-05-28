package pe.speira.antropometria.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pe.speira.antropometria.R
import pe.speira.antropometria.presentation.grupos.GruposActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread {
            Thread.sleep(2000)
            startActivity(Intent(this, GruposActivity::class.java))
            finish()
        }.start()

    }

}
