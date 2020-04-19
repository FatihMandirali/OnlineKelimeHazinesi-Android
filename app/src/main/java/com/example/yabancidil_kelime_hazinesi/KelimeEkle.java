package com.example.yabancidil_kelime_hazinesi;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yabancidil_kelime_hazinesi.Model.Kelimeler;
import com.example.yabancidil_kelime_hazinesi.Model.KelimelerDB;
import com.example.yabancidil_kelime_hazinesi.Reklam.ReklamGetir;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;


public class KelimeEkle extends AppCompatActivity {
    LinearLayout lineerReklamBanner1,lineerReklamBanner2;
    EditText txtKelime,txtKelimeAnlami;
    Button btnKayitEkle;
    KelimelerDB db;
    List<Kelimeler> kelimelis;
   ReklamGetir reklamGetir;

   // private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelime_ekle);
        txtKelime=findViewById(R.id.txtKelime);
        txtKelimeAnlami=findViewById(R.id.txtKelimeAnlami);
        btnKayitEkle=findViewById(R.id.btnGuncelle);

        kelimelis=new ArrayList<Kelimeler>();
        lineerReklamBanner1=findViewById(R.id.lineerReklamBanner1);
        lineerReklamBanner2=findViewById(R.id.lineerReklamBanner2);



       reklamGetir=new ReklamGetir();

        String reklamKodu1=getString(R.string.reklambanner1);
        reklamGetir.BannerReklam(lineerReklamBanner1,getApplicationContext(),reklamKodu1);

        String reklamKodu2=getString(R.string.reklambanner2);
        reklamGetir.BannerReklam(lineerReklamBanner2,getApplicationContext(),reklamKodu2);


        reklamGetir.GecisReklami(getApplicationContext());
        reklamGetir.LoadGecisReklami();


 /*


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/

        btnKayitEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kelime=txtKelime.getText().toString();
                String anlami=txtKelimeAnlami.getText().toString();

                if(txtKelime.getText().toString().trim().equals("") || txtKelimeAnlami.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Lütfen Boş Yerleri Doldurun..",Toast.LENGTH_LONG).show();

                }else{
                    Kelimeler kelimeler=new Kelimeler("İngilizce",kelime,anlami);
                    KelimelerDB db=new KelimelerDB(getApplicationContext());
                    long id= db.KayitEkle(kelimeler);
                    if(id==-1){
                        Toast.makeText(getApplicationContext(),"Kayıt Eklenirken Hata Oluştu..",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Kayıt Başarılı..",Toast.LENGTH_LONG).show();
                        kelimelis=db.TumKayitlar();


                        reklamGetir.ShowGecisReklami();


                    }
                    txtKelimeAnlami.setText("");
                    txtKelime.setText("");
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
