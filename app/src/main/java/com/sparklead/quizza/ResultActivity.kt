package com.sparklead.quizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val username = intent.getStringExtra(Constants.User_name)
        val correct = intent.getIntExtra(Constants.Correct_Question,0)
        val total = intent.getIntExtra(Constants.Total_Question,0)

        userName.text = username
        score.text = "Your Score is $correct/$total"



        btn_finish.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left)
            finish()


        }
    }
}