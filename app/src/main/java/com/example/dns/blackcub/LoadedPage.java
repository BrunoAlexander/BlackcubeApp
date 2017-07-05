package com.example.dns.blackcub;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class LoadedPage extends AppCompatActivity{
    String imageLink;//ссылка на изображение, дада
    String headOfPage;//это голова картинки
    String mnenieExp;//это мнение эксперта
    String article;//это сама статья
    public Elements head;
    public Elements links;
    public Elements expertThink;
    public Elements articleCont;

    int clicked = 0;
    public void headClickPicture(View view) {
        if(clicked==0) {
            Picasso.with(getApplicationContext()).load(imageLink).resize(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight()).into(headPict);
            clicked++;
        }else{
            Picasso.with(getApplicationContext()).load(imageLink).resize(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight()/3).into(headPict);
            clicked--;
        }
    }//ахуевший метод увеличения картинки по клику. инт кликед - это сюда

    public class getDifferentSources extends AsyncTask<Void, Void, Void> {
        Document doc;
        @Override
        protected Void doInBackground(Void... params) {
            try {
                doc = Jsoup.connect(link).get();
                links = doc.getElementsByTag("img");
                head = doc.select(".entry-title");
                expertThink = doc.select(".post-excerpt");
                articleCont = doc.select(".entry-content");
                for (Element link : links) {
                    imageLink = (link.absUrl("src"));

                }
                for(Element pageHead : head){
                    headOfPage = head.text();
                }
                for(Element xp : expertThink){
                    mnenieExp = expertThink.text();
                }
                for(Element done : articleCont){
                    article = articleCont.text();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            pictureSet();//надо сделать ресайз пикчи! UPDATE:сделал
            headSet();//это текст шапки
        }
    }

    TextView shapka;
    TextView expGet;
    ImageView stripe;
    TextView artPart;
    public void headSet(){//этот метод устанавливает текст в шапку и ниже
        try {
            shapka = (TextView) findViewById(R.id.headHead);
            shapka.setText(headOfPage);
            expGet = (TextView) findViewById(R.id.expComment);
            expGet.setText(mnenieExp);
            stripe = (ImageView) findViewById(R.id.striper);
            stripe.setVisibility(View.VISIBLE);
            artPart = (TextView) findViewById(R.id.articlePart);
            if (article.contains(mnenieExp)) {
                article = article.substring(article.indexOf(mnenieExp) + mnenieExp.length() + 1, article.length() - 1);
            }
            artPart.setText(article);
        }catch (Exception e){
            showDialog(DIALOG_EXIT);
        }

    }
    final int DIALOG_EXIT = 1;
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            // заголовок
            adb.setTitle("УПС!");
            // сообщение
            adb.setMessage("Этот контент отображается не полностью или некорректно в приложении. Посмотреть в браузере?");
            // иконка
            adb.setIcon(android.R.drawable.ic_dialog_info);
            // кнопка положительного ответа
            adb.setPositiveButton("ДА", myClickListener);
            // кнопка отрицательного ответа
            adb.setNegativeButton("НЕТ", myClickListener);
            // кнопка нейтрального ответа
            adb.setNeutralButton("отмена", myClickListener);
            // создаем диалог
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:
                    saveData();
                    finish();
                    break;
                // негаитвная кнопка
                case Dialog.BUTTON_NEGATIVE:
                    Toast toast = Toast.makeText(getApplicationContext(),"Приятного просмотра!",Toast.LENGTH_LONG);
                    toast.show();
                    break;
                // нейтральная кнопка
                case Dialog.BUTTON_NEUTRAL:
                    break;
            }
        }
    };

    void saveData() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }














    public void pictureSet(){//устанавливаем головную картинку в шапке
        headPict = (ImageView)findViewById(R.id.headPict);
        Picasso.with(getApplicationContext()).load(imageLink).resize(getWindowManager().getDefaultDisplay().getWidth(),getWindowManager().getDefaultDisplay().getHeight()/3).into(headPict);
    }

    String link;//здесь хранится ссылка на страницу, с которой беру данные
    ImageView headPict;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_loaded_page);
            Intent intent = getIntent();
            link = intent.getStringExtra("link");
            new getDifferentSources().execute();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}


