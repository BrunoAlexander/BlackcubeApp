package com.example.dns.blackcub;



        import android.content.Context;
        import android.content.Intent;
        import android.media.Image;
        import android.net.Uri;
        import android.support.v7.app.ActionBarActivity;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ImageButton;
        import android.widget.TextView;

        import org.w3c.dom.Text;

        import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Kontakts extends ActionBarActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listik, menu);
        return true;
    }

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





    TextView site;
    ImageButton b;
    ImageButton b2;
    TextView call;
    ImageButton b3;
    TextView send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_kontakts);
        site = (TextView)findViewById(R.id.ourSite);
        b = (ImageButton)findViewById(R.id.buttSite);

        View.OnClickListener onClSite = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.blackcub.ru"));
                startActivity(intent);
            }
        };
        b.setOnClickListener(onClSite);
        site.setOnClickListener(onClSite);
        b2 = (ImageButton)findViewById(R.id.buttCall);
        call = (TextView)findViewById(R.id.callUsPlease);
        View.OnClickListener callUs = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialContactPhone("84232500886");
            }
        };
        b2.setOnClickListener(callUs);
        call.setOnClickListener(callUs);
        b3 = (ImageButton)findViewById(R.id.buttMail);
        send = (TextView)findViewById(R.id.sendMailId);

        View.OnClickListener sendEmail = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kontakts.this,send_mail.class);
                startActivity(intent);
            }
        };
        b3.setOnClickListener(sendEmail);
        send.setOnClickListener(sendEmail);



    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

