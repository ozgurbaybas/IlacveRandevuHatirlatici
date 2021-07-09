package com.example.zgr.ilachatirlatici;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zgr.ilachatirlatici.Siniflar.SharedPreference;
import com.example.zgr.ilachatirlatici.Siniflar.Uyeler;




public class Kullanici extends AppCompatActivity {


    // kullanacağım bütün elemanları tanımladım.

    private TextView HosgeldiniztextView;
    private Button ilaclarimButton;
    private Button randevuButton;
    private Button ayarButton;

    public static Uyeler uye;


    public static SharedPreference sharedPreference;
    Activity context = this;
    DatabaseConnector dConnector;



    //init fonksiyonu elemanların initialize edilmesi için yazdığım fonksiyondur.

    public void init()
    {

        HosgeldiniztextView = (TextView) findViewById(R.id.HosgeldiniztextView);
        ilaclarimButton = (Button)findViewById(R.id.ilaclarimButton);
        randevuButton = (Button)findViewById(R.id.randevuButton);
        ayarButton = (Button)findViewById(R.id.ayarButton);

        dConnector = new DatabaseConnector(this,"ilacAppDB",null,1);
        sharedPreference = MainActivity.sharedPreference;

    }

    //ilaclar sayfasının açılmasını sağlar.

    public void ilaclarButton()
    {
        ilaclarimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.example.zgr.ilachatirlatici.Ilaclarim");
                startActivity(intent);

            }
        });

    }

    //randevular sayfasının açılmasını sağlar.

    public void randevuButton()
    {
        randevuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.example.zgr.ilachatirlatici.Randevularim");
                startActivity(intent);

            }

        });

    }

    //ayarlar sayfasının açılmasını sağlar.

    public void ayarButton()
    {
        ayarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.example.zgr.ilachatirlatici.Ayarlarim");
                startActivity(intent);

            }

        });


    }


    //Kullanıcı adının ve soyadının textview'e yazılmasını sağlar.

    public void getInformationforHosgeldiniz(){

        uye =  dConnector.getRecordWithUyeID(MainActivity.sharedPreference.getIntValue(context,"ID"));


        if(uye != null)
        {

            HosgeldiniztextView.setText("Hoşgeldiniz  "+ uye.getUye_name().toUpperCase()+" "+uye.getUye_surname().toUpperCase());
            HosgeldiniztextView.setTextSize(20);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici);
        init();
        getInformationforHosgeldiniz();
        ilaclarButton();
        randevuButton();
        ayarButton();

    }
}
