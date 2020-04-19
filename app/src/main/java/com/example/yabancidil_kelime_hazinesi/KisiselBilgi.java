package com.example.yabancidil_kelime_hazinesi;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yabancidil_kelime_hazinesi.Model.KelimelerDB;
import com.example.yabancidil_kelime_hazinesi.Reklam.ReklamGetir;

public class KisiselBilgi extends AppCompatActivity {
    TextView txtCozulenSoru,txtDogru,txtYanlis,txtSoruSayisi,txtUygulamayaGirisSayisi;
    LinearLayout lineerReklamBanner4,lineerReklamBanner5;
    public static final String SHARED_PREFS="sharedPrefs";

    ReklamGetir reklamGetir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisisel_bilgi);

        txtCozulenSoru=findViewById(R.id.txtCozulenSoru);
        txtDogru=findViewById(R.id.txtDogru);
        txtYanlis=findViewById(R.id.txtYanlis);
        txtSoruSayisi=findViewById(R.id.txtSoruSayisi);
        txtUygulamayaGirisSayisi=findViewById(R.id.txtUygulamayaGirisSayisi);
        lineerReklamBanner4=findViewById(R.id.lineerReklamBanner4);
        lineerReklamBanner5=findViewById(R.id.lineerReklamBanner5);


        reklamGetir=new ReklamGetir();

        String reklamKodu1=getString(R.string.reklambanner4);
        reklamGetir.BannerReklam(lineerReklamBanner4,getApplicationContext(),reklamKodu1);

        String reklamKodu2=getString(R.string.reklambanner5);
        reklamGetir.BannerReklam(lineerReklamBanner5,getApplicationContext(),reklamKodu2);

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit(); //SharedPreferences'a kayıt eklemek için editor oluşturuyoruz

        int dS= sharedPref.getInt("DogruSayisi",0);
        int yS= sharedPref.getInt("YanlisSayisi",0);


        txtDogru.setText(""+dS);
        txtYanlis.setText(""+yS);
        txtCozulenSoru.setText(""+(yS+dS));

        KelimelerDB kelimelerDB=new KelimelerDB(getApplicationContext());
        int kelimeSayisi= kelimelerDB.TumKayitlar().size();
        txtSoruSayisi.setText(""+kelimeSayisi);

        int gS= sharedPref.getInt("OyunaGiris",0);
        txtUygulamayaGirisSayisi.setText(""+gS);


        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }
}
