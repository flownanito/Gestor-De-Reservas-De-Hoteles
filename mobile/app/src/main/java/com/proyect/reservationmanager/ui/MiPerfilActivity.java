package com.proyect.reservationmanager.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.proyect.reservationmanager.AdminDashboardActivity;
import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.api.ApiClient;
import com.proyect.reservationmanager.api.ApiService;
import com.proyect.reservationmanager.model.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiPerfilActivity extends AppCompatActivity {

    // UI Components
    private TextView tvFullName, tvEmail, tvPhone, tvDni;
    private Button btnEditProfile, btnSearchHotels;
    private LinearLayout btnChangePassword, btnManageNotifications, btnLogout;
    private TextView tvViewAll;
    private ImageButton btnEditPhoto;

    // API Service
    private ApiService apiService;

    // Client data
    private Client currentClient;
    private Long clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        apiService = ApiClient.getClient().create(ApiService.class);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        clientId = prefs.getLong("client_id", -1L);

        initViews();

        if (clientId != -1L) {
            loadClientData();
        } else {
            Toast.makeText(this, "Error: Cliente no identificado", Toast.LENGTH_SHORT).show();
        }

        setupListeners();
    }

    private void initViews() {
        tvFullName = findViewById(R.id.tvFullName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvDni = findViewById(R.id.tvDni);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnSearchHotels = findViewById(R.id.btnSearchHotels);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnManageNotifications = findViewById(R.id.btnManageNotifications);
        btnLogout = findViewById(R.id.btnLogout);
        tvViewAll = findViewById(R.id.tvViewAll);
        btnEditPhoto = findViewById(R.id.btnEditPhoto);
    }

    private void setupListeners() {
        btnEditProfile.setOnClickListener(v -> showEditProfileDialog());

        btnLogout.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            prefs.edit().clear().apply();

            Intent intent = new Intent(MiPerfilActivity.this, AdminDashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        btnSearchHotels.setOnClickListener(v -> {
            Toast.makeText(this, "Navegando a búsqueda de hoteles...", Toast.LENGTH_SHORT).show();
        });

        tvViewAll.setOnClickListener(v -> {
            Toast.makeText(this, "Mostrando todas las reservas...", Toast.LENGTH_SHORT).show();
        });

        btnEditPhoto.setOnClickListener(v -> {
            Toast.makeText(this, "Función de cambio de foto en desarrollo", Toast.LENGTH_SHORT).show();
        });

        btnChangePassword.setOnClickListener(v -> {
            showChangePasswordDialog();
        });

        btnManageNotifications.setOnClickListener(v -> {
            Toast.makeText(this, "Función de notificaciones en desarrollo", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadClientData() {
        Call<Client> call = apiService.getClientById(clientId);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful() && response.body() != null) {
                    currentClient = response.body();
                    updateUI();
                } else {
                    Toast.makeText(MiPerfilActivity.this,
                            "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(MiPerfilActivity.this,
                        "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {
        if (currentClient != null) {
            String fullName = currentClient.getFirstName() + " " + currentClient.getLastName();
            tvFullName.setText(fullName);
            tvEmail.setText(currentClient.getEmail());
            tvPhone.setText(currentClient.getPhone() != null ? currentClient.getPhone() : "No especificado");
            tvDni.setText(currentClient.getDni());
        }
    }

    private void showEditProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_profile, null);
        builder.setView(dialogView);

        // Referencias a los campos del diálogo
        TextInputEditText etFirstName = dialogView.findViewById(R.id.etFirstName);
        TextInputEditText etLastName = dialogView.findViewById(R.id.etLastName);
        TextInputEditText etEmail = dialogView.findViewById(R.id.etEmail);
        TextInputEditText etPhone = dialogView.findViewById(R.id.etPhone);
        TextInputEditText etDni = dialogView.findViewById(R.id.etDni);
        Button btnSave = dialogView.findViewById(R.id.btnSave);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnDelete = dialogView.findViewById(R.id.btnDelete);

        if (currentClient != null) {
            etFirstName.setText(currentClient.getFirstName());
            etLastName.setText(currentClient.getLastName());
            etEmail.setText(currentClient.getEmail());
            etPhone.setText(currentClient.getPhone());
            etDni.setText(currentClient.getDni());
        }

        AlertDialog dialog = builder.create();

        btnSave.setOnClickListener(v -> {
            String firstName = etFirstName.getText().toString().trim();
            String lastName = etLastName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String dni = etDni.getText().toString().trim();

            if (validateInput(firstName, lastName, email, dni)) {
                updateClientData(firstName, lastName, email, phone, dni);
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnDelete.setOnClickListener(v -> {
            dialog.dismiss();
            showDeleteConfirmationDialog();
        });

        dialog.show();
    }

    private boolean validateInput(String firstName, String lastName, String email, String dni) {
        if (firstName.isEmpty()) {
            Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lastName.isEmpty()) {
            Toast.makeText(this, "El apellido es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (dni.isEmpty()) {
            Toast.makeText(this, "El DNI es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateClientData(String firstName, String lastName,
            String email, String phone, String dni) {
        Client updatedClient = new Client();
        updatedClient.setId(currentClient.getId());
        updatedClient.setFirstName(firstName);
        updatedClient.setLastName(lastName);
        updatedClient.setEmail(email);
        updatedClient.setPhone(phone);
        updatedClient.setDni(dni);
        updatedClient.setRegistrationDate(currentClient.getRegistrationDate());

        android.util.Log.d("MiPerfil", "Actualizando cliente ID: " + clientId);
        android.util.Log.d("MiPerfil", "Datos: " + firstName + " " + lastName);

        Call<Client> call = apiService.updateClient(clientId, updatedClient);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                android.util.Log.d("MiPerfil", "Response code: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    currentClient = response.body();
                    updateUI();
                    Toast.makeText(MiPerfilActivity.this,
                            "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    android.util.Log.e("MiPerfil", "Error en respuesta: " + response.code());
                    Toast.makeText(MiPerfilActivity.this,
                            "Error al actualizar el perfil: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                android.util.Log.e("MiPerfil", "Error de conexión", t);
                Toast.makeText(MiPerfilActivity.this,
                        "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showChangePasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_change_password, null);
        builder.setView(dialogView);

        TextInputEditText etCurrentPassword = dialogView.findViewById(R.id.etCurrentPassword);
        TextInputEditText etNewPassword = dialogView.findViewById(R.id.etNewPassword);
        TextInputEditText etConfirmPassword = dialogView.findViewById(R.id.etConfirmPassword);
        Button btnChangePassword = dialogView.findViewById(R.id.btnChangePassword);
        Button btnCancelPassword = dialogView.findViewById(R.id.btnCancelPassword);

        AlertDialog dialog = builder.create();

        btnChangePassword.setOnClickListener(v -> {
            String currentPassword = etCurrentPassword.getText().toString().trim();
            String newPassword = etNewPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (validatePasswordChange(currentPassword, newPassword, confirmPassword)) {
                changePassword(currentPassword, newPassword);
                dialog.dismiss();
            }
        });

        btnCancelPassword.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private boolean validatePasswordChange(String currentPassword, String newPassword, String confirmPassword) {
        if (currentPassword.isEmpty()) {
            Toast.makeText(this, "Introduce tu contraseña actual", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (newPassword.isEmpty()) {
            Toast.makeText(this, "Introduce una nueva contraseña", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (newPassword.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void changePassword(String currentPassword, String newPassword) {

        Client updatedClient = new Client();
        updatedClient.setId(currentClient.getId());
        updatedClient.setFirstName(currentClient.getFirstName());
        updatedClient.setLastName(currentClient.getLastName());
        updatedClient.setEmail(currentClient.getEmail());
        updatedClient.setPhone(currentClient.getPhone());
        updatedClient.setDni(currentClient.getDni());
        updatedClient.setRegistrationDate(currentClient.getRegistrationDate());
        updatedClient.setPassword(newPassword); // Nueva contraseña

        Call<Client> call = apiService.updateClient(clientId, updatedClient);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful() && response.body() != null) {
                    currentClient = response.body();
                    Toast.makeText(MiPerfilActivity.this,
                            "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MiPerfilActivity.this,
                            "Error al cambiar la contraseña: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(MiPerfilActivity.this,
                        "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Cuenta")
                .setMessage("¿Estás seguro de que deseas eliminar tu cuenta? Esta acción no se puede deshacer.")
                .setPositiveButton("Eliminar", (dialog, which) -> deleteClient())
                .setNegativeButton("Cancelar", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void deleteClient() {
        Call<Void> call = apiService.deleteClient(clientId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MiPerfilActivity.this,
                            "Cuenta eliminada correctamente", Toast.LENGTH_SHORT).show();

                    // Limpiar sesión y volver al login
                    SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    prefs.edit().clear().apply();

                    Intent intent = new Intent(MiPerfilActivity.this, AdminDashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MiPerfilActivity.this,
                            "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MiPerfilActivity.this,
                        "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}