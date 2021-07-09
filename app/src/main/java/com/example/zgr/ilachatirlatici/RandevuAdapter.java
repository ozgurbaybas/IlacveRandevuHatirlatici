package com.example.zgr.ilachatirlatici;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.zgr.ilachatirlatici.Siniflar.Randevular;

import java.util.Date;
import java.util.List;

public class RandevuAdapter extends BaseAdapter {

//Randevuadapter yapılan tasarımın içinin doldurularak ekranda gösterilmesi için yazıldı.

    private Context context;
    private LayoutInflater inflater;
    private List<Randevular> randevuListesi;


    public RandevuAdapter(Activity activity , List<Randevular> randevular, Context context){

        //tasarladığımız layoutun ekranda gözükmesini sağlar.
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.context = context;

        randevuListesi = randevular;



    }

    @Override
    public int getCount() {
        return randevuListesi.size();
    }

    @Override
    public Object getItem(int position) {
        return randevuListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View satirView;

        satirView = inflater.inflate(R.layout.randevular_list_view,null);

        TextView randevunameTV = (TextView) satirView.findViewById(R.id.randevunameTV);
        Button btninfo = (Button) satirView.findViewById(R.id.btninfo);


        final Randevular randevu = randevuListesi.get(position);

        //her bir randevunun adının yazılmasını sağlar.
        randevunameTV.setText(randevu.getRandevu_hastane());

        //butona basıldığında randevu bilgilerinin ekranda gözükmesini sağlar.
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);


                builder.setMessage("Randevuyu aldığınız bölüm : " + hastaneBolum(randevu.getRandevu_brans()) + "\n"
                                +"Randevu tarihiniz : " + randevuTarih(randevu.getRandevu_muayene_tarihi())+ "\n"
                                +"Randevu saatiniz : " + randevuSaat(randevu.getRandevu_muayene_saati()) + "\n"
                );
                builder.setNeutralButton("Tamam", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                AlertDialog dialog = builder.create();

                dialog.show();
            }
        });



        return satirView;
    }

    //Ekranda daha düzgün görünüm sağlamak için yazdım.

    public String hastaneBolum(String bolum)
    {
        if(bolum != null)
        {
            return bolum;
        }
        else {
            return  "-";
        }
    }

    public String randevuSaat(Time saat)
    {
        if(saat != null)
        {

            return saat.hour+":"+saat.minute;
        }
        else {
            return "-";
        }
    }
    public String randevuTarih(Date tarih)
    {
        if(tarih != null)
        {
            return tarih.toLocaleString();
        }
        else {
            return "-";
        }
    }
}
