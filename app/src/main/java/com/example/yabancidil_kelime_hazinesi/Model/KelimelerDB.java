package com.example.yabancidil_kelime_hazinesi.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class KelimelerDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kelimeler_veritabani";
    private static final String TABLE_NAME = "kelimeler_tablosu";
    private static final int DATABASE_VERSION = 1;

    private static final String Dil = "dil";
    private static final String Kelime = "kelime";
    private static final String Anlami = "anlami";
    private static final String ID = "id";

    public KelimelerDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablo_olustur = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Dil + " TEXT, " + Kelime + " TEXT, " + Anlami + " TEXT)";


        db.execSQL(tablo_olustur);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long KayitEkle(Kelimeler kelimeler) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Dil,kelimeler.getDil());
        cv.put(Kelime,kelimeler.getKelime());
        cv.put(Anlami,kelimeler.getAnlami());

        long id= db.insert(TABLE_NAME,null,cv);

        db.close();
        return id;
    }

    public List<Kelimeler> TumKayitlar() {
        SQLiteDatabase db=this.getReadableDatabase();
        String []sutunlar=new String[]{Dil,Kelime,Anlami,ID};
        Cursor c1=db.query(TABLE_NAME,sutunlar,null,null,null,null,ID+" desc");
        Cursor c=db.query(TABLE_NAME,sutunlar,null,null,null,null,ID+" desc");


        int dilsirano=c.getColumnIndex(Dil);
        int kelimesirano=c.getColumnIndex(Kelime);
        int anlamisirano=c.getColumnIndex(Anlami);
        int idsirano=c.getColumnIndex(ID);
        List<Kelimeler> kelimelis=new ArrayList<Kelimeler>();
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            Kelimeler kelime=new Kelimeler();
            kelime.setDil(c.getString(dilsirano));
            kelime.setKelime(c.getString(kelimesirano));
            kelime.setAnlami(c.getString(anlamisirano));
            kelime.setId(c.getLong(idsirano));
            kelimelis.add(kelime);
        }
        db.close();
        return kelimelis;
    }


    public void Sil(long id) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,ID+"="+id,null);
        db.close();
    }

    public  void Guncelle(long id,String kelime,String anlam){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Kelime,kelime);
        cv.put(Anlami,anlam);
        db.update(TABLE_NAME,cv,ID+"="+id,null);
        db.close();

    }
    public List<Kelimeler> TumKayitlar(String hangidil) {
        SQLiteDatabase db=this.getReadableDatabase();
        String []sutunlar=new String[]{Dil,Kelime,Anlami,ID};
        Cursor c1=db.query(TABLE_NAME,sutunlar,null,null,null,null,ID+" desc");
        Cursor c=db.query(TABLE_NAME,sutunlar,Dil+" = '"+hangidil+"'",null,null,null,ID+" desc");

        if(hangidil!=null) {
            int dilsirano = c.getColumnIndex(Dil);
            int kelimesirano = c.getColumnIndex(Kelime);
            int anlamisirano = c.getColumnIndex(Anlami);
            int idsirano = c.getColumnIndex(ID);
            List<Kelimeler> kelimelis = new ArrayList<Kelimeler>();
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                Kelimeler kelime = new Kelimeler();
                kelime.setDil(c.getString(dilsirano));
                kelime.setKelime(c.getString(kelimesirano));
                kelime.setAnlami(c.getString(anlamisirano));
                kelime.setId(c.getLong(idsirano));
                kelimelis.add(kelime);

            }

            db.close();
            return kelimelis;
        }else{
            int dilsirano = c1.getColumnIndex(Dil);
            int kelimesirano = c1.getColumnIndex(Kelime);
            int anlamisirano = c1.getColumnIndex(Anlami);
            int idsirano = c1.getColumnIndex(ID);
            List<Kelimeler> kelimelis = new ArrayList<Kelimeler>();
            for (c1.moveToFirst(); !c1.isAfterLast(); c1.moveToNext()) {
                Kelimeler kelime = new Kelimeler();
                kelime.setDil(c1.getString(dilsirano));
                kelime.setKelime(c1.getString(kelimesirano));
                kelime.setAnlami(c1.getString(anlamisirano));
                kelime.setId(c1.getLong(idsirano));
                kelimelis.add(kelime);

            }
            db.close();
            return kelimelis;
        }


    }
}
