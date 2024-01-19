package com.example.cursolinkedin

import android.os.Bundle
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



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        }
        showEditTextDialog()
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

}
