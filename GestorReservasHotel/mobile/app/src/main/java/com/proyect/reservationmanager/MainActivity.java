package com.proyect.reservationmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button btnGoToProfile = findViewById(R.id.btnGoToProfile);

    btnGoToProfile.setOnClickListener(v -> {
      // Simular que tenemos un cliente con ID = 1
      SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
      prefs.edit().putLong("client_id", 1L).apply();

      // Abrir MiPerfilActivity
      Intent intent = new Intent(MainActivity.this, MiPerfilActivity.class);
      startActivity(intent);
    });

    Button btnGoToPayment = findViewById(R.id.btnGoToPayment);
    btnGoToPayment.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, PaymentManagementActivity.class);
      startActivity(intent);
    });

      Button btnGoToFeatures = findViewById(R.id.btnGoToFeatures);
      btnGoToFeatures.setOnClickListener(v -> {
          Intent intent = new Intent(MainActivity.this, RoomFeaureActivity.class);
          startActivity(intent);
      });

  }
}