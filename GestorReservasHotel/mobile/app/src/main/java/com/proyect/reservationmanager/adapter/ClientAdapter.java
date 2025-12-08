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
        holder.tvClientName.setText(client.getName());
        holder.tvClientId.setText("ID: " + client.getDocumentId());
        holder.tvClientContact.setText(client.getPhone() + " | " + client.getEmail());

        holder.itemView.setOnClickListener(v -> listener.onClientClick(client));
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvClientName, tvClientId, tvClientContact;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClientName = itemView.findViewById(R.id.tvClientName);
            tvClientId = itemView.findViewById(R.id.tvClientId);
            tvClientContact = itemView.findViewById(R.id.tvClientContact);
        }
    }
}
