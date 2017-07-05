package com.example.dns.blackcub;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CifraDay extends AppCompatActivity {

    String pic;
    String textQoute;
    String author;
    public class trygetPic extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("https://www.dropbox.com/s/5pjgt1m6iezf0fg/%D0%A6%D0%B8%D1%84%D1%80%D0%B0.txt?dl=1");
                Scanner scn = new Scanner(url.openStream());
                pic = scn.nextLine();
                URL url1 = new URL("https://www.dropbox.com/s/toz94kpwauasb24/%D0%97%D0%BD%D0%B0%D1%87%D0%B5%D0%BD%D0%B8%D0%B5%20%D1%86%D0%B8%D1%84%D1%80%D1%8B.txt?dl=1");
                Scanner scn1 = new Scanner(url1.openStream());
                textQoute = scn1.nextLine();
                URL url2 = new URL("https://www.dropbox.com/s/5z7y4994egim9wo/%D0%98%D1%81%D1%82%D0%BE%D1%87%D0%BD%D0%B8%D0%BA%20%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D1%85%20%D1%86%D0%B8%D1%84%D1%80%D1%8B.txt?dl=1");
                Scanner scanner = new Scanner(url2.openStream());
                author = scanner.nextLine();
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
    TextView number;
    TextView textOfQuote;
    TextView quoteAuth;
    TextView load;
    public void textSet(){
        load = (TextView)findViewById(R.id.loading);
        load.setVisibility(View.GONE);
        number = (TextView)findViewById(R.id.numberOfThatDay);
        number.setText(pic);
        textOfQuote = (TextView)findViewById(R.id.informAboutCifra);
        textOfQuote.setText(textQoute);
        quoteAuth = (TextView)findViewById(R.id.authorOfNumber);
        quoteAuth.setText(author);
        textOfQuote.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cifra_day);
        new trygetPic().execute();
    }
}
