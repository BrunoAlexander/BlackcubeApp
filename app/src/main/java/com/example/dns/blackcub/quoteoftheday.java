package com.example.dns.blackcub;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by dns on 05.08.2016.
 */
public class quoteoftheday extends AppCompatActivity {

    String pic;
    public class trygetPic extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("https://www.dropbox.com/s/x8z5nq5fl1j7tqs/%D0%9A%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B0%20%D0%B4%D0%BB%D1%8F%20%D1%86%D0%B8%D1%82%D0%B0%D1%82%D1%8B%20%D0%B4%D0%BD%D1%8F.txt?dl=1");
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
            pictureSet();
        }
    }

    ImageView pictureForQuote;
    public void pictureSet(){//устанавливаем головную картинку в шапке
        pictureForQuote = (ImageView)findViewById(R.id.imageView234);
        Picasso.with(getApplicationContext()).load(pic).into(pictureForQuote);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quoteoftheday);
        new trygetPic().execute();

    }
}
