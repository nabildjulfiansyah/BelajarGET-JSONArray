package testing.belajar.belajarget.activity.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_get.*
import org.json.JSONArray
import org.json.JSONException
import testing.belajar.belajarget.R
import testing.belajar.belajarget.activity.adapter.GETAdapter


class GETActivity : AppCompatActivity() {

    var idArray: ArrayList<String> = ArrayList()
    var userIdArray: ArrayList<String> = ArrayList()
    var titleArray: ArrayList<String> = ArrayList()
    var bodyArray: ArrayList<String> =  ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)

        AndroidNetworking.initialize(applicationContext)
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager

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
                            val test = response.getJSONObject(i)
                            idArray.add(test.getInt("id").toString())
                            userIdArray.add(test.getInt("userId").toString())
                            titleArray.add(test.getString("title"))
                            bodyArray.add(test.getString("body"))
                        }
                    }
                    catch (e : JSONException) {
                        e.printStackTrace()
                    }

                    val getAdapter = GETAdapter(this@GETActivity, idArray, userIdArray, titleArray, bodyArray)
                    recyclerView.adapter = getAdapter
                }

                override fun onError(anError: ANError?) {
                    Toast.makeText(this@GETActivity, "Gagal!", Toast.LENGTH_LONG).show()
                }

            })
    }
}
