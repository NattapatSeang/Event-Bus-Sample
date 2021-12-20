package com.nattapatsaeng.eventbussample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var editText = findViewById<EditText>(R.id.message)
        var button = findViewById<Button>(R.id.moveBtn)

        button.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("Message",editText.text.toString())
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("test first", "finish start")
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

//    override fun onStop() {
//        Log.d("test first", "finish stop")
//        EventBus.getDefault().unregister(this)
//        super.onStop()
//    }

    // Unregistered once this activity is destroyed
    override fun onDestroy() {
        Log.d("test first", "finish stop")
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    // If still subscribed, this method will be called once publisher broadcast
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        var editText = findViewById<EditText>(R.id.message)
        editText.setText(event.message);
    }
}