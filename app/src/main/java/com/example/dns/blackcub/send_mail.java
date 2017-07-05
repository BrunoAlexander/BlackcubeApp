package com.example.dns.blackcub;

import android.net.Uri;
import android.widget.Toast;


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class send_mail extends AppCompatActivity {
    TextView captcha;
    EditText enter;
    EditText letter;
    String s1;
    Button send;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_send_mail);
        captcha = (TextView)findViewById(R.id.numbers);
        int a = (int)(Math.random()*10000);
        s = Integer.toString(a);
        captcha.setText(s);
        send = (Button)findViewById(R.id.btnSend);
        send.setEnabled(true);



        View.OnClickListener click = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                enter = (EditText)findViewById(R.id.numbersEntered);
                letter = (EditText)findViewById(R.id.editTextMail);
                s1 = enter.getText().toString();
                String textt = letter.getText().toString();
                Toast toast;
                if(s1.equals(s)&letter.length()>0) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto: e43632@mvrht.com"));
                    emailIntent.putExtra(Intent.EXTRA_TEXT,textt);
                    startActivity(Intent.createChooser(emailIntent, "Отправить письмо"));
                    finish();
                }else if(letter.length()==0) {
                    toast = Toast.makeText(getApplicationContext(), "Вы не написали письмо!", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    toast = Toast.makeText(getApplicationContext(), "Введен неверный код!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        };




        send.setOnClickListener(click);


    }
}

