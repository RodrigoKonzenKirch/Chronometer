package android.practice.com.chronometer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        final String initialTime = "00:00:00";
        final String buttonStartText = "Start";
        final String buttonPauseText = "Pause";
        final String buttonResumeText = "Resume";

        final CustomChronometer myChronometer = new CustomChronometer((Chronometer) findViewById(R.id.chronometer));
        myChronometer.SetUpChronometer();

        final Button startPauseButton = (Button) findViewById(R.id.button_start_pause);
        Button stopButton = (Button) findViewById(R.id.button_stop);
        Button captureButton = (Button) findViewById(R.id.button_capture);

        final Chronometer capturedTime1 = (Chronometer) findViewById(R.id.chronometer_captured_time_1);
        capturedTime1.setText(initialTime);
        final Chronometer capturedTime2 = (Chronometer) findViewById(R.id.chronometer_captured_time_2);
        capturedTime2.setText(initialTime);
        final Chronometer capturedTime3 = (Chronometer) findViewById(R.id.chronometer_captured_time_3);
        capturedTime3.setText(initialTime);
        startPauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (myChronometer.isMyCustomChronometerOnPause()){
                    myChronometer.StartCustomChronometer();
                    myChronometer.setIsMyCustomChronometerOnPause(false);
                    startPauseButton.setText(buttonPauseText);
                }else{
                    myChronometer.PauseCustomChronometer();
                    myChronometer.setIsMyCustomChronometerOnPause(true);
                    startPauseButton.setText(buttonResumeText);
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                myChronometer.StopCustomChronometer();
                myChronometer.setIsMyCustomChronometerOnPause(true);
                startPauseButton.setText(buttonStartText);
            }
        });

        captureButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                capturedTime3.setText(capturedTime2.getText());
                capturedTime2.setText(capturedTime1.getText());
                capturedTime1.setText(myChronometer.GetCurrentCustomChronometerTime());
            }
        });

    }

    public void countdownButtonClicked(final View v){
        final String initialCountdown = "0:00";
        final TextView showCountdown = (TextView) findViewById(R.id.textView_countdown);
        long milliseconds=0;
        if (showCountdown.getText().equals(initialCountdown)) {
            switch (v.getId()) {
                case R.id.button_countdown01:
                    milliseconds = 30000;
                    break;
                case R.id.button_countdown02:
                    milliseconds = 60000;
                    break;
                case R.id.button_countdown03:
                    milliseconds = 90000;
                    break;
                case R.id.button_countdown04:
                    milliseconds = 120000;
                    break;
                case R.id.button_countdown05:
                    milliseconds = 150000;
                    break;
                case R.id.button_countdown06:
                    milliseconds = 180000;
                    break;
                case R.id.button_countdown07:
                    milliseconds = 210000;
                    break;
                case R.id.button_countdown08:
                    milliseconds = 240000;
                    break;
            }
            new CountDownTimer(milliseconds, 1000) {
                public void onTick(long millisecondsUntilFinish) {
                    long s = millisecondsUntilFinish / 1000;
                    if (s < 60) {
                        showCountdown.setText(s < 10 ? "0:0" + s : "0:" + s);
                    } else {
                        long m = s / 60;
                        s = s % 60;
                        showCountdown.setText(m + ":" + (s < 10 ? "0" + s : +s));
                    }
                }

                public void onFinish() {
                    showCountdown.setText(initialCountdown);
                }
            }.start();
        }
    }

}
