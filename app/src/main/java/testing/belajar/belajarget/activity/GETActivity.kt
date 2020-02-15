package testing.belajar.belajarget.activity

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.androidnetworking.interfaces.JSONArrayRequestListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import testing.belajar.belajarget.R


class GETActivity : AppCompatActivity() {

    private lateinit var tvUserId : TextView
    private lateinit var tvId : TextView
    private lateinit var tvTitle : TextView
    private lateinit var tvBody : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)

        AndroidNetworking.initialize(applicationContext)
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)

        tvUserId = findViewById(R.id.tvUserId)
        tvId = findViewById(R.id.tvId)
        tvTitle = findViewById(R.id.tvTitle)
        tvBody = findViewById(R.id.tvBody)

        getArray()
    }

    private fun getArray(){
        AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts")
            .setTag("GET")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener{
                override fun onResponse(response: JSONArray) {
                    Toast.makeText(this@GETActivity, "Berhasil", Toast.LENGTH_LONG).show()
                    try {
                        for (i in 0 until response.length()) {
                            val test = response.getJSONObject(i).getInt("userId")
                            val test1 = response.getJSONObject(i).getInt("id")
                            tvUserId.text = test.toString()
                            tvId.text = test1.toString()
                        }
                    }
                    catch (e : JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onError(anError: ANError?) {
                    Toast.makeText(this@GETActivity, "Gagal!", Toast.LENGTH_LONG).show()
                }

            })
    }
}