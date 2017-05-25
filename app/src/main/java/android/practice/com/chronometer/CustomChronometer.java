package android.practice.com.chronometer;

import android.os.SystemClock;
import android.widget.Chronometer;


class CustomChronometer {

    private final String chronometerInitialTime = "00:00:00";
    private Chronometer myCustomChronometer;
    private boolean isMyCustomChronometerOnPause = true;
    private long timeWhenPaused = 0;
    private long totalTimeWhenPaused = 0;

    CustomChronometer(Chronometer cArg){
        myCustomChronometer = cArg;
    }

    boolean isMyCustomChronometerOnPause() {
        return isMyCustomChronometerOnPause;
    }

    void setIsMyCustomChronometerOnPause(boolean isOnPauseArg){
        isMyCustomChronometerOnPause = isOnPauseArg;
    }


    void SetUpChronometer(){
        myCustomChronometer.setText(chronometerInitialTime);
        myCustomChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer cArg) {
                long time = SystemClock.elapsedRealtime() - cArg.getBase();
                int h = (int) (time / 3600000);
                int m = (int)(time - h*3600000)/60000;
                int s= (int)(time - h*3600000- m*60000)/1000 ;
                String hh = h < 10 ? "0"+h: h+"";
                String mm = m < 10 ? "0"+m: m+"";
                String ss = s < 10 ? "0"+s: s+"";
                cArg.setText(hh+":"+mm+":"+ss);
            }
        });

    }

    void StartCustomChronometer(){

        myCustomChronometer.setBase(SystemClock.elapsedRealtime() - timeWhenPaused);
        myCustomChronometer.start();
    }

    void StopCustomChronometer(){
        myCustomChronometer.stop();
        myCustomChronometer.setText(chronometerInitialTime);
        totalTimeWhenPaused = totalTimeWhenPaused + timeWhenPaused;
        timeWhenPaused = 0;
        totalTimeWhenPaused = 0;
    }

    void PauseCustomChronometer(){
        timeWhenPaused = SystemClock.elapsedRealtime() - myCustomChronometer.getBase();
        myCustomChronometer.stop();
    }

    String GetCurrentCustomChronometerTime(){

        long time;
        if (isMyCustomChronometerOnPause){
            time = timeWhenPaused;
        }else{
            time = (SystemClock.elapsedRealtime() - myCustomChronometer.getBase()) - totalTimeWhenPaused;
        }

        int h = (int) (time / 3600000);
        int m = (int)(time - h*3600000)/60000;
        int s= (int)(time - h*3600000- m*60000)/1000 ;
        String hh = h < 10 ? "0"+h: h+"";
        String mm = m < 10 ? "0"+m: m+"";
        String ss = s < 10 ? "0"+s: s+"";
        return (hh+":"+mm+":"+ss);
    }
}
