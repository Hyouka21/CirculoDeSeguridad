package com.sosa.circulodeseguridad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;

import servicios.FireBaseMessagin;

public class MainActivity extends AppCompatActivity {
    private Intent servicioFBM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  empezar();
      //  detener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void empezar(){


       // FirebaseMessaging.getInstance().setAnalyticsCollectionEnabled(true);

        FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true);
        FirebaseMessaging.getInstance().subscribeToTopic("hyoukita")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "te susbcribiste con exito";
                        if (!task.isSuccessful()) {
                            msg = "Fallo al susbribirse";
                        }
                        Log.d("PASO", msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        servicioFBM= new Intent(this, FireBaseMessagin.class);
        startService(servicioFBM);

    }
    public void detener() {
        if (servicioFBM != null) {
            stopService(servicioFBM);
            FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(false);
        }
        FirebaseMessaging.getInstance().unsubscribeFromTopic("hyoukita").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String msg = "te desusbcribiste con exito";
                if (!task.isSuccessful()) {
                    msg = "Fallo al desusbribirse";
                }
                Log.d("PASO", msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        FirebaseMessaging.getInstance().deleteToken();
    }
}