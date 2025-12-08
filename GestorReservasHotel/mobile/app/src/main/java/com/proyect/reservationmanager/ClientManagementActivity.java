package com.proyect.reservationmanager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.reservationmanager.adapter.ClientAdapter;
import com.proyect.reservationmanager.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientManagementActivity extends AppCompatActivity {

    private RecyclerView rvClients;
    private ClientAdapter adapter;
    private List<Client> clientList;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_management);

        rvClients = findViewById(R.id.rvClients);
        etSearch = findViewById(R.id.etSearchClient);

        rvClients.setLayoutManager(new LinearLayoutManager(this));

        // Cargar datos de prueba adaptados al nuevo modelo
        clientList = getMockClients();

        // Inicializar adaptador
        adapter = new ClientAdapter(clientList, client -> {
            // Al hacer click, mostramos Nombre y Apellido
            String fullName = client.getFirstName() + " " + client.getLastName();
            Toast.makeText(this, "Seleccionado: " + fullName, Toast.LENGTH_SHORT).show();
            // Aquí iría la navegación al detalle o edición
        });
        rvClients.setAdapter(adapter);

        // Lógica del buscador
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
    }

    private void filter(String text) {
        List<Client> filteredList = new ArrayList<>();
        String filterPattern = text.toLowerCase().trim();

        for (Client item : clientList) {
            // Buscamos coincidencia en Nombre, Apellido o DNI
            // Nota: usamos "!= null" para evitar cierres inesperados si algún dato viene vacío
            boolean firstNameMatch = item.getFirstName() != null && item.getFirstName().toLowerCase().contains(filterPattern);
            boolean lastNameMatch = item.getLastName() != null && item.getLastName().toLowerCase().contains(filterPattern);
            boolean dniMatch = item.getDni() != null && item.getDni().toLowerCase().contains(filterPattern);

            if (firstNameMatch || lastNameMatch || dniMatch) {
                filteredList.add(item);
            }
        }

        // Asegúrate de que tu ClientAdapter tenga el método updateList
        adapter.updateList(filteredList);
    }

    private List<Client> getMockClients() {
        List<Client> list = new ArrayList<>();

        // Constructor nuevo: (dni, firstName, lastName, email, password, phone)
        Client c1 = new Client("12345678A", "John", "Doe", "john@example.com", "123456", "555-1234");
        c1.setId(1L); // Seteamos ID manualmente porque es Long

        Client c2 = new Client("87654321B", "Jane", "Smith", "jane@example.com", "123456", "555-5678");
        c2.setId(2L);

        list.add(c1);
        list.add(c2);

        return list;
    }
}