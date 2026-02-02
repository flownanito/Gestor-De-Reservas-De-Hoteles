package com.proyect.reservationmanager.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.adapter.RoomAdapter;
import com.proyect.reservationmanager.model.Room;
import com.proyect.reservationmanager.model.RoomState;
import com.proyect.reservationmanager.model.RoomType;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

  private TextView tvWelcome;
  private RecyclerView rvRooms;
  private RoomAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    // 1. Vincular vistas
    tvWelcome = findViewById(R.id.tvWelcome);
    rvRooms = findViewById(R.id.rvRooms);
    Button btnMyReservations = findViewById(R.id.btnMyReservations);
    Button btnDateRange = findViewById(R.id.btnDateRange);
    Button btnGuests = findViewById(R.id.btnGuests);

    // 2. Lógica del Saludo
    SharedPreferences prefs = getSharedPreferences("UserSession", Context.MODE_PRIVATE);
    String userName = prefs.getString("USER_NAME", "Viajero");
    tvWelcome.setText("Hola, " + userName);

    // 3. Botón "Ver Mis Reservas"
    btnMyReservations.setOnClickListener(v -> {
      Intent intent = new Intent(HomeActivity.this, ReservationManagementActivity.class);
      startActivity(intent);
    });

    // 4. Botones de Filtro
    btnDateRange.setOnClickListener(v ->
            Toast.makeText(this, "Selector de fechas próximamente", Toast.LENGTH_SHORT).show()
    );
    btnGuests.setOnClickListener(v ->
            Toast.makeText(this, "Selector de huéspedes próximamente", Toast.LENGTH_SHORT).show()
    );

    // 5. Configurar RecyclerView (Lista de Habitaciones)
    setupRecyclerView();
  }

  private void setupRecyclerView() {
    rvRooms.setLayoutManager(new LinearLayoutManager(this));

    List<Room> roomList = getMockRooms();

    adapter = new RoomAdapter(roomList, new RoomAdapter.OnRoomActionListener() {
      @Override
      public void onEdit(Room room) {
        Toast.makeText(HomeActivity.this, "Ver detalles de: " + room.getRoomNumber(), Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onDelete(Room room) {
        Toast.makeText(HomeActivity.this, "Reservar: " + room.getRoomNumber(), Toast.LENGTH_SHORT).show();
      }
    });

    rvRooms.setAdapter(adapter);
  }

  private List<Room> getMockRooms() {
    List<Room> list = new ArrayList<>();

    RoomType typeStandard = new RoomType();
    RoomType typeSuite = new RoomType();

    RoomState stateFree = new RoomState();

    list.add(new Room(1L, "101", 1, stateFree, typeStandard, new ArrayList<>()));
    list.add(new Room(2L, "102", 1, stateFree, typeStandard, new ArrayList<>()));
    list.add(new Room(3L, "205", 2, stateFree, typeSuite, new ArrayList<>()));
    list.add(new Room(4L, "304", 3, stateFree, typeSuite, new ArrayList<>()));

    return list;
  }
}