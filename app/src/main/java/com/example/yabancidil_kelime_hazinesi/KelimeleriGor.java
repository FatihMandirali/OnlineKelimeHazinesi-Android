package com.example.yabancidil_kelime_hazinesi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.yabancidil_kelime_hazinesi.Model.Kelimeler;
import com.example.yabancidil_kelime_hazinesi.Model.KelimelerDB;
import com.example.yabancidil_kelime_hazinesi.Reklam.ReklamGetir;

import java.util.ArrayList;
import java.util.List;

public class KelimeleriGor extends AppCompatActivity {
    TableLayout tablo;
    public static List<Kelimeler> kelimelis;
    ImageView tv_tikla;
    Button tv_Anlami;
    String gelenanlam;
    String hangidil=null;
    ActionMode actionMode;
    long idisi;

    ReklamGetir reklamGetir;
    LinearLayout lineerReklamBanner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelimeleri_gor);
        tablo= (TableLayout) findViewById(R.id.tablo);
        lineerReklamBanner3=findViewById(R.id.lineerReklamBanner3);

        reklamGetir=new ReklamGetir();

        String reklamKodu1=getString(R.string.reklambanner3);
        reklamGetir.BannerReklam(lineerReklamBanner3,getApplicationContext(),reklamKodu1);

        try {
            gel();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Kayıt Bulunamadı..",Toast.LENGTH_LONG).show();
        }

        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
    }


    public void gel(){
        tablo.removeAllViews();
        KelimelerDB db=new KelimelerDB(getApplicationContext());
        List<Kelimeler> kelimelis=new ArrayList<Kelimeler>();
        kelimelis=db.TumKayitlar();

        final TableRow satir1=new TableRow(KelimeleriGor.this);
        satir1.setGravity(Gravity.CENTER);


        for(final Kelimeler kelime:kelimelis){
            final TableRow satir=new TableRow(KelimeleriGor.this);
            satir.setGravity(Gravity.CENTER);




            tv_tikla=new ImageView(KelimeleriGor.this);
            tv_tikla.setImageResource(R.drawable.uzuntik);
            tv_tikla.setPadding(2,2,2,2);

          //  Button tv_Dil=new Button(KelimeleriGor.this);
          //  tv_Dil.setTextColor(Color.BLACK);
          //  tv_Dil.setPadding(2,2,2,2);
          //  tv_Dil.setText(kelime.getDil());


            Button tv_Kelime=new Button(KelimeleriGor.this);
            tv_Kelime.setBackgroundResource(R.drawable.button_mavi);
            tv_Kelime.setPadding(2,2,2,2);
            tv_Kelime.setTextColor(Color.BLACK);
            tv_Kelime.setTextColor(Color.WHITE);
            tv_Kelime.setText(kelime.getKelime());



            tv_Anlami=new Button(KelimeleriGor.this);
            tv_Anlami.setBackgroundResource(R.drawable.edittext_background);
            tv_Anlami.setPadding(2,2,2,2);
            tv_Anlami.setTextColor(Color.BLACK);
           // tv_Anlami.setTextColor(Color.WHITE);
            tv_Anlami.setText(kelime.getAnlami());

            satir.addView(tv_tikla);
           // satir.addView(tv_Dil);
            satir.addView(tv_Kelime);
            satir.addView(tv_Anlami);
            tablo.addView(satir);

            tv_tikla.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    idisi=kelime.getId();
                    //  gelendil=kelime.getDil();
                    gelenanlam=tv_Anlami.getText().toString();
                    //  gelenkelime=kelime.getKelime();
                    // satir.setBackgroundColor(Color.LTGRAY);

                    if(actionMode!=null){
                        return  false;
                    }
                    MyActionModeCallback callback=new MyActionModeCallback();
                    actionMode=startActionMode(callback);
                    v.setSelected(true);
                    return true;

                }
            });



        }

    }

    class MyActionModeCallback implements ActionMode.Callback{


        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.context_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {


            return false;
            //action mode başlatılmadan önce yapılacak işlemler burda tanımlanır.
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getItemId()==R.id.sil)
            {
                KelimelerDB db=new KelimelerDB(getApplicationContext());
                db.Sil(idisi);
                gel();
                mode.finish();
            }/*else if(item.getItemId()==R.id.guncelle)

            {
            //    showDialog(sayi);
                mode.finish();
            }*/

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode=null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gosterilecek_dili_sec,menu);
        return true;
    }
    //--------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.GenelBilgiler){
            Intent i=new Intent(getApplicationContext(),KisiselBilgi.class);
            startActivity(i);
            return  true;
        }else if(item.getItemId()==R.id.KelimeEkle){
            Intent i=new Intent(getApplicationContext(),KelimeEkle.class);
            startActivity(i);
            return true;
        }else if(item.getItemId()==R.id.Anasayfa){
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
