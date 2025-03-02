package com.example.myapplication;
import android.media.MediaPlayer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        // אתחול של MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.elevator_music); // רפלקציה של קובץ מוזיקה מהמדריך resources
        mediaPlayer.setLooping(true);  // אם רוצים שהמוזיקה תחזור על עצמה
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // הפעלת המוזיקה ברקע
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
        return START_STICKY; // אם השירות ייפול, הוא יופעל מחדש
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // הפסקת המוזיקה כאשר השירות נהרס
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // לא נדרשת חיבור של מחלקות
    }
}