package com.proyect.reservationmanager.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.proyect.reservationmanager.R;

import com.proyect.reservationmanager.model.LoginRequest;
import com.proyect.reservationmanager.model.LoginResponse;
import com.proyect.reservationmanager.api.RetrofitClient;
import com.proyect.reservationmanager.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

  private TextInputEditText etEmail, etPassword;
  private MaterialButton btnLogin;
  private TextView tvGoToRegister;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    etEmail = findViewById(R.id.etEmail);
    etPassword = findViewById(R.id.etPassword);
    btnLogin = findViewById(R.id.btnLogin);
    tvGoToRegister = findViewById(R.id.tvGoToRegister);

    btnLogin.setOnClickListener(v -> performLogin());

    tvGoToRegister.setOnClickListener(v -> {
      Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
      startActivity(intent);
    });
  }

  private void performLogin() {
    String email = etEmail.getText().toString().trim();
    String password = etPassword.getText().toString().trim();

    if (email.isEmpty() || password.isEmpty()) {
      Toast.makeText(this, "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show();
      return;
    }

    LoginRequest loginRequest = new LoginRequest(email, password);

    ApiService apiService = RetrofitClient.getInstance().getApi();

    apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
      @Override
      public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
          LoginResponse user = response.body();

          saveUserSession(user);

          Toast.makeText(LoginActivity.this, "Bienvenido " + user.getName(), Toast.LENGTH_SHORT).show();

          Intent intent = new Intent(LoginActivity.this, MainActivity.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
          startActivity(intent);
          finish();

        } else {
          Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<LoginResponse> call, Throwable t) {
        Toast.makeText(LoginActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void saveUserSession(LoginResponse user) {
    SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putLong("USER_ID", user.getId());
    editor.putString("USER_NAME", user.getName());
    editor.putBoolean("IS_LOGGED_IN", true);
    editor.apply();
  }
}