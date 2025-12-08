package com.proyect.reservationmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.adapter.ClientAdapter;
import com.proyect.reservationmanager.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientManagementActivity extends AppCompatActivity {

    private RecyclerView rvClients;
    private ClientAdapter adapter;
    private List<Client> clientList;
    private EditText etSearch;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_management);

        rvClients = findViewById(R.id.rvClients);
        etSearch = findViewById(R.id.etSearchClient);
        btnAdd = findViewById(R.id.btnAddClient);

        rvClients.setLayoutManager(new LinearLayoutManager(this));

        clientList = getMockClients();

        adapter = new ClientAdapter(clientList, client -> {
            String fullName = client.getFirstName() + " " + client.getLastName();
            Toast.makeText(this, "Seleccionado: " + fullName, Toast.LENGTH_SHORT).show();
        });
        rvClients.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(ClientManagementActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void filter(String text) {
        List<Client> filteredList = new ArrayList<>();
        String filterPattern = text.toLowerCase().trim();

        for (Client item : clientList) {
            boolean firstNameMatch = item.getFirstName() != null && item.getFirstName().toLowerCase().contains(filterPattern);
            boolean lastNameMatch = item.getLastName() != null && item.getLastName().toLowerCase().contains(filterPattern);
            boolean dniMatch = item.getDni() != null && item.getDni().toLowerCase().contains(filterPattern);

            if (firstNameMatch || lastNameMatch || dniMatch) {
                filteredList.add(item);
            }
        }

        if (adapter != null) {
            adapter.updateList(filteredList);
        }
    }

    private List<Client> getMockClients() {
        List<Client> list = new ArrayList<>();

        Client c1 = new Client(1L, "12345678A", "John", "Doe", "john@example.com", "600123456", "2023-01-01");
        Client c2 = new Client(2L, "87654321B", "Jane", "Smith", "jane@example.com", "600987654", "2023-02-15");

        list.add(c1);
        list.add(c2);

        return list;
    }
}