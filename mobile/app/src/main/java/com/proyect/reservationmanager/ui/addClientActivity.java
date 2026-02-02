package com.proyect.reservationmanager.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.api.ApiService;
import com.proyect.reservationmanager.api.RetrofitClient;
import com.proyect.reservationmanager.model.Client;

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
        // 1. Recoger datos
        String dni = etDni.getText().toString().trim();
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        // Validacion básica
        if (dni.isEmpty() || firstName.isEmpty() || email.isEmpty() || password.isEmpty()) {
          Toast.makeText(addClientActivity.this, "Por favor, rellena los campos obligatorios", Toast.LENGTH_SHORT).show();
          return;
        }

        // 2. Crear el objeto Client usando SETTERS
        Client newClient = new Client();
        newClient.setDni(dni);
        newClient.setFirstName(firstName);
        newClient.setLastName(lastName);
        newClient.setEmail(email);
        newClient.setPassword(password);
        newClient.setPhone(phone);

        // 3. Instanciar el servicio
        ApiService apiService = RetrofitClient.getInstance().getApi();

        // 4. Preparar la llamada
        Call<Client> call = apiService.createClient(newClient);

        // 5. Ejecutar llamada asíncrona
        call.enqueue(new Callback<Client>() {
          @Override
          public void onResponse(Call<Client> call, Response<Client> response) {
            if (response.isSuccessful()) {
              Toast.makeText(addClientActivity.this, "Cliente registrado con éxito", Toast.LENGTH_SHORT).show();
              finish(); // Cierra la actividad y vuelve atrás
            } else {
              Toast.makeText(addClientActivity.this, "Error: " + response.code(), Toast.LENGTH_LONG).show();
            }
          }

          @Override
          public void onFailure(Call<Client> call, Throwable t) {
            Toast.makeText(addClientActivity.this, "Fallo de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
          }
        });
      }
    });
  }
}