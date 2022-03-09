package com.sparklead.quizza

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import android.content.Intent as Intent

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentposition : Int =1
    private var mQuestionslist :ArrayList<Question>?= null
    private var mSelectedoptionposition : Int =0
    private var mCorrectAnswer :Int =0
    private var mUserName :String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.User_name)

        mQuestionslist = Constants.getQuestions()


        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    private fun setQuestion(){


        val question = mQuestionslist!![mCurrentposition-1]

        defaultOptionsView()

        if(mCurrentposition==mQuestionslist!!.size){
            btn_submit.text ="Finish"
        }
        else
        {
            btn_submit.text = "submit"
        }

        progressbar.progress =mCurrentposition
        tv_progress.text = "$mCurrentposition"+ "/" + progressbar.max

        tv_question.text = question.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.OptionOne
        tv_option_two.text =question.OptionTwo
        tv_option_three.text = question.OptionThree
        tv_option_four.text = question.OptionFour


    }

    private fun defaultOptionsView(){

        var options = ArrayList<TextView>()

        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)


        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT

            option.elevation = 10f

            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_background
            )

        }

    }

    override fun onClick(v: View?) {

        when(v?.id)
        {
            R.id.tv_option_one->{
                selectedoption(tv_option_one , 1)
            }
            R.id.tv_option_two->{
                selectedoption(tv_option_two,2)
            }
            R.id.tv_option_three->{
                selectedoption(tv_option_three,3)
            }
            R.id.tv_option_four->{
                selectedoption(tv_option_four,4)
            }


            R.id.btn_submit->{

                if(mSelectedoptionposition==0)
                {
                    mCurrentposition++

                    when{
                        mCurrentposition <=mQuestionslist!!.size->{
                            setQuestion()
                        }
                        else->
                        {
                           val intent = Intent(this , ResultActivity::class.java)
                            intent.putExtra(Constants.User_name,mUserName)
                            intent.putExtra(Constants.Correct_Question,mCorrectAnswer)
                            intent.putExtra(Constants.Total_Question,mQuestionslist!!.size)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                            finish()
                        }
                    }
                }
                else
                {
                    val question = mQuestionslist!![mCurrentposition-1]

                    if(question.CorrectAnswer != mSelectedoptionposition)
                    {
                        answerView( mSelectedoptionposition , R.drawable.wrong_background )
                    }
                    else
                    {
                        mCorrectAnswer ++
                    }

                    answerView(question.CorrectAnswer, R.drawable.answer_background)

                    if(mCurrentposition == mQuestionslist!!.size){
                        btn_submit.text ="Finish"
                    }
                    else
                    {
                        btn_submit.text ="Go to next question"
                    }
                    mSelectedoptionposition=0

                }
            }
        }
    }

    private fun answerView(answer :Int , drawableView : Int){
        when(answer)
        {
            1->{
                tv_option_one.background =ContextCompat.getDrawable(this , drawableView)
            }
            2->{
                tv_option_two.background = ContextCompat.getDrawable(this,drawableView)
            }
            3->{
                tv_option_three.background = ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                tv_option_four.background = ContextCompat.getDrawable(this,drawableView)
            }

        }
    }


    private fun selectedoption(tv:TextView,
                    selectedoptionnum:Int){

        defaultOptionsView()
        mSelectedoptionposition = selectedoptionnum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)

        tv.elevation = 50f

        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_background

        )
    }
}