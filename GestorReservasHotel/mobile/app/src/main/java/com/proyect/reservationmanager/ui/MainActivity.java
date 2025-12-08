package com.proyect.reservationmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.proyect.reservationmanager.ClientManagementActivity;
import com.proyect.reservationmanager.MiPerfilActivity;
import com.proyect.reservationmanager.PaymentManagementActivity;
import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.ReservationManagementActivity;
import com.proyect.reservationmanager.RoomFeaureActivity;
// Nota: Asegúrate de que "RoomFeaureActivity" está bien escrito según el nombre de tu archivo Java

public class MainActivity extends AppCompatActivity {

  private Button btnProfile, btnClients, btnReservations, btnPayments, btnFeatures;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main); // Vincula con tu nuevo diseño de botones

    // 1. Vincular los botones del XML
    btnProfile = findViewById(R.id.btnGoToProfile);
    btnClients = findViewById(R.id.btnGoToClients);
    btnReservations = findViewById(R.id.btnGoToReservations);
    btnPayments = findViewById(R.id.btnGoToPayment);
    btnFeatures = findViewById(R.id.btnGoToFeatures);

    // 2. Configurar la navegación (Intents)

    // Ir a Mi Perfil
    btnProfile.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, MiPerfilActivity.class);
      startActivity(intent);
    });

    // Ir a Gestión de Clientes (Tu parte)
    btnClients.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, ClientManagementActivity.class);
      startActivity(intent);
    });

    // Ir a Gestión de Reservas
    btnReservations.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, ReservationManagementActivity.class);
      startActivity(intent);
    });

    // Ir a Pagos
    btnPayments.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, PaymentManagementActivity.class);
      startActivity(intent);
    });

    // Ir a Características (Features)
    btnFeatures.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, RoomFeaureActivity.class);
      startActivity(intent);
    });
  }
}