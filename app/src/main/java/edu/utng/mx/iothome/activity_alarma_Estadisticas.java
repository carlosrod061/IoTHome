package edu.utng.mx.iothome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.utng.mx.iothome.model.Alarma;

public class activity_alarma_Estadisticas extends Activity {

    Button btnVolver;

    TextView txtNombreDispositivo,txtValorSensor,txtProbarDispositivo;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma__estadisticas);
        btnVolver = findViewById(R.id.btnVolver);
        inicializarFirebase();
        listarDatos();

        //Volver boton
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_alarma_Estadisticas.this,activity_alarma.class);
                startActivity(intent);

            }
        });


    }

    private void listarDatos(){

        txtNombreDispositivo=findViewById(R.id.txtNombreDispositivo);
        txtValorSensor=findViewById(R.id.txtValorSensor);
        txtProbarDispositivo=findViewById(R.id.txtProbarDispositivo);


        databaseReference.child("Alarma").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    String nombreDispositivo = dataSnapshot.child("nombreAlarma").getValue().toString();
                    String valorSensor = dataSnapshot.child("valorSensor").getValue().toString();
                    String probarDispositivo = dataSnapshot.child("encendidoManual").getValue().toString();




                    txtNombreDispositivo.setText(nombreDispositivo);
                    txtValorSensor.setText(valorSensor);
                    txtProbarDispositivo.setText(probarDispositivo);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


}
