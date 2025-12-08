package com.proyect.reservationmanager.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.proyect.reservationmanager.R;

public class HomeActivity extends AppCompatActivity {

  private TextView tvWelcome;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    tvWelcome = findViewById(R.id.tvWelcome);

    // Recupera el nombre del ususario guardado en LoginActivity
    SharedPreferences prefs = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
    String userName = prefs.getString("USER_NAME", "Viajero");

    tvWelcome.setText("Hola, " + userName);
  }
}
