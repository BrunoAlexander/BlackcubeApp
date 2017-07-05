package com.example.dns.blackcub;

import java.io.IOException;
import java.util.ArrayList;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Listik extends ActionBarActivity {

    ListView list;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listik, menu);
        return true;
    }

    public void gogo(MenuItem item) {
        Intent intent = new Intent(Listik.this,Analytics.class);
        startActivity(intent);
    }

    public void letsStat(MenuItem item) {
        Intent intent = new Intent(Listik.this, Statistics.class);
        startActivity(intent);
    }
    public void expMnen(MenuItem item) {
        Intent intent = new Intent(Listik.this,ExpertMnenie.class);
        startActivity(intent);
    }
    public void onClickAboutCenter(MenuItem item) {
        Intent intent = new Intent(Listik.this,about_center.class);
        startActivity(intent);
    }
    public void ourProjects(MenuItem item) {
        Intent intent = new Intent(Listik.this,Projects.class);
        startActivity(intent);
    }
    public void contactUs(MenuItem item) {
        Intent intent = new Intent(Listik.this,Kontakts.class);
        startActivity(intent);
    }
    String [] words = {
            "Политолог Наталья Коломейцева: Россию ждут перемены, но какие - пока неизвестно. Подробности в статье.",
            "Сверхопасные новости",
            "Здравствуй, мама!",
            "Письма из зоны",
            "Мама Двачера",
            "Патриоты Европы",
            "Гордость страны"
    };

    Integer[]imgid = {
            R.drawable.lightblue,
            R.drawable.gray,
            R.drawable.blue,
    };

    ArrayList<String> hello = new ArrayList<String>();

    Dialog dialog;
    Dialog dialog2;
    public Elements content;

    public void onClickHistory(View view) {
        Intent intent = new Intent(Listik.this, dayInHistory.class);
        startActivity(intent);
    }

    public class NewThread extends AsyncTask<String, Void, String>{

        Document doc;
        @Override
        protected String doInBackground(String... params) {
            try{
                doc = Jsoup.connect("http://blackcub.ru/category/analytics/").get();
                content = doc.select(".entry-header");
                hello.clear();
                for(Element contents: content){
                    hello.add(contents.text());
                }
            }catch (IOException e){
                e.printStackTrace();
            };

            return null;
        }@Override
         protected void onPostExecute(String result){
            list.setAdapter(adapter);
        }
    }
    CustomListAdapter adapter;
//получаем ссылочки в линкедлист, потом переназначаю на стринг, чтобы парсить страницу
    ArrayList<String> linkedList = new ArrayList<String>();
    public Elements links;
    Set<String> hs;
    public class NewThreadLink extends AsyncTask<String, Void, String>{
        Document doc;
        @Override
        protected String doInBackground(String... params) {
            try {
                doc = Jsoup.connect("http://blackcub.ru/category/analytics").get();
                links = doc.select("a");
                for (Element link : links) {
                    String s = link.attr("abs:href");
                    if (s.contains("2016")|s.contains("2015")) {

                        linkedList.add(s);
                        hs = new LinkedHashSet<>();
                        hs.addAll(linkedList);
                        linkedList.clear();
                        linkedList.addAll(hs);
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }
//получили



    String s = "HUY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listik);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        dialog = new Dialog(Listik.this);
        dialog2 = new Dialog(Listik.this);
        dialog2.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        dialog2.setContentView(R.layout.cifra);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        dialog.setContentView(R.layout.quoteoftheday);
        list = (ListView)findViewById(R.id.list23);
        new NewThreadLink().execute();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override //попробовать вставить код сюда
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String ss = linkedList.get(position);
                    Intent intent = new Intent(Listik.this,LoadedPage.class);
                    intent.putExtra("link",ss);
                    startActivity(intent);
                    //Toast toast = Toast.makeText(getApplicationContext(), linkedList.get(position), Toast.LENGTH_LONG);
                    //toast.show();//примерно так надо будет получить все ссылки и дать им позицию. потом создать
                    //невидимый листвью поверх листвью
            }
        });


        new NewThread().execute();

        adapter  = new CustomListAdapter(this,hello,imgid);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public void clickThat(View view) {//клик на цитату дня
        Intent intent = new Intent(Listik.this,Quote.class);
        startActivity(intent);
    }

    public void getCifra(View view) {
       Intent intent = new Intent(Listik.this,CifraDay.class);
        startActivity(intent);
    }
}
