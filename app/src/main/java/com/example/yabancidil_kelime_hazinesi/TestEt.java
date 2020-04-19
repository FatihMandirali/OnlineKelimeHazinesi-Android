package com.example.yabancidil_kelime_hazinesi;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yabancidil_kelime_hazinesi.Model.KelimelerDB;
import com.example.yabancidil_kelime_hazinesi.Reklam.ReklamGetir;

public class TestEt extends AppCompatActivity {
    LinearLayout btnTurkceTest,btnIngilizceTest;
    KelimelerDB kelimelerDB;
    LinearLayout lineerReklamBanner10,lineerReklamBanner11;
    ReklamGetir reklamGetir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_et);

        kelimelerDB=new KelimelerDB(getApplicationContext());
      final int size= kelimelerDB.TumKayitlar().size();
        btnTurkceTest=findViewById(R.id.btnTurkceTest);
        btnIngilizceTest=findViewById(R.id.btnIngilizceTest);
        lineerReklamBanner10=findViewById(R.id.lineerReklamBanner10);
        lineerReklamBanner11=findViewById(R.id.lineerReklamBanner11);



        reklamGetir=new ReklamGetir();

        String reklamKodu1=getString(R.string.reklambanner10);
        reklamGetir.BannerReklam(lineerReklamBanner10,getApplicationContext(),reklamKodu1);

        String reklamKodu2=getString(R.string.reklambanner11);
        reklamGetir.BannerReklam(lineerReklamBanner11,getApplicationContext(),reklamKodu2);
        btnTurkceTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(size<15){
                    Toast.makeText(getApplicationContext(),"Teste başlamak için listenizde en az 15 kelime olmalıdır.",Toast.LENGTH_LONG).show();
                }else{
                Intent i=new Intent(getApplicationContext(),TestBasla.class);
                startActivity(i);
                }
            }
        });
        btnIngilizceTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(size<15){
                    Toast.makeText(getApplicationContext(),"Teste başlamak için listenizde en az 15 kelime olmalıdır.",Toast.LENGTH_LONG).show();
                }else {
                    Intent i = new Intent(getApplicationContext(), TestEtIngilizce.class);
                    startActivity(i);
                }
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
