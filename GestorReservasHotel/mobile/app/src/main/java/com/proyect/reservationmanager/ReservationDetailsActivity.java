package com.proyect.reservationmanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReservationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);

        // Bind Views (Mock data is already in XML for visualization, in real app we
        // populate here)
        Button btnCheckIn = findViewById(R.id.btnCheckIn);
        Button btnCheckOut = findViewById(R.id.btnCheckOut);
        Button btnModify = findViewById(R.id.btnModifyBooking);
        Button btnCancel = findViewById(R.id.btnCancelBooking);

        btnCheckIn.setOnClickListener(v -> {
            Toast.makeText(this, "Check-In Processed", Toast.LENGTH_SHORT).show();
            // Logic to update status to Checked-In
        });

        btnCheckOut.setOnClickListener(v -> {
            Toast.makeText(this, "Check-Out Processed", Toast.LENGTH_SHORT).show();
            // Logic to update status to Checked-Out
        });

        btnModify.setOnClickListener(v -> {
            Toast.makeText(this, "Modify Booking", Toast.LENGTH_SHORT).show();
        });

        btnCancel.setOnClickListener(v -> {
            Toast.makeText(this, "Reservation Cancelled", Toast.LENGTH_SHORT).show();
        });
    }
}
