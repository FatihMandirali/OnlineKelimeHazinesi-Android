package com.example.yabancidil_kelime_hazinesi;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yabancidil_kelime_hazinesi.Reklam.ReklamGetir;

public class TestSonu extends AppCompatActivity {
    LinearLayout btnAnasayfa,btnTekrarOyna;
    TextView txtSonucYanlis,txtSonucDogru;
    LinearLayout lineerReklamBanner14,lineerReklamBanner15;
    ReklamGetir reklamGetir,reklamGetir1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sonu);

        btnAnasayfa=findViewById(R.id.btnAnasayfa);
        btnTekrarOyna=findViewById(R.id.btnTekrarOyna);

        txtSonucDogru=findViewById(R.id.txtSonucDogru);
        txtSonucYanlis=findViewById(R.id.txtSonucYanlis);
        lineerReklamBanner14=findViewById(R.id.lineerReklamBanner14);
        lineerReklamBanner15=findViewById(R.id.lineerReklamBanner15);



        reklamGetir=new ReklamGetir();

        String reklamKodu1=getString(R.string.reklambanner14);
        reklamGetir.BannerReklam(lineerReklamBanner14,getApplicationContext(),reklamKodu1);

        String reklamKodu2=getString(R.string.reklambanner15);
        reklamGetir.BannerReklam(lineerReklamBanner15,getApplicationContext(),reklamKodu2);

        reklamGetir1=new ReklamGetir();
        reklamGetir1.GecisReklami(getApplicationContext());
        reklamGetir1.LoadGecisReklami();


        Bundle extras = getIntent().getExtras();
        String valueYanlis = extras.getString("send_Yanlis");
        String valueDogru = extras.getString("send_Dogru");
        txtSonucYanlis.setText(valueYanlis);
        txtSonucDogru.setText(valueDogru);

        btnAnasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
                reklamGetir1.ShowGecisReklami();
                finish();


            }
        });
        btnTekrarOyna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(getApplicationContext(),TestEt.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
                reklamGetir1.ShowGecisReklami();
                finish();

            }


        });


        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

    }

}
