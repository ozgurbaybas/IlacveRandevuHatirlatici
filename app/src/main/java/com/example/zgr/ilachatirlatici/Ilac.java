package com.example.zgr.ilachatirlatici;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zgr.ilachatirlatici.Siniflar.Ilaclar;
import com.example.zgr.ilachatirlatici.Siniflar.ParseTime;
import com.example.zgr.ilachatirlatici.Siniflar.Uyeler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Ilac extends AppCompatActivity implements View.OnClickListener {

    // kullanacağım bütün elemanları tanımladım.

    public static Ilaclar ilac;

    DatabaseConnector databaseConnector;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText baslamaTarihiET;
    private EditText ilacAdiET;
    private EditText kullanimNedeniET;
    private EditText gunlukKullanimMiktariET;
    private EditText toplamilaçET;
    private EditText sabahSaatET;
    private EditText ogleSaatET;
    private EditText aksamSaatET;
    private CheckBox sabahCB;
    private CheckBox ogleCB;
    private CheckBox aksamCB;
    private Button kaydetButton;
    private Button geriButton;
    private RadioButton yarimRB;
    private RadioButton butunRB;
    private RadioButton ikiRB;

    Calendar calendar = Calendar.getInstance();

    //init fonksiyonu elemanların initialize edilmesi için yazdığım fonksiyondur.

    public void init() {

        baslamaTarihiET = (EditText) findViewById(R.id.baslamaTarihiET);
        baslamaTarihiET.setInputType(InputType.TYPE_NULL);
        baslamaTarihiET.requestFocus();

        ilacAdiET = (EditText) findViewById(R.id.ilacAdiET);
        kullanimNedeniET = (EditText) findViewById(R.id.kullanimNedeniET);
        gunlukKullanimMiktariET = (EditText) findViewById(R.id.gunlukKullanimMiktariET);
        toplamilaçET = (EditText) findViewById(R.id.toplamilaçET);
        sabahSaatET = (EditText) findViewById(R.id.sabahSaatET);
        ogleSaatET = (EditText) findViewById(R.id.ogleSaatET);
        aksamSaatET = (EditText) findViewById(R.id.aksamSaatET);
        sabahCB = (CheckBox) findViewById(R.id.sabahCB);
        ogleCB = (CheckBox) findViewById(R.id.ogleCB);
        aksamCB = (CheckBox) findViewById(R.id.aksamCB);
        kaydetButton = (Button) findViewById(R.id.kaydetButton);
        geriButton = (Button) findViewById(R.id.geriButton);
        yarimRB = (RadioButton) findViewById(R.id.yarimRB);
        butunRB = (RadioButton) findViewById(R.id.butunRB);
        ikiRB = (RadioButton) findViewById(R.id.ikiRB);

        ilac = new Ilaclar();


        databaseConnector = new DatabaseConnector(this, "ilacAppDB", null, 1);


    }

    //sabah öğle akşam checkboxlarının işaretlenip işaretlenmediğini belirler.

    public boolean sabahIsChecked() {
        if (sabahCB.isChecked())
            return true;
        else
            return false;
    }

    public boolean ogleIsChecked() {
        if (ogleCB.isChecked())
            return true;
        else
            return false;

    }

    public boolean aksamIsChecked() {
        if (aksamCB.isChecked())
            return true;
        else
            return false;
    }

    //ilacın yarım tam veya iki tane içileceğini gösterir.

    public float RBChecked() {
        if (yarimRB.isChecked()) {
            return (float) 0.5;
        } else if (butunRB.isChecked()) {
            return 1;
        } else if (ikiRB.isChecked()) {
            return 2;
        } else
            return 0;
    }



    private void gettingIlacInfo() {


        try {


            //sabah öğle ve akşam textview'larına tıklandığında time picker çıkmasını sağlar.

            sabahSaatET.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    setTime(sabahSaatET, ilac);

                }

            });
            ogleSaatET.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTime(ogleSaatET, ilac);

                }
            });
            aksamSaatET.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTime(aksamSaatET, ilac);


                }
            });




            final Uyeler uye = Kullanici.uye;


            kaydetButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    //ilac bilgileri alınır.

                    ilac.setIlac_name(ilacAdiET.getEditableText().toString());
                    ilac.setIlac_kullanma_nedeni(kullanimNedeniET.getEditableText().toString());
                    ilac.setGunlukKullanimMiktari(Integer.parseInt(gunlukKullanimMiktariET.getEditableText().toString()));
                    ilac.setToplam_ilac_sayisi(Integer.parseInt(toplamilaçET.getEditableText().toString()));
                    ilac.setSabah(sabahIsChecked());
                    ilac.setOgle(ogleIsChecked());
                    ilac.setAksam(aksamIsChecked());
                    ilac.setIlac_dozaj(RBChecked());

                    try {


                        Date tarih = new SimpleDateFormat("dd-MM-yyyy").parse(baslamaTarihiET.getEditableText().toString());
                        ilac.setIlac_baslama_tarihi(tarih);


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }




                    //ilac objesi database'e eklenir.

                    databaseConnector.addRecordtoIlaclar(ilac.getIlac_name(), ilac.getIlac_kullanma_nedeni(), uye.getUye_id());


                    databaseConnector.addRecordtoIlacUye(ilac.getIlac_baslama_tarihi(), ilac.getIlac_dozaj(), ilac.getGunlukKullanimMiktari(), ilac.getSabah_saat(),
                            ilac.getOgle_saat(), ilac.getAkşam_saat(), ilac.isSabah(), ilac.isOgle(), ilac.isAksam(), ilac.getToplam_ilac_sayisi(),
                            ilac.getIlac_name(), ilac.getIlac_kullanma_nedeni(), databaseConnector.getIlacID(), uye.getUye_id());






                    //ilaç eklediğimde etkinlik sayfasının açılıp etkinlik olarak eklenerek alarm çalmasını sağlar.

                    if(sabahIsChecked())
                    {
                        takvimeKayitEkleme(ilac);
                    }
                    if(ogleIsChecked()){
                        takvimeKayitEkleme(ilac);
                    }
                    if(aksamIsChecked()){
                        takvimeKayitEkleme(ilac);
                    }




                    //kaydın başarı ile eklendiği bilgisi ekranda gösterilir.

                    final AlertDialog.Builder builder = new AlertDialog.Builder(Ilac.this);
                    builder.setMessage("Kaydınız başarı ile gerçekleşmiştir.")
                            .setTitle("Tebrikler");
                    builder.setNeutralButton("Tamam", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();

                    dialog.show();

                }
            });
        } catch (Exception e) {
            Log.e("zgr", "catche girdi");

        }
    }



    //Time picker'ın çalışması ve saatin alınması sağlanır.

    private void setTime(final TextView textView, final Ilaclar ilac) {
        Time t = ParseTime.converter(textView.getText().toString());
        if (t == null) {
            Date d = new Date();

            t = new Time();
            t.hour = d.getHours();
            t.minute = d.getMinutes();
        }


        TimePickerDialog dialog = new TimePickerDialog(com.example.zgr.ilachatirlatici.Ilac.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                textView.setText(String.format("%2d:%2d", hourOfDay, minute));

                Time t =new Time();
                t.hour = hourOfDay;
                t.minute = minute;

                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                calendar.set(Calendar.SECOND,0);


                if (textView == sabahSaatET)
                    ilac.setSabah_saat(t);



                if (textView == ogleSaatET)
                    ilac.setOgle_saat(t);


                if (textView == aksamSaatET)
                    ilac.setAkşam_saat(t);

            }
        }, t.hour, t.minute, true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                textView.setText("");
                if (textView == sabahSaatET)
                    ilac.setSabah_saat(null);
                if (textView == ogleSaatET)
                    ilac.setOgle_saat(null);
                if (textView == aksamSaatET)
                    ilac.setAkşam_saat(null);
            }
        });
        dialog.show();
    }

    //Date pickerin çalışmasını girilen tarihin ekranda gösterilmesini sağlar.

    private void setDateTimeField() {
        baslamaTarihiET.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                baslamaTarihiET.setText(dateFormatter.format(newDate.getTime()));
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    //tarihEdittextinin üzerine basınca tarih picker'ını çıkartır.
    @Override
    public void onClick(View view) {
        if (view == baslamaTarihiET) {
            fromDatePickerDialog.show();
        }

    }


    //geri butonunun çalışmasını sağlar.

    public void geriFunc() {
        geriButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                finish();
            }
        });
    }

    //Ekranda sabah, öğle ve akşam textview'larının ekranda görülüp görülmemesini ayarlar.

    public void visibility() {
        sabahCB.setOnClickListener(new CheckBox.OnClickListener() {
            public void onClick(View v) {
                if (sabahCB.isChecked()) {
                    sabahSaatET.setVisibility(View.VISIBLE);
                } else
                    sabahSaatET.setVisibility(View.INVISIBLE);

            }
        });
        ogleCB.setOnClickListener(new CheckBox.OnClickListener() {
            public void onClick(View v) {
                if (ogleCB.isChecked()) {
                    ogleSaatET.setVisibility(View.VISIBLE);
                } else
                    ogleSaatET.setVisibility(View.INVISIBLE);

            }
        });
        aksamCB.setOnClickListener(new CheckBox.OnClickListener() {
            public void onClick(View v) {
                if (aksamCB.isChecked()) {
                    aksamSaatET.setVisibility(View.VISIBLE);
                } else
                    aksamSaatET.setVisibility(View.INVISIBLE);

            }
        });

    }

    //ilacın telefonun takviminde bulunan etkinlik kısmına eklenmesini sağlar.

    public void takvimeKayitEkleme(Ilaclar ilac)
    {


        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime",calendar.getTimeInMillis());
        intent.putExtra("allday",false);
        intent.putExtra("endTime",calendar.getTimeInMillis() + 3600000);
        intent.putExtra("title",ilac.getIlac_name());
        intent.putExtra("description",ilac.getIlac_kullanma_nedeni());
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilac);
        init();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        setDateTimeField();
        geriFunc();
        visibility();
        gettingIlacInfo();


    }


}