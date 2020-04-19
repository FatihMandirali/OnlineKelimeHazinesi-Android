package com.example.yabancidil_kelime_hazinesi.Model;

import android.widget.TextView;

import com.example.yabancidil_kelime_hazinesi.TestBasla;
import com.example.yabancidil_kelime_hazinesi.TestEtIngilizce;

public class RastgeleKelime {

    public static String cevap;
    public void RastgeleUret(TextView txtA,TextView txtB,TextView txtC,TextView txtD,TextView txtKelime,int randomsayi,int sonsayi,int oyunTur) {

        int sayi1, sayi2, sayi3;

        //sayi1 -2 , sayi2 -1  sayi3 +2
        if (randomsayi < 3) {
            sayi1 = 1;
            sayi2 = 2;
        } else {
            sayi1 = randomsayi - 2;
            sayi2 = randomsayi - 1;
        }
        if (randomsayi + 2 > sonsayi) {
            sayi3 = 3;
        } else {
            sayi3 = randomsayi + 2;
        }
        for (; ; ) {
            int[] dizi = {randomsayi, sayi1, sayi2, sayi3};
            int rastgelekelime1 = (int) (Math.random() * (dizi.length));
            int rastgelekelime2 = (int) (Math.random() * (dizi.length));
            int rastgelekelime3 = (int) (Math.random() * (dizi.length));
            int rastgelekelime4 = (int) (Math.random() * (dizi.length));
            if (rastgelekelime1 != rastgelekelime2 && rastgelekelime1 != rastgelekelime3 && rastgelekelime1 != rastgelekelime4 && rastgelekelime2 != rastgelekelime3 && rastgelekelime2 != rastgelekelime4 && rastgelekelime3 != rastgelekelime4) {

               if(oyunTur==1){
                   txtA.setText(TestEtIngilizce.kelimelis.get(dizi[rastgelekelime1]).getKelime());
                   txtB.setText(TestEtIngilizce.kelimelis.get(dizi[rastgelekelime2]).getKelime());
                   txtC.setText(TestEtIngilizce.kelimelis.get(dizi[rastgelekelime3]).getKelime());
                   txtD.setText(TestEtIngilizce.kelimelis.get(dizi[rastgelekelime4]).getKelime());
                   txtKelime.setText(TestEtIngilizce.kelimelis.get(randomsayi).getAnlami());
                   cevap = TestEtIngilizce.kelimelis.get(randomsayi).getKelime();
               }else{
                   txtA.setText(TestBasla.kelimelis.get(dizi[rastgelekelime1]).getAnlami());
                   txtB.setText(TestBasla.kelimelis.get(dizi[rastgelekelime2]).getAnlami());
                   txtC.setText(TestBasla.kelimelis.get(dizi[rastgelekelime3]).getAnlami());
                   txtD.setText(TestBasla.kelimelis.get(dizi[rastgelekelime4]).getAnlami());
                   txtKelime.setText(TestBasla.kelimelis.get(randomsayi).getKelime());
                   cevap = TestBasla.kelimelis.get(randomsayi).getAnlami();
               }

                break;
            }else
                {
                rastgelekelime1 = (int) (Math.random() * (dizi.length));
                rastgelekelime2 = (int) (Math.random() * (dizi.length));
                rastgelekelime3 = (int) (Math.random() * (dizi.length));
                rastgelekelime4 = (int) (Math.random() * (dizi.length));
            }
        }
    }


}
