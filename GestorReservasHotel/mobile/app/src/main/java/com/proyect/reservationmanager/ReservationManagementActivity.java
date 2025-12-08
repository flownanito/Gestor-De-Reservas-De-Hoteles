package com.proyect.reservationmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proyect.reservationmanager.adapter.ReservationAdapter;
import com.proyect.reservationmanager.model.Reservation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class ReservationManagementActivity extends AppCompatActivity {

    private RecyclerView rvReservations;
    private ReservationAdapter adapter;
    private List<Reservation> reservationList;
    private EditText etSearch;
    private Spinner spinnerFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_management);

        rvReservations = findViewById(R.id.rvReservations);
        etSearch = findViewById(R.id.etSearchReservation);
        spinnerFilter = findViewById(R.id.spinnerFilterStatus);
        FloatingActionButton fab = findViewById(R.id.fabAddReservation);

        // Setup Spinner
        String[] statuses = { "All", "Confirmed", "Pending", "Cancelled" };
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statuses);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(spinnerAdapter);

        // Setup RecyclerView
        rvReservations.setLayoutManager(new LinearLayoutManager(this));
        reservationList = getMockReservations();
        adapter = new ReservationAdapter(reservationList, reservation -> {
            Intent intent = new Intent(ReservationManagementActivity.this, ReservationDetailsActivity.class);
            // Pass reservation ID if needed
            startActivity(intent);
        });
        rvReservations.setAdapter(adapter);

        // Search Logic
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

        fab.setOnClickListener(v -> {
            // Logic to add new reservation
        });
    }

    private void filter(String text) {
        List<Reservation> filteredList = new ArrayList<>();
        for (Reservation item : reservationList) {
            if (item.getClientName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.updateList(filteredList);
    }

    private List<Reservation> getMockReservations() {
        List<Reservation> list = new ArrayList<>();
        list.add(new Reservation("1", "John Doe", "101", "2023-01-01", "2023-01-05", "Confirmed", 500));
        list.add(new Reservation("2", "Jane Smith", "202", "2023-02-10", "2023-02-12", "Pending", 300));
        return list;
    }
}
