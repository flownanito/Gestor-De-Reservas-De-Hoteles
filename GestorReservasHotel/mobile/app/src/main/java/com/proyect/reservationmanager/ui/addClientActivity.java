package com.proyect.reservationmanager.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.api.ClientApiService;
import com.proyect.reservationmanager.api.RetrofitClient;
import com.proyect.reservationmanager.models.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addClientActivity extends AppCompatActivity {
  private EditText etDni, etFirstName, etLastName, etEmail, etPassword, etPhone;
  private Button btnSave;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_client);

    etDni = findViewById(R.id.etDni);
    etFirstName = findViewById(R.id.etFirstName);
    etLastName = findViewById(R.id.etLastName);
    etEmail = findViewById(R.id.etEmail);
    etPassword = findViewById(R.id.etPassword);
    etPhone = findViewById(R.id.etPhone);
    btnSave = findViewById(R.id.btnSave);

    btnSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String dni = etDni.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String phone = etPhone.getText().toString();

        Client newClient = new Client(dni, firstName, lastName, email, password, phone);
        // Instancias el servicio
        ClientApiService apiService = RetrofitClient.getInstance().getClientApi();

        // Preparar la llamada (enviando el objeto newClient)
        Call<Client> call = apiService.createClient(newClient);

        // Ejecutar
        call.enqueue(new Callback<Client>() {
          @Override
          public void onResponse(Call<Client> call, Response<Client> response) {
            if (response.isSuccessful()) {
              Toast.makeText(addClientActivity.this, "Cliente guardado", Toast.LENGTH_SHORT).show();

              finish();
            } else {
              Toast.makeText(addClientActivity.this, "Error al guardar: " + response.code(), Toast.LENGTH_LONG).show();
            }
          }

          @Override
          public void onFailure(Call<Client> call, Throwable t) {
            Toast.makeText(addClientActivity.this, "Tiempo de conexión agotado: " + t.getMessage(), Toast.LENGTH_LONG).show();
          }
        });
      }
    });
  }
}