package com.proyect.reservationmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.proyect.reservationmanager.ui.MainActivity;
import com.proyect.reservationmanager.ui.ClientManagementActivity;
import com.proyect.reservationmanager.ui.MiPerfilActivity;
import com.proyect.reservationmanager.ui.PaymentManagementActivity;
import com.proyect.reservationmanager.ui.ReservationManagementActivity;

public class AdminDashboardActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin_dashboard);

    // 1. Botón "Vista Cliente"
    Button btnSearch = findViewById(R.id.btnGoToSearch);
    btnSearch.setOnClickListener(v -> {
      Intent intent = new Intent(AdminDashboardActivity.this, MainActivity.class);
      startActivity(intent);
    });

    // 2. Mi Perfil
    Button btnProfile = findViewById(R.id.btnGoToProfile);
    btnProfile.setOnClickListener(v -> {
      Intent intent = new Intent(AdminDashboardActivity.this, MiPerfilActivity.class);
      startActivity(intent);
    });

    // 3. Mis Reservas
    Button btnReservations = findViewById(R.id.btnGoToReservations);
    btnReservations.setOnClickListener(v -> {
      Intent intent = new Intent(AdminDashboardActivity.this, ReservationManagementActivity.class);
      startActivity(intent);
    });

    // 4. Gestión Clientes
    Button btnClients = findViewById(R.id.btnGoToClients);
    btnClients.setOnClickListener(v -> {
      Intent intent = new Intent(AdminDashboardActivity.this, ClientManagementActivity.class);
      startActivity(intent);
    });

    // 5. Pagos
    Button btnPayments = findViewById(R.id.btnGoToPayment);
    btnPayments.setOnClickListener(v -> {
      Intent intent = new Intent(AdminDashboardActivity.this, PaymentManagementActivity.class);
      startActivity(intent);
    });
  }
}