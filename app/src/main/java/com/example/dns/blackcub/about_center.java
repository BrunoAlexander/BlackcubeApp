package com.example.dns.blackcub;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.TextView;

        import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class about_center extends ActionBarActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listik, menu);
        return true;
    }

    public void gogo(MenuItem item) {
        Intent intent = new Intent(this,Analytics.class);
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_center);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

