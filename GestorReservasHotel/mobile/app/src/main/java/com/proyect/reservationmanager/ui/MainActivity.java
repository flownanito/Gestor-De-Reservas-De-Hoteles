package com.proyect.reservationmanager.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.proyect.reservationmanager.AdminDashboardActivity;
import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.adapter.BookingAdapter;
import com.proyect.reservationmanager.model.Room;
import com.proyect.reservationmanager.model.RoomState;
import com.proyect.reservationmanager.model.RoomType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private RecyclerView rvRooms;
  private BookingAdapter adapter;
  private TextInputEditText etDateStart, etDateEnd, etGuests;
  private Button btnSearch;
  private List<Room> roomList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // 1. Vincular vistas
    rvRooms = findViewById(R.id.rvRooms);
    etDateStart = findViewById(R.id.etDateStart);
    etDateEnd = findViewById(R.id.etDateEnd);
    etGuests = findViewById(R.id.etGuests);
    btnSearch = findViewById(R.id.btnSearch);

    try {
      Button btnAdmin = findViewById(R.id.btnAdmin);
      if (btnAdmin != null) {
        btnAdmin.setOnClickListener(v -> {
          Intent intent = new Intent(MainActivity.this, AdminDashboardActivity.class);
          startActivity(intent);
        });
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    Button btnMyReservations = findViewById(R.id.btnMyReservations);
    btnMyReservations.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, com.proyect.reservationmanager.ui.ReservationManagementActivity.class);
      startActivity(intent);
    });

    rvRooms.setLayoutManager(new LinearLayoutManager(this));

    loadMockData();

    adapter = new BookingAdapter(roomList, room -> {

      String nombreHabitacion = (room.getRoomType() != null) ? room.getRoomType().getName() : "Habitación " + room.getRoomNumber();
      Double precio = (room.getRoomType() != null) ? room.getRoomType().getPricePerNight() : 0.0;

      Toast.makeText(this, "Reservando: " + nombreHabitacion, Toast.LENGTH_SHORT).show();

      Intent intent = new Intent(MainActivity.this, ReservationDetailsActivity.class);
      intent.putExtra("ROOM_NAME", nombreHabitacion);
      intent.putExtra("ROOM_PRICE", String.valueOf(precio));
      startActivity(intent);
    });

    rvRooms.setAdapter(adapter);

    setupDatePicker(etDateStart);
    setupDatePicker(etDateEnd);

    btnSearch.setOnClickListener(v -> Toast.makeText(this, "Actualizando lista...", Toast.LENGTH_SHORT).show());
  }

  private void loadMockData() {
    roomList = new ArrayList<>();

    RoomType typeLuxury = new RoomType();
    typeLuxury.setId(1L);
    typeLuxury.setName("Suite Presidencial");
    typeLuxury.setDescription("Vista al mar, jacuzzi y cama King Size.");
    typeLuxury.setPricePerNight(150.0);

    RoomType typeStandard = new RoomType();
    typeStandard.setId(2L);
    typeStandard.setName("Doble Estándar");
    typeStandard.setDescription("Cama doble, TV y baño privado.");
    typeStandard.setPricePerNight(80.0);

    RoomType typeSingle = new RoomType();
    typeSingle.setId(3L);
    typeSingle.setName("Individual");
    typeSingle.setDescription("Ideal para viajeros solitarios.");
    typeSingle.setPricePerNight(45.0);

    RoomState stateFree = new RoomState();
    stateFree.setId(1L);
    stateFree.setStateName("Disponible");

    Room r1 = new Room();
    r1.setId(1L);
    r1.setRoomNumber("101");
    r1.setFloor(1);
    r1.setRoomType(typeLuxury);
    r1.setRoomState(stateFree);

    Room r2 = new Room();
    r2.setId(2L);
    r2.setRoomNumber("102");
    r2.setFloor(1);
    r2.setRoomType(typeStandard);
    r2.setRoomState(stateFree);

    Room r3 = new Room();
    r3.setId(3L);
    r3.setRoomNumber("205");
    r3.setFloor(2);
    r3.setRoomType(typeSingle);
    r3.setRoomState(stateFree);

    roomList.add(r1);
    roomList.add(r2);
    roomList.add(r3);
  }

  private void setupDatePicker(TextInputEditText editText) {
    editText.setOnClickListener(v -> {
      Calendar calendar = Calendar.getInstance();
      new DatePickerDialog(this,
              (view, year, month, day) -> editText.setText(day + "/" + (month + 1) + "/" + year),
              calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
      ).show();
    });
  }
}