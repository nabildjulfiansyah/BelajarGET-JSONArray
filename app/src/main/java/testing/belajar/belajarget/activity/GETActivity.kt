package testing.belajar.belajarget.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import org.json.JSONArray
import org.json.JSONException
import testing.belajar.belajarget.R

class GETActivity : AppCompatActivity() {

    private lateinit var tvUserId : TextView
    private lateinit var tvId : TextView
    private lateinit var tvTitle : TextView
    private lateinit var tvBody : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)

        tvUserId = findViewById(R.id.tvUserId)
        tvId = findViewById(R.id.tvId)
        tvTitle = findViewById(R.id.tvTitle)
        tvBody = findViewById(R.id.tvBody)

        getArray()
    }

    fun getArray(){
        AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts")
            .setTag("GET")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener{
                override fun onResponse(response: JSONArray?) {
                    val jArray = JSONArray(response.toString())
                    for (i in 0 until jArray.length()) {
                    try {
                        val oneObject = jArray.getJSONObject(i)

                        tvUserId.text = oneObject.getInt("userId").toString()
                        tvId.text = oneObject.getInt("id").toString()
                        tvTitle.text = oneObject.getString("title")
                        tvBody.text = oneObject.getString("body")
                    }
                    catch (e : JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onError(anError: ANError?) {
                }

            })
    }
}