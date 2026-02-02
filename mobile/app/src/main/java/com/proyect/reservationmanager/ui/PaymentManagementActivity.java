package com.proyect.reservationmanager.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.adapter.CardAdapter;
import com.proyect.reservationmanager.adapter.TransactionAdapter;
import com.proyect.reservationmanager.model.Payment;
import com.proyect.reservationmanager.model.PaymentCard;

import java.util.ArrayList;
import java.util.List;

public class PaymentManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerCards, recyclerTransactions;
    private List<PaymentCard> cards = new ArrayList<>();
    private List<Payment> transactions = new ArrayList<>();

    private CardAdapter cardAdapter;
    private TransactionAdapter txAdapter;

    private LinearLayout formContainer;
    private EditText etCardNumber, etExpiration, etCvv, etHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_management);

        recyclerCards = findViewById(R.id.recyclerCards);
        recyclerTransactions = findViewById(R.id.recyclerTransactions);
        formContainer = findViewById(R.id.formContainer);

        recyclerCards.setLayoutManager(new LinearLayoutManager(this));
        recyclerTransactions.setLayoutManager(new LinearLayoutManager(this));

        formContainer.setVisibility(View.GONE);

        cardAdapter = new CardAdapter(cards, position -> showDeleteConfirmation(position));
        recyclerCards.setAdapter(cardAdapter);

        txAdapter = new TransactionAdapter(transactions);
        recyclerTransactions.setAdapter(txAdapter);

        etCardNumber = findViewById(R.id.etCardNumber);
        etCvv = findViewById(R.id.etCvv);
        etExpiration = findViewById(R.id.etExpiration);
        etHolder = findViewById(R.id.etHolder);

        Button btnAddCard = findViewById(R.id.btnAddCard);
        Button btnSaveCard = findViewById(R.id.btnSaveCard);

        btnAddCard.setOnClickListener(v -> toggleFormVisibility());
        btnSaveCard.setOnClickListener(v -> saveCard());
    }

    private void toggleFormVisibility() {
        if (formContainer.getVisibility() == View.GONE) {
            formContainer.setVisibility(View.VISIBLE);
            clearForm();
        } else {
            formContainer.setVisibility(View.GONE);
        }
    }

    private void saveCard() {
        String number = etCardNumber.getText().toString().trim();
        String exp = etExpiration.getText().toString().trim();
        String cvv = etCvv.getText().toString().trim();
        String holder = etHolder.getText().toString().trim();

        if (number.isEmpty() || exp.isEmpty() || cvv.isEmpty() || holder.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidCard(number)) {
            Toast.makeText(this, "Número de tarjeta inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        cards.add(new PaymentCard(number, holder, exp));
        cardAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Tarjeta guardada", Toast.LENGTH_SHORT).show();

        clearForm();
        formContainer.setVisibility(View.GONE);
    }

    private void showDeleteConfirmation(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar tarjeta")
                .setMessage("¿Estás seguro de que deseas eliminar esta tarjeta?")
                .setPositiveButton("Eliminar", (dialog, which) -> deleteCard(position))
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void deleteCard(int position) {
        cards.remove(position);
        cardAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Tarjeta eliminada", Toast.LENGTH_SHORT).show();
    }

    private void clearForm() {
        etCardNumber.setText("");
        etExpiration.setText("");
        etCvv.setText("");
        etHolder.setText("");
    }

    private boolean isValidCard(String number) {
        int sum = 0;
        boolean alternate = false;

        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(number.substring(i, i + 1));

            if (alternate) {
                n *= 2;
                if (n > 9)
                    n = (n % 10) + 1;
            }

            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }
}
