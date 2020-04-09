package edu.utng.mx.iothome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class MainActivity extends Activity {



    private TextView txtFirebase;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Button btnAlarma,btnLuces,btnPuertas,btnVentilacion,btnEstadisticas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Inicializacion firebase

        inicializarFirebase();

        //Inicializacion de objetos de tipo boton

        btnAlarma = findViewById(R.id.btnAlarma);
        btnLuces = findViewById(R.id.btnLuces);
        btnPuertas = findViewById(R.id.btnPuertas);
        btnVentilacion = findViewById(R.id.btnVentilacion);

        //Abre el activity de alarma
        btnAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,activity_alarma.class);
                startActivity(intent);

            }
        });

        //Abre el activity de luces
        btnLuces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,activity_luces.class);
                startActivity(intent);

            }
        });

        //Abre el activity de puertas
        btnPuertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,activity_puertas.class);
                startActivity(intent);

            }
        });

        //Abre el activity de ventilacion
        btnVentilacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,activity_ventilacion.class);
                startActivity(intent);

            }
        });

    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


}
