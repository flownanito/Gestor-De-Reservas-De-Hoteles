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
        clientList = getMockClients();

        adapter = new ClientAdapter(clientList, client -> {
            Toast.makeText(this, "Selected: " + client.getName(), Toast.LENGTH_SHORT).show();
            // Navigate to Client Details or Edit
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
    }

    private void filter(String text) {
        List<Client> filteredList = new ArrayList<>();
        for (Client item : clientList) {
            boolean nameMatch = item.getName().toLowerCase().contains(text.toLowerCase());
            boolean idMatch = item.getDocumentId().toLowerCase().contains(text.toLowerCase());
            if (nameMatch || idMatch) {
                filteredList.add(item);
            }
        }
        adapter.updateList(filteredList);
    }

    private List<Client> getMockClients() {
        List<Client> list = new ArrayList<>();
        list.add(new Client("1", "John Doe", "12345678A", "555-1234", "john@example.com"));
        list.add(new Client("2", "Jane Smith", "87654321B", "555-5678", "jane@example.com"));
        return list;
    }
}
