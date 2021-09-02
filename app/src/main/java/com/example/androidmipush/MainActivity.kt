package com.example.androidmipush

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.androidmipush.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pushalias.setOnClickListener{
            pushalias("title","alias")
        }
        binding.pushtopic.setOnClickListener{
            pushtopic("title","topic")
        }
    }

    private fun pushalias(title: String, content: String) {
        thread{
            try {
                val client = OkHttpClient()
                var link = "https://tdtt.top/send?alias=GVance&title=" + title + "&content=" + content
                Log.d("link", "${link}")
                val request = Request.Builder()
                    .url("${link}")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    showResponse(responseData)
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun pushtopic(title: String, content: String) {
        thread{
            try {

                val client = OkHttpClient()
                var link = "https://tdtt.top/send?topic=GF&title=" + title + "&content=" + content
                Log.d("link", "${link}")
                val request = Request.Builder()
                    .url("${link}")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    showResponse(responseData)
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            binding.showResponse.text = response
        }
    }
}