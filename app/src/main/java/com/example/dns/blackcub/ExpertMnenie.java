package com.example.dns.blackcub;

import java.io.IOException;
import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ExpertMnenie extends ActionBarActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listik, menu);
        return true;
    }

    Integer[]imgid = {
            R.drawable.lightblue,
            R.drawable.gray,
            R.drawable.blue,
    };
    ArrayList<String> hello = new ArrayList<String>();
    public Elements content;
    ListView list;

    public void gogo(MenuItem item) {
        Intent intent = new Intent(this, Analytics.class);
        startActivity(intent);
        finish();
    }
    public void letsStat(MenuItem item) {
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
        finish();
    }

    public void expMnen(MenuItem item) {
        Intent intent = new Intent(this,ExpertMnenie.class);
        startActivity(intent);
        finish();
    }

    public void onClickAboutCenter(MenuItem item) {
        Intent intent = new Intent(this,about_center.class);
        startActivity(intent);
        finish();
    }
    public void ourProjects(MenuItem item) {
        Intent intent = new Intent(this,Projects.class);
        startActivity(intent);
        finish();
    }
    public void contactUs(MenuItem item) {
        Intent intent = new Intent(this,Kontakts.class);
        startActivity(intent);
        finish();
    }

    public class NewThread extends AsyncTask<String, Void, String> {
        Document doc;
        @Override
        protected String doInBackground(String... params) {
            try{
                doc = Jsoup.connect("http://blackcub.ru/category/experts/").get();
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

    ArrayList<String> linkedList = new ArrayList<String>();
    public Elements links;
    Set<String> hs;
    public class NewThreadLink extends AsyncTask<String, Void, String>{
        Document doc;
        @Override
        protected String doInBackground(String... params) {
            try {
                doc = Jsoup.connect("http://blackcub.ru/category/experts/").get();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_expert_mnenie);
        list = (ListView)findViewById(R.id.listExpertMn);
        new NewThread().execute();
        adapter  = new CustomListAdapter(this,hello,imgid);
        new NewThreadLink().execute();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override //попробовать вставить код сюда
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ss = linkedList.get(position);
                Intent intent = new Intent(ExpertMnenie.this,LoadedPage.class);
                intent.putExtra("link",ss);
                startActivity(intent);
                //Toast toast = Toast.makeText(getApplicationContext(), linkedList.get(position), Toast.LENGTH_LONG);
                //toast.show();//примерно так надо будет получить все ссылки и дать им позицию. потом создать
                //невидимый листвью поверх листвью
            }
        });

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
