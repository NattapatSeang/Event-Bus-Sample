package com.nattapatsaeng.eventbussample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.greenrobot.eventbus.EventBus

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var editText = findViewById<EditText>(R.id.message)
        var button = findViewById<Button>(R.id.moveBtn)
        editText.setText(intent.getStringExtra("Message").toString());

        button.setOnClickListener {
            // broadcast to all activity that subscribed to event bus
            EventBus.getDefault().post(MessageEvent(editText.text.toString()))
            finish()
        }
    }
}