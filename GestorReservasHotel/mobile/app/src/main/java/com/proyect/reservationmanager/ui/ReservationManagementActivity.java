package com.proyect.reservationmanager.ui;

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

import com.proyect.reservationmanager.R;
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

        String[] statuses = { "Todos", "Confirmada", "Pendiente", "Cancelada" };
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statuses);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(spinnerAdapter);

        rvReservations.setLayoutManager(new LinearLayoutManager(this));

        reservationList = getMockReservations();

        adapter = new ReservationAdapter(reservationList, reservation -> {
            Intent intent = new Intent(ReservationManagementActivity.this, ReservationDetailsActivity.class);
            intent.putExtra("RESERVATION_ID", reservation.getReservationId());
            startActivity(intent);
        });
        rvReservations.setAdapter(adapter);

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
        });
    }

    private void filter(String text) {
        List<Reservation> filteredList = new ArrayList<>();
        String searchText = text.toLowerCase();

        for (Reservation item : reservationList) {
            String idString = String.valueOf(item.getReservationId());
            String status = item.getCondition().toLowerCase();

            if (idString.contains(searchText) || status.contains(searchText)) {
                filteredList.add(item);
            }
        }

        if (adapter != null) {
            adapter.updateList(filteredList);
        }
    }

    private List<Reservation> getMockReservations() {
        List<Reservation> list = new ArrayList<>();

        list.add(new Reservation(1L, "2023-10-01", "2023-12-01", "2023-12-05", "Confirmada", "2", 500));

        list.add(new Reservation(2L, "2023-10-05", "2023-11-20", "2023-11-22", "Pendiente", "1", 300));

        return list;
    }
}