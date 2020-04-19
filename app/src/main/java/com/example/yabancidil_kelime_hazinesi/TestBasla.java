package com.example.yabancidil_kelime_hazinesi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yabancidil_kelime_hazinesi.Model.Kelimeler;
import com.example.yabancidil_kelime_hazinesi.Model.KelimelerDB;
import com.example.yabancidil_kelime_hazinesi.Model.RastgeleKelime;
import com.example.yabancidil_kelime_hazinesi.Reklam.ReklamGetir;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class TestBasla extends AppCompatActivity implements View.OnClickListener {
    LinearLayout btnA,btnB,btnC,btnD,btnKelime;
    TextView txtCevapA,txtCevapB,txtCevapC,txtCevapD,txtKelime,txtSoruSayisi,txtDogru,txtYanlis;
    public static List<Kelimeler> kelimelis;
    KelimelerDB db;
    RastgeleKelime rastgeleKelime;
    int sira;
    int soruSayi=0;
    int dogruSayi=0;
    int yanlisSayi=0;
    int randomsayi,randomsayiSakla;
    MediaPlayer ses1;
    Vibrator titresim;

    LinearLayout lineerReklamBanner8,lineerReklamBanner9;
    ReklamGetir reklamGetir;
    public static final String SHARED_PREFS="sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_basla);


        btnA=findViewById(R.id.btnA);
        btnB=findViewById(R.id.btnB);
        btnC=findViewById(R.id.btnC);
        btnD=findViewById(R.id.btnD);
        btnKelime=findViewById(R.id.btnTurkceKelime);
        btnA.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnKelime.setOnClickListener(this);

        txtCevapA=findViewById(R.id.txtA);
        txtCevapB=findViewById(R.id.txtB);
        txtCevapC=findViewById(R.id.txtC);
        txtCevapD=findViewById(R.id.txtD);
        txtKelime=findViewById(R.id.txtKelime);
        txtSoruSayisi=findViewById(R.id.TxtSoruSayisi);
        txtDogru=findViewById(R.id.TxtDogruSayisi);
        txtYanlis=findViewById(R.id.TxtYanlisSayisi);
        lineerReklamBanner8=findViewById(R.id.lineerReklamBanner8);
        lineerReklamBanner9=findViewById(R.id.lineerReklamBanner9);



        reklamGetir=new ReklamGetir();

        String reklamKodu1=getString(R.string.reklambanner8);
        reklamGetir.BannerReklam(lineerReklamBanner8,getApplicationContext(),reklamKodu1);

        String reklamKodu2=getString(R.string.reklambanner9);
        reklamGetir.BannerReklam(lineerReklamBanner9,getApplicationContext(),reklamKodu2);


        db=new KelimelerDB(getApplicationContext());
        kelimelis=new ArrayList<Kelimeler>();
        kelimelis=db.TumKayitlar();
        sira=kelimelis.size();
        if(sira-10<5){
            Toast.makeText(getApplicationContext(),"Test Etmek İçin Hazinenizde En Az 15 Kelimeniz olmalıdır.Mevcut Kelime Sayınız "+sira,Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        int ilkatama=(int)(Math.random()* sira-10);
        if(ilkatama<3)
        randomsayi = 3;
        else
        randomsayi = ilkatama;
        randomsayiSakla=randomsayi+9;

        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }


        titresim= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        ses1= MediaPlayer.create(getApplicationContext(),R.raw.ses);

        rastgeleKelime=new RastgeleKelime();
        rastgeleKelime.RastgeleUret(txtCevapA,txtCevapB,txtCevapC,txtCevapD,txtKelime,randomsayi,randomsayiSakla,2);

    }

    public void onClick(View v) {
    switch (v.getId()){


        @Override
    case R.id.btnA:
        if(RastgeleKelime.cevap==txtCevapA.getText()) {
            DogruCevap();

        }
        else {
            YanlisCevap();
        }
        break;

    case R.id.btnB:
        if(RastgeleKelime.cevap==txtCevapB.getText()) {

            DogruCevap();
        }
        else {
            YanlisCevap();
        }
        break;

        case R.id.btnC:
            if(RastgeleKelime.cevap==txtCevapC.getText()) {

                DogruCevap();
            }
            else {
                YanlisCevap();
            }
        break;


    case R.id.btnD:
        if(RastgeleKelime.cevap==txtCevapD.getText()) {

            DogruCevap();
        }
        else {
            YanlisCevap();
        }
        break;

       }
    }


    private void DogruCevap(){
        ses1.start();
        soruSayi++;
        txtSoruSayisi.setText("Soru : "+soruSayi+"/10");
dogruSayi++;
txtDogru.setText("Doğru : "+dogruSayi);
        SoruSonu();
        randomsayi++;
        rastgeleKelime.RastgeleUret(txtCevapA,txtCevapB,txtCevapC,txtCevapD,txtKelime,randomsayi,randomsayiSakla,2);

    }

    private void YanlisCevap(){
        titresim.vibrate(250);
        soruSayi++;
        txtSoruSayisi.setText("Soru : "+soruSayi+"/10");
        yanlisSayi++;
        txtYanlis.setText("Yanlış : "+yanlisSayi);
        SoruSonu();
        randomsayi++;
        rastgeleKelime.RastgeleUret(txtCevapA,txtCevapB,txtCevapC,txtCevapD,txtKelime,randomsayi,randomsayiSakla,2);

    }
    private void SoruSonu(){
        if(randomsayi==randomsayiSakla){
            btnA.setEnabled(false);
            btnB.setEnabled(false);
            btnC.setEnabled(false);
            btnD.setEnabled(false);
            SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit(); //SharedPreferences'a kayıt eklemek için editor oluşturuyoruz

           //String yy= sharedPref.getString("yazii","");
            //Toast.makeText(this, ""+yy, Toast.LENGTH_SHORT).show();
          //  editor.apply();

            SharedSaved(dogruSayi,yanlisSayi);

            Thread zamanlayici=new Thread(){

                public void run(){
                    try {

                        sleep(2000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent gecis=new Intent(getApplicationContext(),TestSonu.class);
                        String dSayi=String.valueOf(dogruSayi);
                        String ySayi=String.valueOf(yanlisSayi);
                        gecis.putExtra("send_Dogru",dSayi);
                        gecis.putExtra("send_Yanlis",ySayi);
                        startActivity(gecis);
                        finish();
                    }
                }

            };
            zamanlayici.start();


        }
    }
    public  void SharedSaved(int dSayi,int ySayi){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit(); //SharedPreferences'a kayıt eklemek için editor oluşturuyoruz

        int dS= sharedPref.getInt("DogruSayisi",0)+dSayi;
        int yS= sharedPref.getInt("YanlisSayisi",0)+ySayi;

        editor.putInt("DogruSayisi",dS); //int değer ekleniyor
        editor.putInt("YanlisSayisi",yS); //int değer ekleniyor
        editor.apply();
    }
}


