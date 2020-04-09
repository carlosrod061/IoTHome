package edu.utng.mx.iothome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_puertas_Estadisticas extends Activity {

    Button btnVolver;
    TextView txtNombreDispositivo,txtValorSensor,txtProbarDispositivo;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puertas__estadisticas);
        btnVolver = findViewById(R.id.btnVolver);

        inicializarFirebase();
        listarDatos();


        //Volver boton
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_puertas_Estadisticas.this,activity_puertas.class);
                startActivity(intent);

            }
        });
    }




    private void listarDatos(){

        txtNombreDispositivo=findViewById(R.id.txtNombreDispositivo);
        txtValorSensor=findViewById(R.id.txtValorSensor);
        txtProbarDispositivo=findViewById(R.id.txtProbarDispositivo);


        databaseReference.child("Puerta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    String nombreDispositivo = dataSnapshot.child("nombrePuerta").getValue().toString();
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
