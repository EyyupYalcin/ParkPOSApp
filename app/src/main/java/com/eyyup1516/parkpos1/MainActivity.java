package com.eyyup1516.parkpos1;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText editplaka,editgiris,editcikis;
    Button btnkaydet,btngoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editplaka = (EditText)findViewById(R.id.plaka);
        editgiris = (EditText)findViewById(R.id.giris);
        editcikis = (EditText)findViewById(R.id.cikis);
        btnkaydet = (Button)findViewById(R.id.kaydet);
        btngoster = (Button)findViewById(R.id.goster);

        AddData();
        viewAll();

    }

    public void AddData(){
        btnkaydet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isInserted = myDb.insertData(editplaka.getText().toString(),
                                editgiris.getText().toString(),
                               editcikis.getText().toString());

                        if(isInserted == true) {
                            Toast.makeText(MainActivity.this, "Plaka Kaydedildi",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(MainActivity.this,"Plaka Kaydedilemedi",
                                    Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    public void viewAll(){
        btngoster.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor res = myDb.getAllData();

                        if(res.getCount()==0){

                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();

                        while(res.moveToNext()){

                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("Plaka:"+res.getString(1)+"\n");
                            buffer.append("Giris :"+res.getString(2)+"\n");
                            buffer.append("Cikis :"+res.getString(3)+"\n\n");
                        }


                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }



    public void showMessage(String title, String message){
        // AlertDialog.Builder sinifi ile kullaniciya Toast'dan daha farkli bir tipde mesaj
        // gonderebiliyoruz.
        // Asagidaki sekilde nesneyi olusturduk.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Nesnenin ozelliklerini belirledik.
        builder.setCancelable(true);// bu ifade true ise kullanici gonderilen mesaji kapatabilir
        builder.setTitle(title); // mesajin basligi
        builder.setMessage(message); // mesajin icerigi
        builder.show(); // mesaji gosterir
    }
}
