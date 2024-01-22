package com.example.cursolinkedin

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cursolinkedin.ui.theme.CursoLinkedinTheme
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.material3.AlertDialog
import com.google.android.material.snackbar.Snackbar

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import android.content.Intent

class MainActivity : AppCompatActivity() {

    var incrementNumber = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val button: Button = findViewById(R.id.buttonOpenSecondActivity)
        button.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        var llmain : LinearLayout = findViewById(R.id.main)
        var btn1 : Button = findViewById(R.id.btn1)
        var btn2 : Button = findViewById(R.id.btn2)
        var btn3 : Button = findViewById(R.id.btn3)




        btn1.setOnClickListener {
            val defaultSnackbar = Snackbar.make(llmain, "Hii, dream developers", Snackbar.LENGTH_LONG)
            defaultSnackbar.show()
        }
        btn2.setOnClickListener {
            val customSnackbar = Snackbar.make(llmain, "Custom developer", Snackbar.LENGTH_LONG)
            customSnackbar.show()
        }
        btn3.setOnClickListener {
            val actionSnackbar = Snackbar.make(llmain, "Action developer", Snackbar.LENGTH_LONG)
            actionSnackbar.show()
            // MainActivity.kt o cualquier otra clase
            Log.d("EventBus", "Sending MessageEvent")
            EventBus.getDefault().post(MessageEvent("Hello everyone!"))
        }
        showEditTextDialog()
    }
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        Log.d("EventBus", "Received MessageEvent with message: ${event.message}")
        Snackbar.make(findViewById(R.id.main), event.message, Snackbar.LENGTH_LONG).show()
    }
    private fun showEditTextDialog(){
        var tv_textView : TextView = findViewById(R.id.tv_textView)

        tv_textView.setOnClickListener{

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_text_layout, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.et_editText)

            with(builder){
                setTitle("Enter some text")
                setPositiveButton("ok"){
                        dialog, wich -> tv_textView.text = editText.text.toString()
                }
                setView(dialogLayout)
                show()
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val userName = incrementNumber
        outState.putInt("savedInt", incrementNumber)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
                super.onRestoreInstanceState(savedInstanceState)
        val userInt = savedInstanceState.getInt("savedInt", 0)
        incrementNumber = userInt
        var tv_increment: TextView = findViewById(R.id.tv_increment)
        tv_increment.text = incrementNumber.toString()
    }
}
