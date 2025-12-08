package com.proyect.reservationmanager.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.api.ClientApiService;
import com.proyect.reservationmanager.api.RetrofitClient;
import com.proyect.reservationmanager.model.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
  private EditText etName, etLastName, etEmail, etPassword, etDni, etPhone;
  private Button btnRegister;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_client);

    etDni = findViewById(R.id.etDni);
    etName = findViewById(R.id.etFirstName);
    etLastName = findViewById(R.id.etLastName);
    etEmail = findViewById(R.id.etEmail);
    etPassword = findViewById(R.id.etPassword);
    etPhone = findViewById(R.id.etPhone);

    btnRegister = findViewById(R.id.btnSave);

    btnRegister.setOnClickListener(v -> performRegister());
  }

  private void performRegister() {
    String name = etName.getText().toString().trim();
    String lastName = etLastName.getText().toString().trim();
    String email = etEmail.getText().toString().trim();
    String password = etPassword.getText().toString().trim();
    String dni = etDni.getText().toString().trim();
    String phone = etPhone.getText().toString().trim();

    if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || dni.isEmpty()) {
      Toast.makeText(this, "Rellene todos los campos obligatorios", Toast.LENGTH_SHORT).show();
      return;
    }

    Client client = new Client();
    client.setFirstName(name);
    client.setLastName(lastName);
    client.setEmail(email);
    client.setPassword(password);
    client.setDni(dni);
    client.setPhone(phone);

    ClientApiService clientApiService = RetrofitClient.getInstance().getClientApi();

    clientApiService.createClient(client).enqueue(new Callback<Client>() {
      @Override
      public void onResponse(Call<Client> call, Response<Client> response) {
        if (response.isSuccessful()) {
          Toast.makeText(RegisterActivity.this, "Cliente guardado con éxito", Toast.LENGTH_LONG).show();
          finish();
        } else {
          String msg = "Error: " + response.code();
          if (response.code() == 409) msg = "El usuario o DNI ya existe";
          Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<Client> call, Throwable t) {
        Toast.makeText(RegisterActivity.this, "Fallo de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}