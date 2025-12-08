package com.proyect.reservationmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.model.Reservation;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private List<Reservation> reservationList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Reservation reservation);
    }

    public ReservationAdapter(List<Reservation> reservationList, OnItemClickListener listener) {
        this.reservationList = reservationList;
        this.listener = listener;
    }

    public void updateList(List<Reservation> newList) {
        this.reservationList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_reservation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        holder.tvClientName.setText(reservation.getClientName());
        holder.tvRoomInfo.setText("Room: " + reservation.getRoomNumber());
        holder.tvDates.setText(reservation.getStartDate() + " - " + reservation.getEndDate());
        holder.tvStatus.setText(reservation.getStatus());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(reservation));
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvClientName, tvRoomInfo, tvDates, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClientName = itemView.findViewById(R.id.tvClientName);
            tvRoomInfo = itemView.findViewById(R.id.tvRoomInfo);
            tvDates = itemView.findViewById(R.id.tvDates);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
