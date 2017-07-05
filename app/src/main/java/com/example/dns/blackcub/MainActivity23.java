package com.example.dns.blackcub;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class MainActivity23 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity23.this,Listik.class);
                startActivity(intent);
                MainActivity23.this.finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 4000);

    }
}

