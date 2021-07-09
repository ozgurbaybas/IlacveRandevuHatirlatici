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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zgr.ilachatirlatici.Siniflar.Doktorlar;
import com.example.zgr.ilachatirlatici.Siniflar.ParseTime;
import com.example.zgr.ilachatirlatici.Siniflar.Randevular;
import com.example.zgr.ilachatirlatici.Siniflar.Uyeler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Randevu extends AppCompatActivity implements View.OnClickListener {


    // kullanacağım bütün elemanları tanımladım.
    public static Doktorlar doktor;
    public static Randevular randevu;

    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;


    Calendar calendar=Calendar.getInstance();


    DatabaseConnector databaseConnector ;

    Button randevuEkleButton;


    EditText randevuTarihiET;
    EditText randevuSaatET;
    EditText doktorSoyadiET;
    EditText doktorAdiET;
    EditText randevuBolumuET;
    EditText hastaneAdiET;

    //init fonksiyonu elemanların initialize edilmesi için yazdığım fonksiyondur.
    public void init()
    {

        randevuTarihiET = (EditText) findViewById(R.id.randevuTarihiET);
        randevuTarihiET.setInputType(InputType.TYPE_NULL);
        randevuTarihiET.requestFocus();

        randevuEkleButton = (Button) findViewById(R.id.randevuEkleButton);
        randevuSaatET = (EditText) findViewById(R.id.randevuSaatET);
        doktorSoyadiET = (EditText) findViewById(R.id.doktorSoyadiET);
        doktorAdiET = (EditText) findViewById(R.id.doktorAdiET);
        randevuBolumuET = (EditText) findViewById(R.id.randevuBolumuET);
        hastaneAdiET = (EditText) findViewById(R.id.hastaneAdiET);



        databaseConnector = new DatabaseConnector(this,"ilacAppDB",null,1);


        doktor = new Doktorlar();
        randevu = new Randevular();





    }
    //tarihpickerinin çalışmasını sağlar.

    private void setDateTimeField() {
        randevuTarihiET.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                randevuTarihiET.setText(dateFormatter.format(newDate.getTime()));

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,monthOfYear);
                calendar.set(Calendar.HOUR_OF_DAY,dayOfMonth);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }
    //tarihEdittextinin üzerine basınca tarih picker'ını çıkartır.
    @Override
    public void onClick(View view) {
        if(view == randevuTarihiET) {
            fromDatePickerDialog.show();
        }
    }


    //Randevu ekle butonuna basınca yapılması gerekenlerin yapılmasını sağlar.
    public void randevuEkleFunc()
    {

        //SaatPicker'ının çıkmasını sağlar.
        randevuSaatET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(randevu);
            }
        });


        randevuEkleButton.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {


                                                     Uyeler uye = Kullanici.uye;

                                                     //doktor ve randevu bilgileri alınır.

                                                     doktor.setDoctor_hospital(hastaneAdiET.getEditableText().toString());
                                                     doktor.setDoctor_brans(randevuBolumuET.getEditableText().toString());
                                                     doktor.setDoctor_name(doktorAdiET.getEditableText().toString());
                                                     doktor.setDoctor_surname(doktorSoyadiET.getEditableText().toString());

                                                     randevu.setRandevu_doktor_id(databaseConnector.getDoktorID());

                                                     try {

                                                         Date tarih = new SimpleDateFormat("dd-MM-yyyy").parse(randevuTarihiET.getEditableText().toString());
                                                         randevu.setRandevu_muayene_tarihi(tarih);


                                                     } catch (ParseException e) {
                                                         e.printStackTrace();
                                                     }


                                                     randevu.setRandevu_uye_id(uye.getUye_id());
                                                     randevu.setRandevu_hastane(hastaneAdiET.getEditableText().toString());
                                                     randevu.setRandevu_brans(randevuBolumuET.getEditableText().toString());



                                                     //randevu eklediğimde etkinlik sayfasının açılıp etkinlik olarak eklenerek alarm çalmasını sağlar.
                                                     takvimeKayitEkleme(randevu);


                                                     ////////





                                                     try {



                                                         //Randevu ve doktor objeleri database'e eklenir.



                                                         databaseConnector.addRecordtoDoktor(doktor.getDoctor_name(), doktor.getDoctor_surname(), doktor.getDoctor_brans(),
                                                                 doktor.getDoctor_hospital(), uye.getUye_id());



                                                         databaseConnector.addRecordtoRandevular(randevu.getRandevu_muayene_saati(),randevu.getRandevu_muayene_tarihi(),uye.getUye_id(),databaseConnector.getDoktorID(),randevu.getRandevu_hastane(),randevu.getRandevu_brans());



                                                         //kaydın başarı ile eklendiği bilgisi ekranda gösterilir.

                                                         final AlertDialog.Builder builder = new AlertDialog.Builder(Randevu.this);
                                                         builder.setMessage("Kaydınız başarı ile gerçekleşmiştir.")
                                                                 .setTitle("Tebrikler");
                                                         builder.setNeutralButton("Tamam", new DialogInterface.OnClickListener() {
                                                             public void onClick(DialogInterface dialog, int which) {

                                                             }
                                                         });

                                                         AlertDialog dialog = builder.create();

                                                         dialog.show();




                                                     }

                                                     catch (Exception e)
                                                     {
                                                         Log.e("zgr", "eklemeye calismadi");
                                                     }

                                                 }





                                             }



        );
    }

    //Time picker'ın çalışması ve saatin alınması sağlanır.

    private void setTime(final Randevular randevu) {
        Time t = ParseTime.converter(randevuSaatET.getText().toString());
        if (t == null) {
            Date d = new Date();

            t = new Time();
            t.hour = d.getHours();
            t.minute = d.getMinutes();
        }

        TimePickerDialog dialog = new TimePickerDialog(Randevu.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                randevuSaatET.setText(String.format("%2d:%2d", hourOfDay, minute));

                Time t = new Time();
                t.hour = hourOfDay;
                t.minute = minute;

                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                calendar.set(Calendar.SECOND,0);


                randevu.setRandevu_muayene_saati(t);

            }
        }, t.hour, t.minute, true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                randevuSaatET.setText("");
                randevu.setRandevu_muayene_saati(null);
            }
        });
        dialog.show();
    }

    //randevunun telefonun takviminde bulunan etkinlik kısmına eklenmesini sağlar.
    public void takvimeKayitEkleme(Randevular randevu)
    {


        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime",calendar.getTimeInMillis());
        intent.putExtra("allday",false);
        intent.putExtra("endTime",calendar.getTimeInMillis() + 3600000);
        intent.putExtra("title",randevu.getRandevu_hastane());
        intent.putExtra("description",randevu.getRandevu_brans());
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu);
        init();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        setDateTimeField();
        randevuEkleFunc();

    }

}
