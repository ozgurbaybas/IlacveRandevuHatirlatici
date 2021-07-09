package com.example.zgr.ilachatirlatici;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;

import com.example.zgr.ilachatirlatici.Siniflar.Ilaclar;
import com.example.zgr.ilachatirlatici.Siniflar.ParseTime;
import com.example.zgr.ilachatirlatici.Siniflar.Randevular;
import com.example.zgr.ilachatirlatici.Siniflar.Uyeler;

import java.util.ArrayList;
import java.util.Date;

public class DatabaseConnector extends SQLiteOpenHelper {

    private static final String tableName = "UYELER";

    private static final  String uye_id = "uye_id";
    private static final String uye_name = "uye_name";
    private static final String uye_surname = "uye_surname";
    private static final String username = "uye_username";
    private static final String email = "uye_email";
    private static final String password = "uye_password";


    private static final String tableName2 = "ILACLAR";

    private static final String ilac_name = "ilac_name";
    private static final String ilac_id = "ilac_id";
    private static final String ilac_kullanim_nedeni = "ilac_kullanim_nedeni";
    private static final String ilaclar_uye_id = "ilaclar_uye_id";


    private static final String tableName3 = "ILAC_UYE";

    private static final String ilac_uye_id = "ilac_uye_id";
    private static final String baslama_tarihi = "baslama_tarihi";
    private static final String bitis_tarihi = "bitis_tarihi";
    private static final String dozaj = "dozaj";
    private static final String sabah_saat = "sabah_saat";
    private static final String ogle_saat = "ogle_saat";
    private static final String aksam_saat = "aksam_saat";
    private static final String sabah = "sabah";
    private static final String ogle = "ogle";
    private static final String aksam = "aksam";
    private static final String toplam_ilac_sayisi = "toplam_ilac_miktari";
    private static final String kalan_ilac_miktari = "kalan_ilac_miktari";
    private static final String gunluk_kullanim_miktari = "gunluk_kullanim_miktari";
    private static final String ilacuye_ilac_id = "ilacuye_ilac_id";
    private static final String ilacuye_uye_id = "ilacuye_uye_id";


    private static final String tableName4 = "DOKTORLAR";

    private static final String doktor_id = "doktor_id";
    private static final String doktor_name = "doktor_name";
    private static final String doktor_surname = "doktor_surname";
    private static final String doktor_brans = "doktor_brans";
    private static final String doktor_hastane = "doktor_hastane";
    private static final String doktorlar_uye_id = "doktorlar_uye_id";

    private static final String tableName5 = "RANDEVULAR";

    private static final String randevu_id = "randevu_id";
    private static final String randevu_tarihi = "randevu_tarih";
    private static final String randevu_saati = "randevu_saat";
    private static final String randevular_uye_id = "uye_id";
    private static final String randevular_doktor_id = "doktor_id";
    private static final String randevular_hastane = "randevular_hastane";
    private static final String randevular_brans = "randevular_brans";

    public DatabaseConnector(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        createTables(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + tableName + ";");
        db.execSQL("DROP TABLE IF EXISTS " + tableName2 + ";");
        db.execSQL("DROP TABLE IF EXISTS " + tableName3 + ";");
        db.execSQL("DROP TABLE IF EXISTS " + tableName4 + ";");
        db.execSQL("DROP TABLE IF EXISTS " + tableName5 + ";");

        onCreate(db);
    }

    //database tablolarının yazıldığı fonksiyondur.

    public void createTables(SQLiteDatabase db) {

        String createUYELERTable = "CREATE TABLE " + tableName + " (" + uye_id +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + uye_name + " TEXT, " +
                uye_surname + " TEXT, " + username + " TEXT, " + email + " TEXT, " + password + " TEXT);";

        db.execSQL(createUYELERTable);

        String createILACLARTable = "CREATE TABLE " + tableName2 + " (" + ilac_id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                ilac_name + " TEXT, " + ilac_kullanim_nedeni + " TEXT, " + ilaclar_uye_id + " INTEGER);";

        db.execSQL(createILACLARTable);

        String createDOKTORLARTable = "CREATE TABLE " + tableName4 + " (" + doktor_id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                doktor_name + " TEXT, " + doktor_surname + " TEXT, " + doktor_brans + " TEXT, " + doktor_hastane + " TEXT, " + doktorlar_uye_id + " INTEGER);";


        db.execSQL(createDOKTORLARTable);


        String createILAC_UYETable = "CREATE TABLE " + tableName3 + " (" + ilac_uye_id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                baslama_tarihi + " TEXT, " + bitis_tarihi + " TEXT, " + dozaj + " INTEGER, " + gunluk_kullanim_miktari + " INTEGER, " +
                toplam_ilac_sayisi + " INTEGER, " + kalan_ilac_miktari + " INTEGER, " + sabah_saat + " TEXT, " +
                ogle_saat + " TEXT, " + aksam_saat + " TEXT, " + sabah + " TEXT, " + ogle + " TEXT, " + aksam + " TEXT, " + ilac_name + " TEXT, " + ilac_kullanim_nedeni + " TEXT, " + ilacuye_ilac_id + " INTEGER, " + ilacuye_uye_id + " INTEGER);";


        db.execSQL(createILAC_UYETable);

        String createRANDEVULARTable = "CREATE TABLE " + tableName5 + " (" + randevu_id +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + randevu_saati + " TEXT, " +
                randevu_tarihi + " TEXT, " +  randevular_hastane + " TEXT, "+ randevular_brans + " TEXT, " + randevular_uye_id + " INTEGER, " + randevular_doktor_id + " INTEGER);";
        db.execSQL(createRANDEVULARTable);
    }


    // add... fonksiyonları database tablolarına veri girilmesini sağlayan fonksiyonlardır.

    public void addRecordtoUyeler(String _name, String _surname, String _username, String _email, String _password) {

        String insertSQL = "INSERT INTO " + tableName + " (" + uye_name + ", " + uye_surname + " ," + username + " ," + email + " ," + password + ") "

                + "VALUES" + " ('" + _name + "', '" + _surname + "', '" + _username + "', '" + _email + "', '" + _password + "')";


        SQLiteDatabase dataBase = this.getWritableDatabase();

        dataBase.execSQL(insertSQL);

        dataBase.close();

    }
    public void addRecordtoIlaclar ( String _ilac_name, String _ilac_kullanim_nedeni , int _uye_id) {


        String insertSQL = "INSERT INTO " + tableName2 + " (" + ilac_name + ", " + ilac_kullanim_nedeni + ", " + ilaclar_uye_id + ") "

                + "VALUES" + " ('" + _ilac_name + "', '" + _ilac_kullanim_nedeni + "', '" + _uye_id + "')";

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(insertSQL);
        database.close();

    }

    public void addRecordtoIlacUye(Date _baslama_tarihi, double _dozaj, int _gunluk_kullanim_miktari, Time _sabah_saat, Time _ogle_saat,
                                   Time _aksam_saat, boolean _sabah, boolean _ogle, boolean _aksam, int _toplam_ilac_sayisi, String _ilac_name, String _ilac_kullanim_nedeni ,int _ilac_id, int _uye_id) {

        String insertSQL = "INSERT INTO " + tableName3 + " (" + baslama_tarihi +  ", " + dozaj + ", " + gunluk_kullanim_miktari + ", " + toplam_ilac_sayisi + ", " + sabah_saat + ", " + ogle_saat + ", "  + aksam_saat + ", "
                + sabah + ", " + ogle + ", " + aksam + ", "  + ilac_name + ", " + ilac_kullanim_nedeni + ", " + ilacuye_ilac_id  + ", " + ilacuye_uye_id + ") "

                + "VALUES" + " ('" + _baslama_tarihi.getTime() + "', '" + _dozaj + "', '" + _gunluk_kullanim_miktari + "', '" + _toplam_ilac_sayisi + "', '" + (_sabah_saat == null ? "" : _sabah_saat.format2445()) + "', '" + (_ogle_saat == null ? "" : _ogle_saat.format2445()) + "', '" + (_aksam_saat == null ? "" : _aksam_saat.format2445()) +

                "', '" + _sabah + "', '" + _ogle + "', '" + _aksam + "', '" + _ilac_name + "', '" + _ilac_kullanim_nedeni + "', '"+ _ilac_id + "', '" + _uye_id + "')";

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(insertSQL);
        database.close();
    }

    public void addRecordtoDoktor(String _doktor_name, String _doktor_surname, String _doktor_brans, String _doktor_hastane, int _uye_id) {

        String insertSQL = "INSERT INTO " + tableName4 + " (" + doktor_name + ", " + doktor_surname + ", " + doktor_hastane + ", " + doktor_brans + ", " + doktorlar_uye_id + ") "

                + "VALUES" + " ('" + _doktor_name + "', '" + _doktor_surname + "', '" + _doktor_hastane + "', '" + _doktor_brans + "', '" + _uye_id + "')";

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(insertSQL);
        database.close();
    }

    public void addRecordtoRandevular(Time _muayene_saati, Date _muayene_tarihi,int _uye_id,int _doktor_id, String _hastane_randevu, String _randevular_brans)
    {
        String insertSQL = "INSERT INTO " + tableName5 + " (" + randevu_saati + ", " + randevu_tarihi + ", " + randevular_hastane + ", " + randevular_brans + ", " + randevular_uye_id + ", " + randevular_doktor_id + ") "

                + "VALUES" + " ('" + _muayene_saati.toMillis(false) + "', '" + _muayene_tarihi.getTime() + "', '" +_hastane_randevu + "', '" +_randevular_brans + "', '" +  _uye_id + "', '" + _doktor_id +  "')";

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(insertSQL);
        database.close();
    }



    //uye adı ve soyadı ile database'den veri getiren fonksiyondur.

    public Uyeler getRecord(String _username, String _password) {



        Uyeler uye = new Uyeler();


        SQLiteDatabase database = this.getReadableDatabase();

        String getSQL = "SELECT * FROM " + tableName + " WHERE " + username + " = '" + _username + "' AND " + password + " = '" + _password + "'";

        Cursor cursor = database.rawQuery(getSQL, null);

        cursor.moveToFirst();

        int u_id = cursor.getInt(0);
        String u_name = cursor.getString(1);
        String u_surname = cursor.getString(2);
        String u_username = cursor.getString(3);
        String u_email = cursor.getString(4);
        String u_password = cursor.getString(5);

        uye.setUye_id(u_id);
        uye.setUye_name(u_name);
        uye.setUye_surname(u_surname);
        uye.setUsername(u_username);
        uye.setEmail(u_email);
        uye.setPassword(u_password);

        database.close();

        return uye;
    }

    // uye id ile databaseden veri getiren fonksiyondur.

    public Uyeler getRecordWithUyeID(int _id)
    {

        Uyeler uye = new Uyeler();

        SQLiteDatabase database = this.getReadableDatabase();

        String getSQL = "SELECT * FROM " + tableName + " WHERE " + uye_id + " = '" + _id + "'";
        Cursor cursor = database.rawQuery(getSQL,null);
        cursor.moveToFirst();

        int u_id = cursor.getInt(0);
        String u_name = cursor.getString(1);
        String u_surname = cursor.getString(2);
        String u_username = cursor.getString(3);
        String u_email = cursor.getString(4);
        String u_password = cursor.getString(5);

        uye.setUye_id(u_id);
        uye.setUye_name(u_name);
        uye.setUye_surname(u_surname);
        uye.setUsername(u_username);
        uye.setEmail(u_email);
        uye.setPassword(u_password);

        database.close();

        return uye;
    }

    //uye adı ve soyadı ile sisteme giriş yapabilip yapamayacağımı kontrol eden fonksiyondur.

    public boolean login(String _username, String _password) {

        String getSQL = "SELECT * FROM " + tableName + " where " + username + " = '" + _username + "' and " + password + " = '" + _password + "'";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(getSQL,null);

        if (cursor.moveToNext()) {

            return true;

        } else {

            return false;
        }
    }

    //Database'den ilaç id'lerin getirildiği fonksiyondur.

    public int getIlacID()
    {
        String getSQL = "SELECT " + ilac_id + " FROM " + tableName2 + " ORDER BY " + ilac_id + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(getSQL,new String[]{});

        if(cursor.moveToFirst()) {

            return cursor.getInt(0);

        }
        return -1;
    }

    //Database'den doktor id'lerin getirildiği fonksiyondur.

    public int getDoktorID()
    {
        String getSQL = "SELECT " + doktor_id + " FROM " + tableName4 + " ORDER BY " + doktor_id + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(getSQL,new String[]{});

        if(cursor.moveToFirst()) {

            return cursor.getInt(0);

        }
        return -1;
    }

    //Database'e kayıtlı tüm ilaçların getirildiği fonksiyondur.

    public ArrayList<Ilaclar> getAllIlaclar(int _uye_id) {

        ArrayList<Ilaclar> ilaclar = new ArrayList<>();

        String getSQL = "SELECT * FROM " + tableName3 + " WHERE " + ilacuye_uye_id + " = '" + _uye_id + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(getSQL, null);

        Ilaclar ilac= null;

        if (cur.moveToFirst()) {
            do {
                ilac = new Ilaclar();

                ilac.setIlac_name(cur.getString(13));
                ilac.setIlac_kullanma_nedeni(cur.getString(14));
                ilac.setIlac_baslama_tarihi(new Date(Long.valueOf(cur.getString(1))));
                ilac.setIlac_dozaj(cur.getFloat(3));
                ilac.setGunlukKullanimMiktari(cur.getInt(4));
                ilac.setToplam_ilac_sayisi(cur.getInt(5));

              /*  ilac.setSabah_saat(ParseTime.fromLong(Long.valueOf(cur.getString(7))));
                ilac.setOgle_saat(ParseTime.fromLong(Long.valueOf(cur.getString(8))));
                ilac.setAkşam_saat(ParseTime.fromLong(Long.valueOf(cur.getString(9))));*/

                ilac.setSabah_saat(new Time(cur.getString(7)));
                ilac.setOgle_saat(new Time(cur.getString(8)));
                ilac.setAkşam_saat(new Time(cur.getString(9)));



                if ((cur.getString(10) == "TRUE") || (cur.getString(10) == "true")) {
                    ilac.setSabah(true);
                } else {
                    ilac.setSabah(false);
                }

                if ((cur.getString(11) == "TRUE") || (cur.getString(11) == "true")) {
                    ilac.setOgle(true);
                } else {
                    ilac.setOgle(false);
                }

                if ((cur.getString(12) == "TRUE") || (cur.getString(12) == "true")) {
                    ilac.setAksam(true);
                } else {
                    ilac.setAksam(false);
                }

                ilaclar.add(ilac);

            } while (cur.moveToNext());

        }
        return ilaclar;
    }

    //Database'e kayıtlı tüm randevuların getirildiği fonksiyondur.

    public ArrayList<Randevular> getAllRandevular (int _uye_id){


        ArrayList <Randevular> randevular = new ArrayList<>();

        String getSQL = "SELECT * FROM " + tableName5 + " WHERE " + randevular_uye_id + " = '" + _uye_id + "'";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(getSQL,null);

        Randevular randevu = null;


        if(cur.moveToFirst())
        {
            do{
                randevu = new Randevular();

                randevu.setRandevu_id(cur.getInt(0));
                randevu.setRandevu_muayene_saati(ParseTime.fromLong(Long.valueOf(cur.getString(1))));
                randevu.setRandevu_muayene_tarihi(new Date(Long.valueOf(cur.getString(2))));
                randevu.setRandevu_hastane(cur.getString(3));
                randevu.setRandevu_brans(cur.getString(4));
                randevu.setRandevu_uye_id(cur.getInt(5));
                randevu.setRandevu_doktor_id(cur.getInt(6));

                randevular.add(randevu);

            }while (cur.moveToNext());
        }
        return randevular;
    }
}

