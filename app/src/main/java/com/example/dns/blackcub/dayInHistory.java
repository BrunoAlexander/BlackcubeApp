package com.example.dns.blackcub;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class dayInHistory extends AppCompatActivity {

    String pic;
    public class trygetPic extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("https://www.dropbox.com/s/m0ggyue8x9d1qlw/%D0%94%D0%B5%D0%BD%D1%8C%20%D0%B2%20%D0%B8%D1%81%D1%82%D0%BE%D1%80%D0%B8%D0%B8.txt?dl=1");
                Scanner scn = new Scanner(url.openStream());
                pic = scn.nextLine();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            textSet();
        }
    }



    TextView histDay;
    TextView load;
    public void textSet(){
        load = (TextView)findViewById(R.id.loading);
        load.setVisibility(View.GONE);
        histDay = (TextView)findViewById(R.id.informAboutHist);
        histDay.setText(pic);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_in_history);
        new trygetPic().execute();
    }
}
