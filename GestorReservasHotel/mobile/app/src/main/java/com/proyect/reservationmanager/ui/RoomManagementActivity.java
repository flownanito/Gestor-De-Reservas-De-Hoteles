package com.proyect.reservationmanager.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.adapter.RoomAdapter;
import com.proyect.reservationmanager.model.Room;
import com.proyect.reservationmanager.model.RoomState;
import com.proyect.reservationmanager.model.RoomType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RoomManagementActivity extends AppCompatActivity {

    private RecyclerView rvRooms;
    private RoomAdapter adapter;
    private List<Room> roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_management);

        rvRooms = findViewById(R.id.rvRooms);
        FloatingActionButton fab = findViewById(R.id.fabAddRoom);

        rvRooms.setLayoutManager(new LinearLayoutManager(this));

        roomList = getMockRooms();

        adapter = new RoomAdapter(roomList, new RoomAdapter.OnRoomActionListener() {
            @Override
            public void onEdit(Room room) {
                Toast.makeText(RoomManagementActivity.this, "Editar Habitación: " + room.getRoomNumber(), Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onDelete(Room room) {
                Toast.makeText(RoomManagementActivity.this, "Borrar Habitación: " + room.getRoomNumber(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
        rvRooms.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            Toast.makeText(this, "Añadir Nueva Habitación", Toast.LENGTH_SHORT).show();
        });
    }

    private List<Room> getMockRooms() {
        List<Room> list = new ArrayList<>();

        RoomType typeSingle = new RoomType();

        RoomType typeDouble = new RoomType();

        RoomState stateFree = new RoomState();

        RoomState stateOccupied = new RoomState();


        Room r1 = new Room(1L, "101", 1, stateFree, typeSingle, new ArrayList<>());

        Room r2 = new Room(2L, "102", 1, stateOccupied, typeDouble, new ArrayList<>());

        Room r3 = new Room(3L, "201", 2, stateFree, typeDouble, new ArrayList<>());

        list.add(r1);
        list.add(r2);
        list.add(r3);

        return list;
    }
}