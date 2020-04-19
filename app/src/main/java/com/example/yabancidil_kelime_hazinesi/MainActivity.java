package com.example.yabancidil_kelime_hazinesi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yabancidil_kelime_hazinesi.Model.Kelimeler;
import com.example.yabancidil_kelime_hazinesi.Model.KelimelerDB;
import com.example.yabancidil_kelime_hazinesi.Reklam.ReklamGetir;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout btnKelimeGor,btnKelimeEkle,btnKisiselBilgi,btnTestEt;
    public static final String SHARED_PREFS="sharedPrefs";
    ReklamGetir reklamGetir;
    LinearLayout lineerReklamBanner6,lineerReklamBanner7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnKelimeGor=findViewById(R.id.btnKelimeGor);
        btnKelimeEkle=findViewById(R.id.btnKelimeEkle);
        btnKisiselBilgi=findViewById(R.id.btnKisiselBilgi);
        btnTestEt=findViewById(R.id.btnTestEt);
        lineerReklamBanner6=findViewById(R.id.lineerReklamBanner6);
        lineerReklamBanner7=findViewById(R.id.lineerReklamBanner7);



        reklamGetir=new ReklamGetir();

        String reklamKodu1=getString(R.string.reklambanner6);
        reklamGetir.BannerReklam(lineerReklamBanner6,getApplicationContext(),reklamKodu1);

        String reklamKodu2=getString(R.string.reklambanner7);
        reklamGetir.BannerReklam(lineerReklamBanner7,getApplicationContext(),reklamKodu2);

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit(); //SharedPreferences'a kayıt eklemek için editor oluşturuyoruz

        int gS= sharedPref.getInt("OyunaGiris",0)+1;
        editor.putInt("OyunaGiris",gS); //int değer ekleniyor
        editor.apply();

        btnKelimeGor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),KelimeleriGor.class);
                startActivity(i);
            }
        });

        btnKelimeEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),KelimeEkle.class);
                startActivity(i);
            }
        });
        btnTestEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),TestEt.class);
                startActivity(i);
            }
        });
        btnKisiselBilgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),KisiselBilgi.class);
                startActivity(i);
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
