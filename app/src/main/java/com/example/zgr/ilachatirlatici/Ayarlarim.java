package com.example.zgr.ilachatirlatici;

import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class Ayarlarim extends AppCompatActivity {

    // Kullanacağım Bütün Elemanları Tanımladım.
    DatabaseConnector databaseConnector;
    public static Switch titresimSwitch;
    public static Switch sesSwitch;

    //init fonksiyonu elemanların initialize edilmesi için yazdığım fonksiyondur.
    public void init()
    {
        sesSwitch = (Switch)findViewById(R.id.sesSwitch);
        titresimSwitch = (Switch)findViewById(R.id.titresimSwitch);
        databaseConnector = new DatabaseConnector(this,"ilacAppDB",null,1);
    }

    public static boolean sesFunc()
    {
        if(sesSwitch.isChecked() == false)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }
    public static boolean titresimFunc()
    {
        if(titresimSwitch.isChecked() == false)
        {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlarim);
        init();
    }
}