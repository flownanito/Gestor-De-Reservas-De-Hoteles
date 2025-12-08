package com.proyect.reservationmanager.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.proyect.reservationmanager.R;

public class ReservationDetailsActivity extends AppCompatActivity {

    private TextView tvRoom, tvPrice;
    private TextInputEditText etCardHolder, etCardNumber, etExpiry, etCVV;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);

        tvRoom = findViewById(R.id.tvSummaryRoom);
        tvPrice = findViewById(R.id.tvSummaryPrice);

        etCardHolder = findViewById(R.id.etCardHolder);
        etCardNumber = findViewById(R.id.etCardNumber);
        etExpiry = findViewById(R.id.etCardExpiry);
        etCVV = findViewById(R.id.etCardCVV);

        btnConfirm = findViewById(R.id.btnConfirmPayment);

        String roomName = getIntent().getStringExtra("ROOM_NAME");
        String roomPrice = getIntent().getStringExtra("ROOM_PRICE");

        if (roomName != null) {
            tvRoom.setText(roomName);
        } else {
            tvRoom.setText("Selección desconocida");
        }

        if (roomPrice != null) {
            tvPrice.setText(roomPrice + " €");
        }

        // 4. Configurar el botón de confirmar
        btnConfirm.setOnClickListener(v -> {
            if (validateForm()) {
                showSuccessDialog();
            }
        });
    }

    private boolean validateForm() {
        if (TextUtils.isEmpty(etCardHolder.getText())) {
            etCardHolder.setError("Introduce el titular");
            return false;
        }
        if (TextUtils.isEmpty(etCardNumber.getText()) || etCardNumber.getText().length() < 16) {
            etCardNumber.setError("Tarjeta inválida (16 dígitos)");
            return false;
        }
        if (TextUtils.isEmpty(etExpiry.getText())) {
            etExpiry.setError("Falta la fecha");
            return false;
        }
        if (TextUtils.isEmpty(etCVV.getText())) {
            etCVV.setError("Falta el CVV");
            return false;
        }
        return true;
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("¡Pago Realizado!")
                .setMessage("Tu reserva se ha confirmado correctamente. ¡Gracias por confiar en nosotros!")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("Volver al Inicio", (dialog, which) -> {
                    finish();
                })
                .setCancelable(false)
                .show();
    }
}