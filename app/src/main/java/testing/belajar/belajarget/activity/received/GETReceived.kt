package testing.belajar.belajarget.activity.received

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import testing.belajar.belajarget.R

class GETReceived : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.receive_get)

        intent.getStringExtra("imageUri")

    }
}

