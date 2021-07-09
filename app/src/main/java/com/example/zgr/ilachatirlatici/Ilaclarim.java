package com.example.zgr.ilachatirlatici;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zgr.ilachatirlatici.Siniflar.Ilaclar;
import com.example.zgr.ilachatirlatici.Siniflar.Uyeler;

import java.util.ArrayList;
import java.util.List;


public class Ilaclarim extends AppCompatActivity {


    // kullanacağım bütün elemanları tanımladım.

    private Button ilacEkleButton;
    private ListView ilaclarListView;


    DatabaseConnector databaseConnector;

    final Uyeler uye = Kullanici.uye;

    List<Ilaclar> ilaclar = new ArrayList<Ilaclar>();



    //init fonksiyonu elemanların initialize edilmesi için yazdığım fonksiyondur.

    public void init()
    {
        ilacEkleButton = (Button) findViewById(R.id.ilacEkleButton);
        ilaclarListView = (ListView) findViewById(R.id.ilaclarListView);


        databaseConnector = new DatabaseConnector(this,"ilacAppDB",null,1);


        ilaclar = databaseConnector.getAllIlaclar(uye.getUye_id());

    }

    //ilaç bilgilerinin girileceği ekranın açılmasını sağlar.

    public void ilacEkle()
    {
        ilacEkleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.zgr.ilachatirlatici.Ilac");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilaclarim);
        init();
        ilacEkle();

        //database'deki ilaçların ekranda belirli bir düzende gözükmesini sağlar.
        IlaclarAdapter adapter = new IlaclarAdapter(this,ilaclar,this);
        ilaclarListView.setAdapter(adapter);

    }
}
