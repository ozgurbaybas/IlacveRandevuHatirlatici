package com.example.zgr.ilachatirlatici;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zgr.ilachatirlatici.Siniflar.Uyeler;

public class UyeFormu extends AppCompatActivity {


    // kullanacağım bütün elemanları tanımladım.

    private EditText adiEditText;
    private EditText soyadiEditText;
    private EditText kullaniciAdiEditText;
    private EditText EmailEditText;
    private EditText passwordEditText;
    private Button kaydetButton;
    private Button geriButton;

    DatabaseConnector dConnector;


    public static Uyeler uye = new Uyeler();


    //init fonksiyonu elemanların initialize edilmesi için yazdığım fonksiyondur.

    public void init() {


        adiEditText = (EditText) findViewById(R.id.adiEditText);
        soyadiEditText = (EditText) findViewById(R.id.soyadiEditText);
        kullaniciAdiEditText = (EditText) findViewById(R.id.kullaniciAdiEditText);
        EmailEditText = (EditText) findViewById(R.id.EmailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        kaydetButton = (Button) findViewById(R.id.kaydetButton);
        geriButton = (Button) findViewById(R.id.geriButton);

        dConnector = new DatabaseConnector(this,"ilacAppDB",null,1);


    }

    //kaydet fonksiyonu kaydet tuşuna bastığımız anda gerçekleşecek olayları barındırır.
    public void kaydet() {


        kaydetButton.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View view) {



                if (adiEditText.getEditableText().toString() != "" && soyadiEditText.getEditableText().toString() != "" &&

                        kullaniciAdiEditText.getEditableText().toString() != "" && EmailEditText.getEditableText().toString() != ""
                        && passwordEditText.getEditableText().toString() != "") {

                    //üye bilgileri alınır.

                    uye.setUye_name(adiEditText.getEditableText().toString());
                    uye.setUye_surname(soyadiEditText.getEditableText().toString());
                    uye.setUsername(kullaniciAdiEditText.getEditableText().toString());
                    uye.setEmail(EmailEditText.getEditableText().toString());
                    uye.setPassword(passwordEditText.getEditableText().toString());

                    //database'e üye eklenir.

                    dConnector.addRecordtoUyeler(uye.getUye_name(), uye.getUye_surname(), uye.getUsername(), uye.getEmail(), uye.getPassword());


                    //kaydın başarı ile eklendiğine dair bilgi verilir.

                    final AlertDialog.Builder builder = new AlertDialog.Builder(UyeFormu.this);
                    builder.setMessage("Kaydınız başarı ile gerçekleşmiştir.")
                            .setTitle("Tebrikler");
                    builder.setNeutralButton("Tamam", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();

                    dialog.show();


                    //textler silinir.

                    clear();
                }

                //boş olan alanların doldurulması için uyarı verir.

                else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(UyeFormu.this);
                    alertDialog.setTitle("Uyarı");
                    alertDialog.setMessage("Eksiksiz Doldurunz!");
                    alertDialog.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();

                }
            }


        });


    }

    //geriDon fonksiyonu geri dönüş butonunu kullanamamızı sağlayan fonksiyondur.

    public void geriDon() {
        geriButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                finish();
            }
        });
    }

    //clear fonksiyonu ekrandaki textviewların içini siler.
    public void clear() {
        adiEditText.setText("");
        soyadiEditText.setText("");
        kullaniciAdiEditText.setText("");
        EmailEditText.setText("");
        passwordEditText.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uyeformu);

        try {

            init();
            kaydet();
            geriDon();

        } catch (Exception exc) {
            Log.e("advanced", exc.getMessage());

        }
    }
}
