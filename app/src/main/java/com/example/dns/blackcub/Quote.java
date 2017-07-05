package com.example.dns.blackcub;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Quote extends AppCompatActivity {
    String pic;
    String textQoute;
    String author;
    public class trygetPic extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("https://www.dropbox.com/s/x8z5nq5fl1j7tqs/%D0%9A%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B0%20%D0%B4%D0%BB%D1%8F%20%D1%86%D0%B8%D1%82%D0%B0%D1%82%D1%8B%20%D0%B4%D0%BD%D1%8F.txt?dl=1");
                Scanner scn = new Scanner(url.openStream());
                pic = scn.nextLine();
                URL url1 = new URL("https://www.dropbox.com/s/4zp7dhso5a35zh4/%D0%A2%D0%B5%D0%BA%D1%81%D1%82%20%D1%86%D0%B8%D1%82%D0%B0%D1%82%D1%8B.txt?dl=1");
                Scanner scn1 = new Scanner(url1.openStream());
                textQoute = scn1.nextLine();
                URL url2 = new URL("https://www.dropbox.com/s/07yj83fr3qilus7/%D0%90%D0%B2%D1%82%D0%BE%D1%80%20%D1%86%D0%B8%D1%82%D0%B0%D1%82%D1%8B.txt?dl=1");
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
            pictureSet();
            textSet();
        }
    }

    ImageView pictureForQuote;
    public void pictureSet(){//устанавливаем головную картинку в шапке
        pictureForQuote = (ImageView)findViewById(R.id.imageView234);
        Picasso.with(getApplicationContext()).load(pic).into(pictureForQuote);
    }
    TextView textOfQuote;
    TextView quoteAuth;
    TextView load;
    public void textSet(){
        load = (TextView)findViewById(R.id.loading);
        load.setVisibility(View.GONE);
        textOfQuote = (TextView)findViewById(R.id.textView5);
        textOfQuote.setText(textQoute);
        quoteAuth = (TextView)findViewById(R.id.authorQuote);
        quoteAuth.setText(author);
        textOfQuote.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        new trygetPic().execute();
    }
}
