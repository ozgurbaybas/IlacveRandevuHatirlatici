package com.example.zgr.ilachatirlatici;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zgr.ilachatirlatici.Siniflar.SharedPreference;
import com.example.zgr.ilachatirlatici.Siniflar.Uyeler;

public class MainActivity extends AppCompatActivity {

    // kullanacağım bütün elemanları tanımladım.

    public EditText kullaniciAdıEditText;
    private EditText passwordEditText;
    private Button girisYapButton;
    private Button uyeOlButton;

    public static SharedPreference sharedPreference;
    public static DatabaseConnector dConnector;

    Activity context = this;


    int login_counter = 3;
    public static Uyeler uye;


    //init fonksiyonu elemanların initialize edilmesi için yazdığım fonksiyondur.


    private void init() {

        kullaniciAdıEditText = (EditText) findViewById(R.id.kullaniciAdıEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        girisYapButton = (Button) findViewById(R.id.girisYapButton);
        uyeOlButton = (Button) findViewById(R.id.uyeOlButton);
        uye = new Uyeler();

        sharedPreference = new SharedPreference();

        dConnector = new DatabaseConnector(this,"ilacAppDB",null,1);

    }

//Shared preference yardımı ile kullanıcı bir kere giriş yaptığında onun bir daha şifre girmeden direk kullanıcı ekranına ulaşmasını sağlar.
    //Bu method da sadece sharedPreference kaydı yapılır.

    public void gettingObjectFromOtherActivity(){


        uye = dConnector.getRecord(kullaniciAdıEditText.getEditableText().toString(),passwordEditText.getEditableText().toString());


        sharedPreference.saveInt(context,uye.getUye_id(),"ID");
        sharedPreference.saveString(context,"logged","LOGIN");
        Intent intent = new Intent("com.example.zgr.ilachatirlatici.Kullanici");
        startActivity(intent);

    }

    //kullanıcı yanlış şifre girdiyse uyarı ekranı açılır.

    public void creatingAlertDialogForLogin()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Yanlış kullanıcı adı veya şifre girdiniz! " + "Lütfen bir daha deneyiniz. " +
                "                  Son " + (login_counter - 1) + " hakkınız kaldı.")
                .setTitle("Lütfen Dikkat!");
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

//kullanıcı giriş yap butonuna bastığında gerekenlerin yapılmasını sağlar.

    private void loginButton() {
        girisYapButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        try {

                            //database'de kayıtlı kullanıcı varsa sisteme giriş yapmayı sağlar.

                            if ((dConnector.login(kullaniciAdıEditText.getEditableText().toString(), passwordEditText.getEditableText().toString())) == true) {


                                gettingObjectFromOtherActivity();
                            }

                            else {


                                creatingAlertDialogForLogin();
                                clear();
                                login_counter--;

                                if (login_counter == 0) {

                                    girisYapButton.setEnabled(false);

                                }

                            }
                        }

                        catch (Exception e) {

                            Toast.makeText(getApplicationContext(), "Login Failed, Record Not Found", Toast.LENGTH_SHORT).show();

                            e.printStackTrace();

                        }
                    }
                }
        );
    }

    // üye olma sayfasının açılmasını sağlar.

    private void uyeOlButton() {
        uyeOlButton.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent("com.example.zgr.ilachatirlatici.UyeFormu");
                        startActivity(intent);

                    }
                }
        );
    }

    //Textviewlerin temizlenmesini sağlar.
    private void clear(){
        kullaniciAdıEditText.setText("");
        passwordEditText.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loginButton();
        uyeOlButton();

        //eğer kullanıcı daha önce giriş yaptıysa direk kullanıcı sayfasına yönlenmeyi sağlar.
        if(sharedPreference.getStringValue(context,"LOGIN").toString().equals("logged"))
        {
            Intent i = new Intent(MainActivity.this,Kullanici.class);
            startActivity(i);

        }

    }
}
