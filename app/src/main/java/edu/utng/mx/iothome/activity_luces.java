package edu.utng.mx.iothome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class activity_luces extends Activity {

    Button btnVolver,btnVerEstadisticas;
    Switch stEncendidoManual;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luces);

        btnVolver = findViewById(R.id.btnVolver);
        btnVerEstadisticas = findViewById(R.id.btnVerEstadisticas);
        stEncendidoManual = findViewById(R.id.stEncendidoManual);
        inicializarFirebase();
        listarDatos();


        //Volver boton
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_luces.this,MainActivity.class);
                startActivity(intent);

            }
        });

        btnVerEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_luces.this,activity_luces_estadisticas.class);
                startActivity(intent);

            }
        });

        //SWICTH ENCENDIDO MANUAL
        stEncendidoManual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                Map<String, Object> dispositivoMap = new HashMap<>();

                if(isChecked) {
                    dispositivoMap.put("encendidoManual", "Encendido");
                }else{
                    dispositivoMap.put("encendidoManual", "Apagado");
                }
                databaseReference.child("Luz").updateChildren(dispositivoMap);
            }
        });



    }

    private void inicializarFirebase() {
        //CONEXION
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listarDatos() {

//LISTAR DATOS
        databaseReference.child("Luz").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {


                    String encendidoManual = dataSnapshot.child("encendidoManual").getValue().toString();

                    if (encendidoManual == "Encendido") {
                        stEncendidoManual.setChecked(true);
                    } else {
                        stEncendidoManual.setChecked(false);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
