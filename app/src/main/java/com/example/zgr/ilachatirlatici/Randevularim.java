package com.example.zgr.ilachatirlatici;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zgr.ilachatirlatici.Siniflar.Randevular;
import com.example.zgr.ilachatirlatici.Siniflar.Uyeler;

import java.util.ArrayList;
import java.util.List;


public class Randevularim extends AppCompatActivity {

    //kullanacağım bütün elemanları tanımladım.

    private Button randevuEkleButton;
    private Button randevuAlButton;
    private ListView randevularListView;

    List<Randevular> randevular;

    DatabaseConnector databaseConnector;

    Uyeler uye ;

    public Randevularim() {
    }


    //init fonksiyonu elemanların initialize edilmesi için yazdığım fonksiyondur.

    public void init()
    {
        randevuEkleButton = (Button) findViewById(R.id.randevuEkleButton);
        randevuAlButton = (Button) findViewById(R.id.randevuAlButton);
        randevularListView = (ListView) findViewById(R.id.randevularListView);

        databaseConnector = new DatabaseConnector(this,"ilacAppDB",null,1);

        uye = Kullanici.uye;

        randevular = new ArrayList<Randevular>();
    }

    //randevu eklemek için gerekli bilgilerin alınacağı randevu activiy'nin açılmasını sağlar.
    public void randevuEkle()
    {
        randevuEkleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.zgr.ilachatirlatici.Randevu");
                startActivity(intent);
            }
        });
    }

    //randevual internete bağlanarak randevu alma adresine gitmeyi sağlayan fonksiyon.
    public void randevuAl(){

        randevuAlButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hastanerandevu.gov.tr/Randevu/"));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevularim);
        init();
        randevuEkle();
        randevuAl();

        //database'deki randevu kayıtlarını getirir.
        randevular = databaseConnector.getAllRandevular(uye.getUye_id());

        //yazdığım randevu adapter ile randevuların ekranda gözükmesini sağlar.
        RandevuAdapter randevuAdapter = new RandevuAdapter(this,randevular,this);
        randevularListView.setAdapter(randevuAdapter);
    }
}
