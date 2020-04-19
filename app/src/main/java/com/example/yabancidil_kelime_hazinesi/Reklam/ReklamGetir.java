package com.example.yabancidil_kelime_hazinesi.Reklam;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class ReklamGetir extends AppCompatActivity {
    private AdView adView;
    private InterstitialAd gecisreklami;
    private AdRequest adRequest;

    public  void BannerReklam(LinearLayout layout, Context context, String reklamKodu){
        adView=new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(reklamKodu);
        layout.addView(adView);
      //  AdRequest adRequest=new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public void GecisReklami(Context context){
        adRequest=new AdRequest.Builder().build();
        gecisreklami=new InterstitialAd(context);

       //  gecisreklami.setAdUnitId("ca-app-pub-3940256099942544/1033173712");  google deneme reklam geçiş
        gecisreklami.setAdUnitId("ca-app-pub-8449130834114994/2851501880");

        // ca-app-pub-8449130834114994/2851501880     geçiş reklam
        gecisreklami.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                LoadGecisReklami();
            }

            //ca-app-pub-8449130834114994~6323950832   uygulama kimliği

            @Override
            public void onAdLoaded() {
                LoadGecisReklami();
            }
        });
        LoadGecisReklami();
    }
    public  void  LoadGecisReklami(){

        gecisreklami.loadAd(adRequest);
    }
    public  void ShowGecisReklami(){
        if(gecisreklami.isLoaded()){
            gecisreklami.show();
        }
    }
}

