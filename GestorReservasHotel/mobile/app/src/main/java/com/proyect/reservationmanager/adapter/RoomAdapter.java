package com.proyect.reservationmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.model.Room;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private List<Room> roomList;
    private OnRoomActionListener listener;

    public interface OnRoomActionListener {
        void onEdit(Room room);
        void onDelete(Room room);
    }

    public RoomAdapter(List<Room> roomList, OnRoomActionListener listener) {
        this.roomList = roomList;
        this.listener = listener;
    }

    public void updateList(List<Room> newList) {
        this.roomList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = roomList.get(position);

        holder.tvRoomNumber.setText("Habitación " + room.getRoomNumber());

        if (room.getRoomType() != null) {
            holder.tvRoomType.setText(room.getRoomType().toString());
        } else {
            holder.tvRoomType.setText("Tipo sin asignar");
        }

        holder.tvRoomPrice.setText("Piso: " + room.getFloor());

        if (room.getRoomState() != null) {
            holder.tvRoomStatus.setText(room.getRoomState().toString());
        } else {
            holder.tvRoomStatus.setText("Estado desconocido");
        }

        // Listeners para los botones
        holder.btnEdit.setOnClickListener(v -> listener.onEdit(room));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(room));
    }

    @Override
    public int getItemCount() {
        return roomList != null ? roomList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoomNumber, tvRoomType, tvRoomPrice, tvRoomStatus;
        ImageButton btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomNumber = itemView.findViewById(R.id.tvRoomNumber);
            tvRoomType = itemView.findViewById(R.id.tvRoomType);
            tvRoomPrice = itemView.findViewById(R.id.tvRoomPrice);
            tvRoomStatus = itemView.findViewById(R.id.tvRoomStatus);
            btnEdit = itemView.findViewById(R.id.btnEditRoom);
            btnDelete = itemView.findViewById(R.id.btnDeleteRoom);
        }
    }
}