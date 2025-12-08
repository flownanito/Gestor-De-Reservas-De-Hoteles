package com.proyect.reservationmanager;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proyect.reservationmanager.adapter.RoomAdapter;
import com.proyect.reservationmanager.model.Room;
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
                Toast.makeText(RoomManagementActivity.this, "Edit Room: " + room.getNumber(), Toast.LENGTH_SHORT)
                        .show();
                // Open edit dialog or activity
            }

            @Override
            public void onDelete(Room room) {
                Toast.makeText(RoomManagementActivity.this, "Delete Room: " + room.getNumber(), Toast.LENGTH_SHORT)
                        .show();
                // Logic to delete room
            }
        });
        rvRooms.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            Toast.makeText(this, "Add New Room", Toast.LENGTH_SHORT).show();
            // Open add room dialog or activity
        });
    }

    private List<Room> getMockRooms() {
        List<Room> list = new ArrayList<>();
        list.add(new Room("101", "Single", 100, "Cozy single room", "Available"));
        list.add(new Room("102", "Double", 150, "Spacious double room", "Occupied"));
        list.add(new Room("201", "Suite", 300, "Luxury suite", "Maintenance"));
        return list;
    }
}
