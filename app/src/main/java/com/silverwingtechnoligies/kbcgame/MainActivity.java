package com.silverwingtechnoligies.kbcgame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ProgressBar mProgressBar;

    TextView progress;

    CountDownTimer mcountDownTimer2;

    int i=10;
    int j=110;

    int index=0;

    int flg=1;

    MediaPlayer player;
    MediaPlayer winnwrAndser;
    MediaPlayer wrongAnswer;
    MediaPlayer rightAnswer;

    TextView question;

    TextView tv_q1;

    TextView tv_q2;

    TextView tv_q3;

    TextView tv_q4;


    TextView step7;
    TextView step6;
    TextView step5;
    TextView step4;
    TextView step3;
    TextView step2;
    TextView step1;


    TextView tv_5050;

    TextView tv_double_tap;

    List<DataGeneralGameKbg> kbgQues;

    ImageView iv_profile;
    TextView tv_name;
    TextView tv_score;


    String point="";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressbar);
        tv_double_tap = findViewById(R.id.tv_double_tap);
        tv_5050 = findViewById(R.id.tv_5050);

        iv_profile = findViewById(R.id.iv_profile);
        tv_name = findViewById(R.id.tv_name);
        tv_score = findViewById(R.id.tv_score);

        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        step4 = findViewById(R.id.step4);
        step5 = findViewById(R.id.step5);
        step6 = findViewById(R.id.step6);
        step7 = findViewById(R.id.step7);


        tv_q4 = findViewById(R.id.tv_q4);
        tv_q3 = findViewById(R.id.tv_q3);
        tv_q2 = findViewById(R.id.tv_q2);
        tv_q1 = findViewById(R.id.tv_q1);

        question = findViewById(R.id.question);
        progress = findViewById(R.id.progress);

        kbgQues = new ArrayList<>();

        kbgQues.add(new DataGeneralGameKbg("2+2","1","2","3","4","4","1,00,000"));
        kbgQues.add(new DataGeneralGameKbg("3+5","9","8","7","5","8","5,00,000"));
        kbgQues.add(new DataGeneralGameKbg("1+7","8","7","6","5","8","10,00,000"));
        kbgQues.add(new DataGeneralGameKbg("4-2","2","3","4","5","2","25,00,000"));
        kbgQues.add(new DataGeneralGameKbg("8+4-1","10","11","12","13","11","50,00,000"));
        kbgQues.add(new DataGeneralGameKbg("7-3+1","4","3","5","7","5","75,00,000"));
        kbgQues.add(new DataGeneralGameKbg("8+7-9","3","4","5","6","6","1,00,00,000"));
        kbgQues.add(new DataGeneralGameKbg("8+2-9","1","4","5","6","1","5,00,00,000"));
        kbgQues.add(new DataGeneralGameKbg("9+7-9","3","7","5","6","7","7,00,00,000"));

        intCode();

        tv_score.setText(" ₹ "+point+" won");


        if (SaveData.profil2 != null) {
            iv_profile.setImageBitmap(SaveData.profil2);
        } else if (SaveData.profile != null) {
            iv_profile.setImageURI(SaveData.profile);

        }

        if (SaveData.Name.length() > 2) {
            tv_name.setText(SaveData.Name);
        }

    }


    private void intCode() {


        tv_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chechAns(tv_q1);

            }
        });
        tv_q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chechAns(tv_q2);

            }
        });

        tv_q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chechAns(tv_q3);

            }
        });

        tv_q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chechAns(tv_q4);

            }
        });

        setData();

    }


    public void chechAns(final TextView tv){

        final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_sure);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView iv_yes = dialog.findViewById(R.id.iv_yes);
        TextView iv_no = dialog.findViewById(R.id.iv_no);

        iv_yes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                dialog.dismiss();

                j=10;
                mProgressBar.setProgress(j);

                if (tv.getText().toString().trim().equalsIgnoreCase(kbgQues.get(index).getAnswer().trim())){
                    point = kbgQues.get(index).getWinPoint();
                    index++;
                    tv_score.setText(" ₹ "+point+" won");

                    rightAnswer();

                }else {

                    if (flg!=2){
                        wrongAns();

                    }else {
                        flg=1;
                        Toast.makeText(MainActivity.this, getString(R.string.tap), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        iv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    @SuppressLint("SetTextI18n")
    public void wrongAns(){
        wrongAnswer.start();

        final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_wrong);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tv_title = dialog.findViewById(R.id.tv_title);
        ImageView iv_close  = dialog.findViewById(R.id.iv_close);
        TextView iv_play  = dialog.findViewById(R.id.iv_play);
        TextView textPoint = dialog.findViewById(R.id.tv_point);

        //textPoint.style(this,preferenceManager.getFontID());
        textPoint.setText(getResources().getString(R.string.yougot)+" "+point+"  "+getResources().getString(R.string.point));

        //tv_title.style(this,preferenceManager.getFontID());
        tv_title.setText(getResources().getString(R.string.wrongans));

        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                wrongAnswer.pause();
                wrongAnswer.stop();
                if (mcountDownTimer2!=null) {
                    mcountDownTimer2.cancel();
                    player.pause();
                }
                Intent intent= getIntent();
                finish();
                startActivity(intent);

            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                wrongAnswer.pause();
                wrongAnswer.stop();
                if (mcountDownTimer2!=null) {
                    mcountDownTimer2.cancel();
                    player.pause();
                }
                finish();
            }
        });

        dialog.show();

        if (mcountDownTimer2!=null) {
            mcountDownTimer2.cancel();
            player.pause();
        }



    }

    public void rightAnswer(){

        winnwrAndser.start();
        if (mcountDownTimer2!=null) {
            mcountDownTimer2.cancel();
            player.pause();
        }

        final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_wrong);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tv_title = dialog.findViewById(R.id.tv_title);
        ImageView iv_close  = dialog.findViewById(R.id.iv_close);
        TextView iv_play  = dialog.findViewById(R.id.iv_play);
        TextView textPoint = dialog.findViewById(R.id.tv_point);
        iv_play.setText("Ok");


        //textPoint.style(this,preferenceManager.getFontID());
        textPoint.setText(getResources().getString(R.string.yougot)+" "+point+"  "+getResources().getString(R.string.point));

        //tv_title.style(this,preferenceManager.getFontID());
        tv_title.setText(getResources().getString(R.string.right_ans));

        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                winnwrAndser.pause();
                wrongAnswer.stop();
                if (mcountDownTimer2!=null) {
                    mcountDownTimer2.cancel();
                    player.pause();
                }
                setData();

            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                winnwrAndser.pause();
                wrongAnswer.stop();
                if (mcountDownTimer2!=null) {
                    mcountDownTimer2.cancel();
                    player.pause();
                }
                setData();
            }
        });

        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    public void winner(){

        winnwrAndser.start();
        final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_wrong);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tv_title = dialog.findViewById(R.id.tv_title);
        ImageView iv_close  = dialog.findViewById(R.id.iv_close);
        TextView iv_play  = dialog.findViewById(R.id.iv_play);
        TextView textPoint = dialog.findViewById(R.id.tv_point);



        //textPoint.style(this,preferenceManager.getFontID());
        textPoint.setText(getResources().getString(R.string.yougot)+" "+point+"  "+getResources().getString(R.string.point));

        //tv_title.style(this,preferenceManager.getFontID());
        tv_title.setText(getResources().getString(R.string.winner));

        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                winnwrAndser.pause();
                wrongAnswer.stop();
                if (mcountDownTimer2!=null) {
                    mcountDownTimer2.cancel();
                    player.pause();
                }
                Intent intent= getIntent();
                finish();
                startActivity(intent);

            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                winnwrAndser.pause();
                wrongAnswer.stop();
                if (mcountDownTimer2!=null) {
                    mcountDownTimer2.cancel();
                    player.pause();
                }
                finish();
            }
        });

        dialog.show();

        if (mcountDownTimer2!=null) {
            mcountDownTimer2.cancel();
            player.pause();
        }
    }


    @SuppressLint("SetTextI18n")
    public void timesUp(){

        try {
            if (mcountDownTimer2!=null) {
                mcountDownTimer2.cancel();
                player.pause();
            }
            wrongAnswer.start();
            final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_wrong);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView tv_title = dialog.findViewById(R.id.tv_title);
            ImageView iv_close  = dialog.findViewById(R.id.iv_close);
            TextView iv_play  = dialog.findViewById(R.id.iv_play);
            TextView textPoint = dialog.findViewById(R.id.tv_point);

            // textPoint.style(this,preferenceManager.getFontID());
            textPoint.setText(getResources().getString(R.string.yougot)+" "+point+" "+getResources().getString(R.string.point));

            // tv_title.style(this,preferenceManager.getFontID());
            tv_title.setText(getResources().getString(R.string.timeover));

            iv_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wrongAnswer.pause();
                    wrongAnswer.stop();

                    dialog.dismiss();
                    if (mcountDownTimer2!=null) {
                        mcountDownTimer2.cancel();
                        player.pause();
                    }

                    Intent intent= getIntent();
                    finish();
                    startActivity(intent);

                }
            });
            iv_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    wrongAnswer.pause();
                    wrongAnswer.stop();
                    if (mcountDownTimer2!=null) {
                        mcountDownTimer2.cancel();
                        player.pause();
                    }
                    finish();
                }
            });

            dialog.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void setData(){

        tv_score.setText(" ₹ "+point+" won");

        flg=1;

        tv_double_tap.setBackgroundTintList(null);
        tv_5050.setBackgroundTintList(null);

        if (kbgQues!=null && kbgQues.size()>0 && index<kbgQues.size()) {

            if (index<7) {


                question.setText(kbgQues.get(index).getQuestion());

                tv_q1.setText(kbgQues.get(index).getOption1());
                tv_q2.setText(kbgQues.get(index).getOption2());


                tv_q3.setText(kbgQues.get(index).getOption3());

                tv_q4.setText(kbgQues.get(index).getOption4());


                hideans(tv_q1);
                hideans(tv_q2);
                hideans(tv_q3);
                hideans(tv_q4);


                if (index == 1) {
                    step1.setBackgroundColor(ContextCompat.getColor(this, R.color.green_400));
                } else if (index == 2) {
                    step2.setBackgroundColor(ContextCompat.getColor(this, R.color.green_400));
                } else if (index == 3) {
                    step3.setBackgroundColor(ContextCompat.getColor(this, R.color.green_400));
                } else if (index == 4) {
                    step4.setBackgroundColor(ContextCompat.getColor(this, R.color.green_400));
                } else if (index == 5) {
                    step5.setBackgroundColor(ContextCompat.getColor(this, R.color.green_400));
                } else if (index == 6) {
                    step6.setBackgroundColor(ContextCompat.getColor(this, R.color.green_400));
                } else if (index == 7) {
                    step7.setBackgroundColor(ContextCompat.getColor(this, R.color.green_400));
                }

                if (mcountDownTimer2!=null) {
                    mcountDownTimer2.cancel();
                    player.pause();
                }

                setFlip(question);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showans(tv_q1);
                        setFlip(tv_q1);
                    }
                }, 1000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showans(tv_q2);
                        setFlip(tv_q2);
                    }
                }, 1500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showans(tv_q3);
                        setFlip(tv_q3);
                    }
                }, 2000);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showans(tv_q4);
                        setFlip(tv_q4);
                    }
                }, 2500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        strat();
                    }
                }, 3000);

            }else {

                winner();

            }
        }else {

            Toast.makeText(this, "No Question Added", Toast.LENGTH_SHORT).show();
        }
    }


    public void setFlip(TextView slpTextview){

        Animation anim= AnimationUtils.loadAnimation(MainActivity.this, R.anim.flip);
        slpTextview.setAnimation(anim);

    }


    private void hideans(TextView slpTextview){
        slpTextview.setVisibility(View.GONE);
    }
    private void showans(TextView slpTextview){
        slpTextview.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void strat(){


       // mProgressBar.setProgress(j);
        player=MediaPlayer.create(this,R.raw.timer);
        wrongAnswer=MediaPlayer.create(this,R.raw.wrong_answer);
        rightAnswer=MediaPlayer.create(this,R.raw.kbc_winner);
        winnwrAndser=MediaPlayer.create(this,R.raw.kbc_winner);


        j=10;

        mProgressBar.setProgress(j);


        mcountDownTimer2=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progress.setText(""+i);
                i--;
                j--;

                int per = j*100/10;

                mProgressBar.setProgress(per);

            }

            @Override
            public void onFinish() {
                i=0;
                progress.setText(""+i);
                try {
                    timesUp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                j=0;
                mProgressBar.setProgress(j);
            }
        };

        player.start();
        mcountDownTimer2.start();
        i=10;
        progress.setText(""+i);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player!=null){
            player.pause();
            player.stop();
        }    }

    @Override
    protected void onPause() {
        super.onPause();

        if (player!=null){
            player.pause();
            player.stop();
        }
    }


    public void closeGame(View view) {
        if (player!=null){
            player.pause();
            player.stop();
        }
        if (mcountDownTimer2!=null){
            mcountDownTimer2.cancel();
        }
        finish();
    }


    public void on5050(View view) {
        if (tv_5050.getTag().toString().equalsIgnoreCase("0")){

            tv_5050.setBackgroundTintList(getResources().getColorStateList(R.color.green_600));

            if (tv_q1.getText().toString().equalsIgnoreCase(kbgQues.get(index).getAnswer())) {
                tv_q2.setVisibility(View.GONE);
                tv_q3.setVisibility(View.GONE);
            }
            if (tv_q2.getText().toString().equalsIgnoreCase(kbgQues.get(index).getAnswer())) {
                tv_q1.setVisibility(View.GONE);
                tv_q4.setVisibility(View.GONE);
            }
            if (tv_q3.getText().toString().equalsIgnoreCase(kbgQues.get(index).getAnswer())) {

                tv_q1.setVisibility(View.GONE);
                tv_q4.setVisibility(View.GONE);

            }
            if (tv_q4.getText().toString().equalsIgnoreCase(kbgQues.get(index).getAnswer())) {

                tv_q1.setVisibility(View.GONE);
                tv_q3.setVisibility(View.GONE);


            }

            tv_5050.setTag("1");

        }else {

            Toast.makeText(this, getString(R.string.optionuse), Toast.LENGTH_SHORT).show();

        }

    }

    public void onDouble(View view) {

        if (tv_double_tap.getTag().toString().equalsIgnoreCase("0")) {

            flg =2;

            tv_double_tap.setBackgroundTintList(getResources().getColorStateList(R.color.green_600));


            tv_double_tap.setTag("1");

        }else {

            Toast.makeText(this, getString(R.string.optionuse), Toast.LENGTH_SHORT).show();

        }
    }
}



