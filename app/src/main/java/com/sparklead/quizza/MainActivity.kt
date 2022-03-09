package com.sparklead.quizza

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER


//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btn_start.setOnClickListener {

            if(et_name.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Enter Your Name" , Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent = Intent(this ,QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.User_name,et_name.text.toString())

                startActivity(intent)

                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                finish()


            }
        }

    }
}


