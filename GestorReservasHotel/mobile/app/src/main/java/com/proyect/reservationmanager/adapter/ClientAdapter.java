package com.proyect.reservationmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.model.Client;
import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {

    private List<Client> clientList;
    private OnClientClickListener listener;

    public interface OnClientClickListener {
        void onClientClick(Client client);
    }

    public ClientAdapter(List<Client> clientList, OnClientClickListener listener) {
        this.clientList = clientList;
        this.listener = listener;
    }

    public void updateList(List<Client> newList) {
        this.clientList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Client client = clientList.get(position);

        // 1. Nombre Completo
        String fullName = client.getFirstName();
        if (client.getLastName() != null) {
            fullName += " " + client.getLastName();
        }
        holder.tvClientName.setText(fullName);

        holder.tvClientEmail.setText(client.getEmail());

        holder.tvClientPhone.setText(client.getPhone());

        holder.itemView.setOnClickListener(v -> listener.onClientClick(client));
    }

    @Override
    public int getItemCount() {
        return clientList != null ? clientList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Usamos los IDs que SÍ existen en tu nuevo XML
        TextView tvClientName, tvClientEmail, tvClientPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClientName = itemView.findViewById(R.id.tvClientName);
            tvClientEmail = itemView.findViewById(R.id.tvClientEmail);
            tvClientPhone = itemView.findViewById(R.id.tvClientPhone);
        }
    }
}