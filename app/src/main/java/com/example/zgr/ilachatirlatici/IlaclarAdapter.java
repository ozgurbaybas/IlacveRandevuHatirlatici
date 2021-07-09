package com.example.zgr.ilachatirlatici;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.zgr.ilachatirlatici.Siniflar.Ilaclar;

import java.util.Date;
import java.util.List;

public class IlaclarAdapter extends BaseAdapter {

    //Ilaclaradapter yapılan tasarımın içinin doldurularak ekranda gösterilmesi için yazıldı.

    private LayoutInflater inflater;
    private List<Ilaclar> ilacListesi;

    private TextView ilacnameTV;
    private Button btninfo;

    private Context context;


    public IlaclarAdapter(Activity activity , List<Ilaclar> ilaclar,Context context) {

        //tasarladığımız layoutun ekranda gözükmesini sağlar.
        inflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;

        ilacListesi = ilaclar;

    }

    @Override
    public int getCount() {
        return ilacListesi.size();
    }

    @Override
    public Object getItem(int position) {
        return ilacListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final View satirView;

        satirView = inflater.inflate(R.layout.ilaclar_list_view,null);

        ilacnameTV = (TextView)satirView.findViewById(R.id.ilacnameTV);
        btninfo =  (Button)satirView.findViewById(R.id. btninfo);


        final Ilaclar ilac = ilacListesi.get(position);

        //her bir ilacın adının yazılmasını sağlar.
        ilacnameTV.setText(ilac.getIlac_name());


        //butona basıldığında ilaç bilgilerinin ekranda gözükmesini sağlar.
        btninfo.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                           final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                           builder.setIcon(R.drawable.info);
                                           builder.setMessage("İlacı kullanma nedeniniz : " + ilacKullanmaNedeni(ilac.getIlac_kullanma_nedeni()) + "\n"
                                                   +"İlaca başlama tarihiniz : " + ilacBaslamaTarihi(ilac.getIlac_baslama_tarihi()) + "\n"
                                                   +"Almanız gereken miktar : " + dozaj(ilac.getIlac_dozaj())+ "\n"
                                                   + "Sabah ilaç saatiniz : " + ilac.getSabah_saat()+"\n"
                                                   + "Öğle ilaç saatiniz : " + ilac.getOgle_saat()+"\n"
                                                   + "Akşam ilaç saatiniz : " + ilac.getAkşam_saat()+ "\n"
                                           );
                                           builder.setNeutralButton("Tamam", new DialogInterface.OnClickListener() {

                                               public void onClick(DialogInterface dialog, int which) {


                                               }
                                           });

                                           AlertDialog dialog = builder.create();

                                           dialog.show();

                                       }
                                   }


        );

        return satirView;
    }

    //Ekranda daha düzgün görünüm sağlamak için yazdım.

    public String ilacBaslamaTarihi(Date tarih){


        if(tarih!=null)
        {
            return tarih.toLocaleString();
        }
        else{
            return "-";
        }

    }

    public String ilacKullanmaNedeni(String neden)
    {
        if(neden!=null)
        {
            return neden;
        }
        else
        {
            return "-";
        }
    }
    public String dozaj(float doz)
    {
        if(doz==0.5)
        {
            return "Yarım içilecek.";
        }
        else if(doz==1.0)
        {
            return "Bütün içilecek.";
        }
        else if(doz==2.0)
        {
            return "İki tane içilecek.";
        }
        else
        {
            return "-";
        }
    }

}

