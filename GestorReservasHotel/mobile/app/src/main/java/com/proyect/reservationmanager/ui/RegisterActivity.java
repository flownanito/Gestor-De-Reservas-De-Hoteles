package com.proyect.reservationmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.api.ApiService;
import com.proyect.reservationmanager.api.RetrofitClient;
import com.proyect.reservationmanager.models.RegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
  private TextInputEditText etName, etLastName, etEmail, etPassword, etDni;
  private MaterialButton btnRegister;
  private TextView tvGoToLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    // Vincular
    etName = findViewById(R.id.etName);
    etLastName = findViewById(R.id.etLastName);
    etEmail = findViewById(R.id.etEmail);
    etPassword = findViewById(R.id.etPassword);
    etDni = findViewById(R.id.etDni);
    btnRegister = findViewById(R.id.btnRegister);
    tvGoToLogin = findViewById(R.id.tvGoToLogin);

    btnRegister.setOnClickListener(v -> performRegister());

    tvGoToLogin.setOnClickListener(v -> finish());
  }

  private void performRegister() {
    String name = etName.getText().toString().trim();
    String lastName = etLastName.getText().toString().trim();
    String email = etEmail.getText().toString().trim();
    String password = etPassword.getText().toString().trim();
    String dni = etDni.getText().toString().trim();

    // Validaciones
    if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || dni.isEmpty()) {
      Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
      return;
    }

    // Creamos el objeto
    RegisterRequest request = new RegisterRequest(name, lastName, email, password, dni, "");

    ApiService apiService = RetrofitClient.getInstance().getApi();
    apiService.register(request).enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
          Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_LONG).show();
          finish();
        } else {
          Toast.makeText(RegisterActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<Void> call, Throwable t) {
        Toast.makeText(RegisterActivity.this, "Fallo de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}
